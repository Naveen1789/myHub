import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeSet;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class Phase2StockSort {
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text>{
		
		private Text keyMP1 = new Text(); // set as the company's name.
		private Text valueMP1 = new Text(); // set Volatility.
		
		public void map(LongWritable key, Text value, Context context){	
			
			String line = value.toString();
			System.out.println("line : " + line);
			String[] elements = line.split("\t");
			if((elements != null)&&(elements.length==2)){
				try {
					keyMP1.set(new Text("vol"));
					
					valueMP1.set(elements[1] + "#" + elements[0]);
//					System.out.println("keyMP1 : " + keyMP1.toString());
//					System.out.println("valueMP1 : " + valueMP1.toString());
					Double dTemp = Double.parseDouble(elements[1]);
					if(dTemp > 0){
						context.write(keyMP1, valueMP1);
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		
		int topNStocks = 10 ; 
		int bottomNStocks = 10;
		
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
			
			TreeSet<String> set = new TreeSet<String>();
			

			Double vol = 0.0;
			
			
			String[] tArr;
			String t;
			
			for (Text val: values){
				System.out.println("key : " + key.toString());
				System.out.println("val = " + val.toString());
				
				tArr = (val.toString()).split("#");
				
				double dT = new Double(tArr[0]);
				NumberFormat formatter = new DecimalFormat("###.############################################################");
				
				t = formatter.format(dT) + "#" + tArr[1];
				
				System.out.println(val.toString());
				set.add(t);
			}
			
			context.write(new Text("Bottom stocks"),new Text("Ascending order"));
			context.write(new Text("================================================="),new Text("================================================="));
			Iterator<String> itr = set.iterator();
			String temp = "";
			String[] tempArr ;
			while((itr.hasNext()) && (bottomNStocks>0)){
				
				bottomNStocks--;
				
				temp = itr.next();
				
				tempArr = temp.split("#");
				context.write(new Text(tempArr[1]),new Text((tempArr[0])));
				
			}
			
			context.write(new Text("_________________________________________________"),new Text("_________________________________________________"));
			context.write(new Text("Top stocks"),new Text("Descending order"));
			context.write(new Text("================================================="),new Text("================================================="));
			Iterator<String> backwardItr = set.descendingIterator();
			while((backwardItr.hasNext()) && (topNStocks>0)){
				
				topNStocks--;
				
				temp = backwardItr.next();
				
				tempArr = temp.split("#");
				context.write(new Text(tempArr[1]),new Text((tempArr[0])));
				
			}
		}
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

//class ValueComparator implements Comparator<String> {
//
//    Map<String, Double> base;
//    public ValueComparator(Map<String, Double> base) {
//        this.base = base;
//    }
//
//    // Note: this comparator imposes orderings that are inconsistent with equals.    
//    public int compare(String a, String b) {
//        if (base.get(a) >= base.get(b)) {
//            return -1;
//        } else {
//            return 1;
//        } // returning 0 would merge keys
//    }
//}
