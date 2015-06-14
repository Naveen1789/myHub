
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;


public class Main{


	public static void main(String[] args){

		Configuration conf = HBaseConfiguration.create();
		try {
			
			HBaseAdmin admin = new HBaseAdmin(conf);
			HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("raw"));
			tableDescriptor.addFamily(new HColumnDescriptor("stock"));
			tableDescriptor.addFamily(new HColumnDescriptor("time"));
			tableDescriptor.addFamily(new HColumnDescriptor("price"));
			if ( admin.isTableAvailable("raw")){
				admin.disableTable("raw");
				admin.deleteTable("raw");
			}
			admin.createTable(tableDescriptor);


			Job job = Job.getInstance();
			job.setJarByClass(Main.class);
			FileInputFormat.addInputPath(job, new Path(args[0]));
			job.setInputFormatClass(TextInputFormat.class);
			job.setMapperClass(Job1.Map.class);
			TableMapReduceUtil.initTableReducerJob("raw", null, job);
			job.setNumReduceTasks(0);
			job.waitForCompletion(true);
			
			// Start : MR - Phase 1
			// Source : http://hbase.apache.org/book.html#mapreduce.example
			// Section 51.4
			
			HTableDescriptor target1TableDescriptor = new HTableDescriptor(TableName.valueOf("target1Table"));
			
			target1TableDescriptor.addFamily(new HColumnDescriptor("StockName"));
			target1TableDescriptor.addFamily(new HColumnDescriptor("Volatility"));
			
//			tableDescriptor.addFamily(new HColumnDescriptor("time"));
//			tableDescriptor.addFamily(new HColumnDescriptor("price"));
			if ( admin.isTableAvailable("target1Table")){
				admin.disableTable("target1Table");
				admin.deleteTable("target1Table");
			}
			admin.createTable(target1TableDescriptor);
			
			
			
			Job job1 = Job.getInstance();
			job1.setJarByClass(Phase1.class);     // class that contains mapper and reducer

			Scan scan = new Scan();
			scan.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
			scan.setCacheBlocks(false);  // don't set to true for MR jobs
			// set other scan attrs

			TableMapReduceUtil.initTableMapperJob(
			  "raw",        			// input table
			  scan,               	// Scan instance to control CF and attribute selection
			  Phase1.MyMapper.class,     	// mapper class
			  Text.class,         	// mapper output key
			  Text.class,  			// mapper output value
			  job1);
			TableMapReduceUtil.initTableReducerJob(
			  "target1Table",        		// output table
			  Phase1.MyTableReducer.class,    // reducer class
			  job1);
			job1.setNumReduceTasks(1);   // at least one, adjust as required

			boolean b = job1.waitForCompletion(true);
			if (!b) {
			  throw new IOException("error with job1!");
			}
			
			// End : MR - Phase 1
			
			// Start : MR - Phase 2
			// Source : http://hbase.apache.org/book.html#mapreduce.example
			// Section 51.4
			
			// Table for top 10 stocks
			HTableDescriptor resultsTableDescriptor = new HTableDescriptor(TableName.valueOf("resultsTable"));
			
			resultsTableDescriptor.addFamily(new HColumnDescriptor("StockName"));
			resultsTableDescriptor.addFamily(new HColumnDescriptor("Volatility"));

			if ( admin.isTableAvailable("resultsTable")){
				admin.disableTable("resultsTable");
				admin.deleteTable("resultsTable");
			}
			admin.createTable(resultsTableDescriptor);

			
			
			
			Job job2 = Job.getInstance();
			job2.setJarByClass(Phase2.class);     // class that contains mapper and reducer
			Scan scanNew = new Scan();
			scanNew.setCaching(500);        // 1 is the default in Scan, which will be bad for MapReduce jobs
			scanNew.setCacheBlocks(false);  // don't set to true for MR jobs
			// set other scan attrs

			TableMapReduceUtil.initTableMapperJob(
			  "target1Table",        			// input table
			  scanNew,               	// Scan instance to control CF and attribute selection
			  Phase2.MyMapper.class,     	// mapper class
			  Text.class,         	// mapper output key
			  Text.class,  			// mapper output value
			  job2);
			TableMapReduceUtil.initTableReducerJob(
			  "resultsTable",        		// output table
			  Phase2.MyTableReducer.class,    // reducer class
			  job2);
			job2.setNumReduceTasks(1);   // at least one, adjust as required

			boolean bo = job2.waitForCompletion(true);
			if (!bo) {
			  throw new IOException("error with job2!");
			}
			
			// End : MR - Phase 2
			
			HTable resTab = new HTable(conf,"resultsTable");

			ResultScanner scanner = resTab.getScanner(scanNew);

			TreeMap<String, String> results = new TreeMap<String, String>();

			for( Result scanRes : scanner ){
				String sN = new String(scanRes.getValue(Bytes.toBytes("StockName"),Bytes.toBytes("name")));
				String vol = new String(scanRes.getValue(Bytes.toBytes("Volatility"),Bytes.toBytes("vol")));
				
				double x = new Double(vol);
				
				NumberFormat formatter = new DecimalFormat("###.######################");
				String temp = formatter.format(x);
				results.put(temp,sN);
				// System.out.println(sN + " : " + vol);
			}

        //Iterate over HashMap
		int countTemp = 1 ;
        for(String key: results.keySet()){
			if(countTemp == 1){
				System.out.println("Top 10 results :");
			}
			if(countTemp == 11){
				System.out.println("Last 10 results :");
			}

            System.out.println(key  +" :: "+ results.get(key));
			
            countTemp++;

        }
			
			admin.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

