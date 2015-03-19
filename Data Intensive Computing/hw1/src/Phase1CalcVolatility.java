
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class Phase1CalcVolatility {
	
	public static class Map extends Mapper<LongWritable, Text, Text, Text>{
		
		private Text keyMP1 = new Text(); // set as the company's name.
		private Text valueMP1 = new Text(); // set Monthly Rate of Return.
		
		public void map(LongWritable key, Text value, Context context){			
			
			String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
			// [ Source - http://stackoverflow.com/questions/19012482/how-to-get-the-input-file-name-in-the-mapper-in-a-hadoop-program ]
			
			// Remove .csv
			fileName = fileName.substring(0, (fileName.length() - 4));
			
			String line = value.toString();
			String[] elements = line.split(",");
			
			// Change this part
			// [
			// Remove last 4 chars - .csv
			
			if((elements != null) && (elements.length == 7) && ( elements[0] != null ) && (elements[0].length() == 10) && (elements[6]!=null)){
			try {
				keyMP1.set(fileName);
				valueMP1.set(elements[0]+","+elements[6]);
//				System.out.println("keyMP1 : " + keyMP1.toString());
//				System.out.println("valueMP1 : " + valueMP1.toString());
				
				context.write(keyMP1, valueMP1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ]
			}
		}
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		
		private Text keyRP1 = new Text(); // set as the company's name.
		private Text valueRP1 = new Text(); // set Monthly Rate of Return.
		
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
			
			String tempDate;
			String tempMonth;
			String tempYear;
			String tempAdjCP;
			String temp;
			String date;
			String mapKey;
			String mapValue;
			String minDate;
			String maxDate;
			
			String arr[];
			String dateArr[];

			
			HashMap<String,String> map = new HashMap<String,String>();
			int count = 0 ;			
			for (Text val: values){
				count++;
				// System.out.println("File : " + key.toString());
				// System.out.println("Val : " + val);
				
				
				temp = (val.toString());
				// System.out.println("temp - " + temp);
				
				arr = temp.split(",");
				
				date = arr[0];
				// System.out.println("date - " + date);
				
				dateArr = date.split("-");
				if((dateArr != null) && (dateArr.length == 3)){
				
				tempYear = dateArr[0];
				tempMonth = dateArr[1];
				tempDate = dateArr[2];
				
				tempAdjCP = arr[1];
				
				mapKey = key.toString() + "#"+ tempYear + "#" + tempMonth;
				// System.out.println("mapKey : " + mapKey);
				
				if(map.containsKey(mapKey)){
					// System.out.println("Map contains key");
					temp = map.get(mapKey);
					dateArr = temp.split("#");
					
					if(Integer.parseInt(tempDate) < Integer.parseInt(dateArr[0])){
						mapValue = tempDate + "#" + tempAdjCP + "#" + dateArr[2] + "#" + dateArr[3];
						map.remove(mapKey);
						map.put(mapKey, mapValue);
					}
					else if(Integer.parseInt(tempDate) > Integer.parseInt(dateArr[2])){
						mapValue = dateArr[0] + "#" + dateArr[1] + "#" + tempDate + "#" + tempAdjCP;
						map.remove(mapKey);
						map.put(mapKey, mapValue);
					}
					
				}
				else{
					// System.out.println("Map does not contain key");
					mapValue = tempDate + "#" + tempAdjCP + "#" + tempDate + "#" + tempAdjCP;
					map.put(mapKey, mapValue);
				}
				
			}
				
			}
			// System.out.println("count : " + count);
			// System.out.println("map.size() : " + map.size());
			
			
			// [
			
			// Calculate xBar, n
			
			String min;
			String max;
			String acp;
			
			String tempAcpArr[];
			
			Double xi = 0.0;
			Double xBar = 0.0;
			Double sum = 0.0;
			int n = 0;
			
			
			LinkedList<Double> x = new LinkedList<Double>();
			
			for(String k : map.keySet()){
				acp = map.get(k);
				tempAcpArr = acp.split("#");
				min = tempAcpArr[1];
				max = tempAcpArr[3];
				
				xi = ( Double.parseDouble(max) - Double.parseDouble(min) ) / (Double.parseDouble(min));
				
				sum = sum + xi;
				
				n = n + 1;
				
				x.add(xi);
				
				
			}
			
			// System.out.println("sum : " + sum);
			
			// System.out.println("n : " + n);
			
			xBar = (sum) / n ;
			// System.out.println("xBar : " + xBar);
			// ]
			
			// [
			// Calculate volatility
			Double sumOfSqDiff = 0.0 ;
			for (Double d : x){				
				sumOfSqDiff = sumOfSqDiff + ((d-xBar) * (d-xBar));
			}
			
			// System.out.println("sumOfSqDiff : " + sumOfSqDiff);
			
			Double volatility = 0.0;
			
			if(n > 1){
				volatility = sumOfSqDiff / (n-1);
				volatility = Math.sqrt(volatility);
				
				context.write(key,new Text(volatility.toString()));
			}
			
			// System.out.println("volatility : " + volatility);
			
			
			
			// ]
			
		}
	}


}
