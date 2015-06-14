/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.accumulo.examples.simple.mapreduce;

import java.io.IOException;
import java.util.HashMap;

import org.apache.accumulo.core.client.mapreduce.AccumuloOutputFormat;
import org.apache.accumulo.core.data.Mutation;
import org.apache.accumulo.core.data.Value;
import org.apache.accumulo.core.security.ColumnVisibility;
import org.apache.accumulo.core.util.CachedConfiguration;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * A simple map reduce job that inserts word counts into accumulo. See the README for instructions on how to run this.
 * 
 */
public class WordCount extends Configured implements Tool {
  private static Options opts;
  private static Option passwordOpt;
  private static Option usernameOpt;
  private static String USAGE = "wordCount <instance name> <zoo keepers> <input dir> <output table>";
  
  static {
    usernameOpt = new Option("u", "username", true, "username");
    passwordOpt = new Option("p", "password", true, "password");
    
    opts = new Options();
    
    opts.addOption(usernameOpt);
    opts.addOption(passwordOpt);
  }
  
  public static class MapClass extends Mapper<LongWritable,Text,Text,Mutation> {
    @Override
    public void map(LongWritable key, Text value, Context output) throws IOException {
    	
    	HashMap<String, String> teams = new HashMap<String, String>(); 
    	
    	teams.put("#Celtics", "Boston Celtics"); 
    	teams.put("#Knicks", "New York Knicks"); 
    	teams.put("#76ers", "Philadelphia 76ers"); 
    	teams.put("#Nets", "New Jersey Nets"); 
    	teams.put("#Raptors", "Toronto Raptors"); 
    	teams.put("#Bulls", "Chicago Bulls");     	
    	teams.put("#Pacers", "Indiana Pacers"); 
    	teams.put("#Bucks", "Milwaukee Bucks"); 
    	teams.put("#Pistons", "Detroit Pistons"); 
    	teams.put("#Cavs", "Cleveland Cavaliers"); 
    	teams.put("#MiamiHeat", "Miami Heat"); 
    	teams.put("#OrlandoMagic", "Orlando Magic"); 
    	teams.put("#Hawks", "Atlanta Hawks"); 
    	teams.put("#Bobcats", "Charlotte Bobcats"); 
    	teams.put("#Wizards", "Washington Wizards"); 
    	teams.put("#okcthunder", "Oklahoma City"); 
    	teams.put("#Nuggets", "Denver Nuggets"); 
    	teams.put("#TrailBlazers", "Portland Trailblazers"); 
    	teams.put("#UtahJazz", "Utah Jazz"); 
    	teams.put("#TWolves", "Minnesota Timberwolves"); 
    	teams.put("#Lakers", "LA Lakers"); 
    	teams.put("#Suns", "Phoenix Suns"); 
    	teams.put("#GSWarriors", "Golden State Warriors"); 
    	teams.put("#Clippers", "L.A. Clippers"); 
    	teams.put("#NBAKings", "Sacramento Kings"); 
    	teams.put("#GoSpursGo", "San Antonio Spurs"); 
    	teams.put("#Mavs", "Dallas Mavericks"); 
    	teams.put("#Hornets", "New Orleans Hornets"); 
    	teams.put("#Grizzlies", "Memphis Grizzlies"); 
    	teams.put("#Rockets", "Houston Rockets");
    	
    	
      FileSplit fileSplit = (FileSplit)output.getInputSplit(); 
      String filename = fileSplit.getPath().getName();
        
      String[] words = value.toString().split("\\s+");
      
      for (String word : words) {
    	  // Wins
    	  if(word.equalsIgnoreCase("win")){
    		  if(filename.contains("Celtics") || filename.contains("Knicks") || filename.contains("76ers") || 
    				  filename.contains("Nets") || filename.contains("Raptors") || filename.contains("Bulls")|| 
    				  filename.contains("Pacers") || filename.contains("Bucks") || filename.contains("Pistons")|| 
    				  filename.contains("Cavs") || filename.contains("MiamiHeat") || filename.contains("OrlandoMagic")|| 
    				  filename.contains("Hawks") || filename.contains("Bobcats") || filename.contains("Wizards")){
    			  
      	        Mutation mutation = new Mutation(new Text(teams.get(word)));
      	        mutation.put(new Text( "#" + (filename.substring(0,(filename.length() - 4)))), new Text("wins"),new ColumnVisibility("east"), new Value("1".getBytes()));
      	        
      	        try {
      	          output.write(new Text("Win"), mutation);
      	        } catch (InterruptedException e) {
      	          e.printStackTrace();
      	        }
    		  }
    		  
    		  else if(filename.contains("okcthunder") || filename.contains("Nuggets") || filename.contains("TrailBlazers") || 
    				  filename.contains("UtahJazz") || filename.contains("TWolves") || filename.contains("Lakers")|| 
    				  filename.contains("Suns") || filename.contains("GSWarriors") || filename.contains("Clippers")|| 
    				  filename.contains("NBAKings") || filename.contains("GoSpursGo") || filename.contains("Mavs")|| 
    				  filename.contains("Hornets") || filename.contains("Grizzlies") || filename.contains("Rockets") ){
    			  
        	        Mutation mutation = new Mutation(new Text(teams.get(word)));
          	        mutation.put(new Text( "#" + (filename.substring(0,(filename.length() - 4)))), new Text("wins"),new ColumnVisibility("west"), new Value("1".getBytes()));

          	        
          	        try {
          	          output.write(new Text("Win"), mutation);
          	        } catch (InterruptedException e) {
          	          e.printStackTrace();
          	        }
        		  }
			  
    	  }
    	  // Losses
    	  else if(word.equalsIgnoreCase("lose")){
    		  
    		  if(filename.contains("Celtics") || filename.contains("Knicks") || filename.contains("76ers") || 
    				  filename.contains("Nets") || filename.contains("Raptors") || filename.contains("Bulls")|| 
    				  filename.contains("Pacers") || filename.contains("Bucks") || filename.contains("Pistons")|| 
    				  filename.contains("Cavs") || filename.contains("MiamiHeat") || filename.contains("OrlandoMagic")|| 
    				  filename.contains("Hawks") || filename.contains("Bobcats") || filename.contains("Wizards")){
    			  
        	        Mutation mutation = new Mutation(new Text(teams.get(word)));
          	        mutation.put(new Text( "#" + (filename.substring(0,(filename.length() - 4)))), new Text("losses"),new ColumnVisibility("east"), new Value("1".getBytes()));

        	        
        	        try {
        	          output.write(new Text("Lose"), mutation);
        	        } catch (InterruptedException e) {
        	          e.printStackTrace();
        	        }
      		  }
    		  else if(filename.contains("okcthunder") || filename.contains("Nuggets") || filename.contains("TrailBlazers") || 
    				  filename.contains("UtahJazz") || filename.contains("TWolves") || filename.contains("Lakers")|| 
    				  filename.contains("Suns") || filename.contains("GSWarriors") || filename.contains("Clippers")|| 
    				  filename.contains("NBAKings") || filename.contains("GoSpursGo") || filename.contains("Mavs")|| 
    				  filename.contains("Hornets") || filename.contains("Grizzlies") || filename.contains("Rockets") ){

        	        Mutation mutation = new Mutation(new Text(teams.get(word)));
          	        mutation.put(new Text( "#" + (filename.substring(0,(filename.length() - 4)))), new Text("losses"),new ColumnVisibility("west"), new Value("1".getBytes()));

            	        
            	        try {
            	          output.write(new Text("Lose"), mutation);
            	        } catch (InterruptedException e) {
            	          e.printStackTrace();
            	        }
          		  }
    		  
    	  }
    	  
    	  else {
    		  continue;
    	  }
        
    	/*
        Mutation mutation = new Mutation(new Text(word));
        mutation.put(new Text("count"), new Text("20080906"), new Value("1".getBytes()));
        
        try {
          output.write(null, mutation);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        */
      }
    }
  }
  
