package otherSimpleAlgorithms;

public class RR {
	
	public static boolean chkIfDone(int[] runTime){
		int n = runTime.length;
		for(int i = 0; i < n; i++){
			if(runTime[i] > 0){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int[] arr = {0,5,8};
		int[] run = {10,15,5};
		int q = 4;
		
		int time = arr[0] ;
		int curJob = 0;
		
		int[] waitTime = new int[run.length];
		
		while(chkIfDone(run)){
			if(run[curJob] <= q){
				
				for(int i = 0; i < run.length; i++){
					if((i != curJob) && (arr[i] < (time + q) )){
						waitTime[i] = waitTime[i] + (time + q - arr[i] );
					}
				}
				run[curJob] = 0;
				
			}
			else{
				
			}
			
			if(curJob >= ){
				
			}
			curJob++;
		}
		
	}

}