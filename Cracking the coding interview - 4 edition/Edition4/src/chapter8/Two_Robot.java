package chapter8;

import java.util.ArrayList;

public class Two_Robot {
	
	public static void robotPaths(int x, int y, ArrayList<String> paths, String path){
		
		if(x >= 2 && y >= 2){
			String newPath = path + x + "," + y + "\t";
			paths.add(newPath);
		}
		else if(x >= 2){
			String newPath = path + x + "," + y + "\t";
			robotPaths(x,(y+1),paths,newPath);
		}
		else if(y >= 2){
			String newPath = path + x + "," + y + "\t";
			robotPaths((x+1),y,paths,newPath);
		}
		else{
			String newPath1 = path + x + "," + y + "\t";
			robotPaths(x,(y+1),paths,newPath1);
			String newPath2 = path + x + "," + y + "\t";
			robotPaths((x+1),y,paths,newPath2);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> paths = new ArrayList<String>();
		robotPaths(0,0,paths,"");
		for(String s : paths){
			System.out.println(s);
		}
		
	}

}