  public int run(String[] unprocessed_args) throws Exception {
    Parser p = new BasicParser();
    
    CommandLine cl = p.parse(opts, unprocessed_args);
    String[] args = cl.getArgs();
    
    String username = cl.getOptionValue(usernameOpt.getOpt(), "root");
    String password = cl.getOptionValue(passwordOpt.getOpt(), "secret");
    
    if (args.length != 4) {
      System.out.println("ERROR: Wrong number of parameters: " + args.length + " instead of 4.");
      return printUsage();
    }
    
    Job job = new Job(getConf(), WordCount.class.getName());
    job.setJarByClass(this.getClass());
    
    job.setInputFormatClass(TextInputFormat.class);
    TextInputFormat.setInputPaths(job, new Path(args[2]));
    
    job.setMapperClass(MapClass.class);
    
    job.setNumReduceTasks(0);
    
    job.setOutputFormatClass(AccumuloOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Mutation.class);
    AccumuloOutputFormat.setOutputInfo(job.getConfiguration(), username, password.getBytes(), true, args[3]);
    AccumuloOutputFormat.setZooKeeperInstance(job.getConfiguration(), args[0], args[1]);
    job.waitForCompletion(true);
    return 0;
  }
  
  private int printUsage() {
    HelpFormatter hf = new HelpFormatter();
    hf.printHelp(USAGE, opts);
    return 0;
  }
  
  public static void main(String[] args) throws Exception {
    int res = ToolRunner.run(CachedConfiguration.getInstance(), new WordCount(), args);
    System.exit(res);
  }
}
