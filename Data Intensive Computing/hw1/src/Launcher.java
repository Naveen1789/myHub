
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class Launcher {
	

	
	/*
	 * Launcher code 
	 */
	

	public static void main(String[] args) throws Exception {		
		Configuration conf = new Configuration();
		
		long start = new Date().getTime();
		
		Job phase1Job = Job.getInstance();
		phase1Job.setJarByClass(Phase1CalcVolatility.class);
		Job phase2Job = Job.getInstance();
		phase2Job.setJarByClass(Phase2StockSort.class);
		
		phase1Job.setMapperClass(Phase1CalcVolatility.Map.class);
		// phase1Job.setCombinerClass(Phase1CalcVolatility.Reduce.class);
		phase1Job.setReducerClass(Phase1CalcVolatility.Reduce.class);
		
		phase1Job.setMapOutputKeyClass(Text.class);
		phase1Job.setMapOutputValueClass(Text.class);
		
		phase2Job.setMapperClass(Phase2StockSort.Map.class);
		// phase2Job.setCombinerClass(Phase2StockSort.Reduce.class);
		phase2Job.setReducerClass(Phase2StockSort.Reduce.class);
		
		phase2Job.setMapOutputKeyClass(Text.class);
		phase1Job.setMapOutputValueClass(Text.class);
		
		// phase1Job.setNumReduceTasks(5);
		 phase2Job.setNumReduceTasks(1);
		
		FileInputFormat.addInputPath(phase1Job, new Path(args[0]));
		FileOutputFormat.setOutputPath(phase1Job, new Path("Inter"));
		
		FileInputFormat.addInputPath(phase2Job, new Path("Inter"));
		FileOutputFormat.setOutputPath(phase2Job, new Path(args[1]));
		
		
		
		phase1Job.waitForCompletion(true);
		phase2Job.waitForCompletion(true);
		
		long end = new Date().getTime();
		
		System.out.println("\nJob took " + (end-start)/1000 + "seconds\n");

	}
}
