import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.TreeSet;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class Phase2 {

	public static class MyMapper extends TableMapper<Text, Text>  {
		
		  public static final byte[] STOCK = "StockName".getBytes();
		  public static final byte[] STOCK_ATTR1 = "name".getBytes();
		  
		  public static final byte[] VOL = "Volatility".getBytes();
		  public static final byte[] VOL_ATTR1 = "vol".getBytes();
		  
		  private Text keyTemp = new Text();
		  private Text valTemp = new Text();
		
		public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
			
			keyTemp.set(new String("key"));
			String val = new String(value.getValue(VOL, VOL_ATTR1)) + "#" + new String(value.getValue(STOCK, STOCK_ATTR1));
			valTemp.set(val);
			
			Double dTemp = Double.parseDouble(new String(value.getValue(VOL, VOL_ATTR1)));
			if(dTemp > 0){
				context.write(keyTemp, valTemp);
			}
			
		}
	}
	
	public static class MyTableReducer extends TableReducer<Text, Text, ImmutableBytesWritable>  {
		
		int topNStocks = 10; 
		int bottomNStocks = 10;
		
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			
			TreeSet<String> set = new TreeSet<String>();
			
			Double vol = 0.0;
			
			
			String[] tArr;
			String t;
			
			for (Text val: values){
				System.out.println("key : " + key.toString());
				System.out.println("val = " + val.toString());
				
				tArr = (val.toString()).split("#");
				
				double dT = new Double(tArr[0]);
				NumberFormat formatter = new DecimalFormat("###.############");
				
				t = formatter.format(dT) + "#" + tArr[1];
				set.add(val.toString());
			}
			
			
			
			Iterator<String> itr = set.iterator();
			String temp = "";
			String[] tempArr ;
			while((itr.hasNext()) && (bottomNStocks>0)){
				
				bottomNStocks--;
				
				temp = itr.next();
				
				tempArr = temp.split("#");
				
				Put put = new Put(Bytes.toBytes("Results" + "-last-" + bottomNStocks));
			    put.add(Bytes.toBytes("StockName"), Bytes.toBytes("name"), Bytes.toBytes(tempArr[1].toString()));
			    put.add(Bytes.toBytes("Volatility"), Bytes.toBytes("vol"), Bytes.toBytes(tempArr[0].toString()));
				
			    context.write(null,put);
				
			}
			
			Iterator<String> backwardItr = set.descendingIterator();
			while((backwardItr.hasNext()) && (topNStocks>0)){
				
				topNStocks--;
				
				temp = backwardItr.next();
				
				tempArr = temp.split("#");
				Put put = new Put(Bytes.toBytes("Results" + "-first-"+ topNStocks));
			    put.add(Bytes.toBytes("StockName"), Bytes.toBytes("name"), Bytes.toBytes(tempArr[1].toString()));
			    put.add(Bytes.toBytes("Volatility"), Bytes.toBytes("vol"), Bytes.toBytes(tempArr[0].toString()));
			    context.write(null,put);
			}
			
			
		}
		
	}
}
