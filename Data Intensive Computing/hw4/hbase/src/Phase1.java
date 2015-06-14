import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;


public class Phase1 {
	
	public static class MyMapper extends TableMapper<Text, Text>  {
		  public static final byte[] STOCK = "stock".getBytes();
		  public static final byte[] STOCK_ATTR1 = "name".getBytes();
		  
		  public static final byte[] TIME = "time".getBytes();
		  public static final byte[] TIME_ATTR1 = "yr".getBytes();
		  public static final byte[] TIME_ATTR2 = "mm".getBytes();
		  public static final byte[] TIME_ATTR3 = "dd".getBytes();
		  
		  public static final byte[] PRICE = "price".getBytes();
		  public static final byte[] PRICE_ATTR1 = "price".getBytes();

		  private Text keyTemp = new Text();
		  private Text valTemp = new Text();

		  public void map(ImmutableBytesWritable row, Result value, Context context) throws IOException, InterruptedException {
		    String key = new String(value.getValue(STOCK, STOCK_ATTR1));
		    
		    String val = new String(value.getValue(TIME, TIME_ATTR1)) + "-" + new String(value.getValue(TIME, TIME_ATTR2)) + "-" + new String(value.getValue(TIME, TIME_ATTR3)) + "," + new String(value.getValue(PRICE, PRICE_ATTR1));
		    
		    keyTemp.set(key);
		    valTemp.set(val);
		    
		    try{
		    context.write(keyTemp, valTemp);
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		  }
		}
	
	public static class MyTableReducer extends TableReducer<Text, Text, ImmutableBytesWritable>  {

		  public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			  
				String tempDate;
				String tempMonth;
				String tempYear;
				String tempAdjCP;
				String temp;
				String date;
				String mapKey;
				String mapValue;
				
				String arr[];
				String dateArr[];

				
				HashMap<String,String> map = new HashMap<String,String>();
			  
		    int count = 0;
		    for (Text val : values) {
//		    	System.out.println("Inside while loop");
		    	count++;
		    	
		    	temp = (val.toString());
		    	
				arr = temp.split(",");
				
				date = arr[0];
//				System.out.println("date : " + date);
				dateArr = date.split("-");
				if((dateArr != null) && (dateArr.length == 3)){
				
				tempYear = dateArr[0];
				tempMonth = dateArr[1];
				tempDate = dateArr[2];
				
				tempAdjCP = arr[1];
//				System.out.println("tempAdjCP : " + tempAdjCP);
				
				mapKey = key.toString() + "#"+ tempYear + "#" + tempMonth;
//				System.out.println("mapKey : " + mapKey);
				
				if(map.containsKey(mapKey)){
					
//					System.out.println("Map contains mapkey");
					// System.out.println("Map contains key");
					temp = map.get(mapKey);
//					System.out.println("temp : " + temp);
					
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
//				System.out.println("volatility : " + volatility);
				
				
			    Put put = new Put(Bytes.toBytes(key.toString()));
			    put.add(Bytes.toBytes("StockName"), Bytes.toBytes("name"), Bytes.toBytes(key.toString()));
			    put.add(Bytes.toBytes("Volatility"), Bytes.toBytes("vol"), Bytes.toBytes(volatility.toString()));

			    context.write(null, put);
			}
		    
		  }
		}

}
