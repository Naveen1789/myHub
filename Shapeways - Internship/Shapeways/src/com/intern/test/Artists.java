package com.intern.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Artists {
	
	// HashMap to hold artists and the lines in which they appear
	// Artist's name is the key - which maps to a BitSet representing all the lines in which they occur
	// A bit value of 'true' implies the occurrence of the artist at the line number given by its index
	private static HashMap<String,BitSet> artists = new HashMap<String,BitSet>();
	
		
	/*
	 * Code to read from the input file
	 * Source : http://javarevisited.blogspot.com/2012/07/read-file-line-by-line-java-example-scanner.html
	 */
	
	private static void readFromFile(String filePath){
		
        //reading file line by line in Java using BufferedReader      
        FileInputStream fis = null;
        BufferedReader reader = null;
     
        try {
            fis = new FileInputStream(filePath);
            reader = new BufferedReader(new InputStreamReader(fis));
         
            // temp List - of fav artists for 1 user
            String[] userFav;
                     
            // holds the index to be set to true
            int lineNum = 0 ;
            
            String line = reader.readLine();
            while(line != null){
            	
            	// Extract each artist's name
            	userFav = line.split(",");
            	
            	if((userFav != null) && (userFav.length > 0)){
            		
            		for(String s : userFav){
            			// If the artist is already present in the HashMap, set the artist's corresponding BitSet index to true
            			if(artists.containsKey(s)){
            				artists.get(s).set(lineNum, true);            				
            			}
            			// Create a new BitSet and assign it to the new artist
            			// Add the artist to the HashMap
            			else{
            				BitSet newBs = new BitSet();
            				newBs.set(lineNum,true);
            				artists.put(s,newBs);            				
            			}
            		}
            	}
            	
                line = reader.readLine();
                lineNum++;

            }          
         
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
         
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	private static void displayHashMap(){
		
		Iterator<String> keySetIterator = Artists.artists.keySet().iterator();

		while(keySetIterator.hasNext()){
		  String key = keySetIterator.next();
		  System.out.println("key: " + key + " value: " + Artists.artists.get(key));
		}
		
	}
	
	// For this problem, we only need to consider artists who feature in atleast 50 lines
	private static void removeNotSoPopularArtists(){
		
		// Removing elements from the HashMap while iterating
		// Source : http://stackoverflow.com/questions/1884889/iterating-over-and-removing-from-a-map
		
	    for(Iterator<Map.Entry<String, BitSet>> it = Artists.artists.entrySet().iterator(); it.hasNext(); ) {
	        Map.Entry<String, BitSet> entry = it.next();
	        
	        if(entry.getValue().cardinality() < 50){
	        	it.remove();
	        }
	      }		
	}
	
	private static int findArtistsOccurringTogetherOften(){
		
		int numOfComparisionsMade = 0;
		int commonPairs = 0 ;
		
		BitSet tempBs = new BitSet();
		
		for(Iterator<Map.Entry<String, BitSet>> it1 = Artists.artists.entrySet().iterator(); it1.hasNext(); ) {
			
			Map.Entry<String, BitSet> outerEntry = it1.next();
			
			for(Iterator<Map.Entry<String, BitSet>> it2 = Artists.artists.entrySet().iterator(); it2.hasNext(); ) {
				
				numOfComparisionsMade++;
				
				Map.Entry<String, BitSet> innerEntry = it2.next();
				
				tempBs = (BitSet)outerEntry.getValue().clone();
				
				// Do not compare an artist with himself/herself
				if(! outerEntry.getKey().equals(innerEntry.getKey())){
					
					tempBs.and(innerEntry.getValue());
					
					
					if(tempBs.cardinality() >= 50){
						System.out.println(outerEntry.getKey() + " & " + innerEntry.getKey());
						commonPairs++;
					}
				}
			}
			
			it1.remove();
		}
		
		System.out.println("Number of comparisions made : " + numOfComparisionsMade);
		return commonPairs;
	}
	
	public static void main(String args[]){
				
		Artists.readFromFile("Artist_lists_small.txt");
		
		System.out.println("Number of artists : " + Artists.artists.size());
		// Artists.displayHashMap();
		
		System.out.println("Removing not so popular artists");
		
		Artists.removeNotSoPopularArtists();
		
		System.out.println("Number of artists : " + Artists.artists.size());
		// Artists.displayHashMap();

		System.out.println("Number of common pairs : " + Artists.findArtistsOccurringTogetherOften());
		
	}

}
