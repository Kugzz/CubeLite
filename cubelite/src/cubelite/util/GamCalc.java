package cubelite.util;

public class GamCalc {
	
	static double x, y;
	public static int width, heigth;
	
	public static void playerValues(int x, int y, int width, int heigth) {
		GamCalc.x = x;
		GamCalc.y = y;
		GamCalc.width = width;
		GamCalc.width = width;
	}
	
	public static double[] basisVector(int x, int y, int playerx, int playery){
		int vectorx = x - playerx;
		int vectory = y - playery;
		double[] arr = new double[2];
		double length = Math.sqrt((double)Math.pow(vectorx, 2) + (double)Math.pow(vectory, 2));
		arr[0] = (double)vectorx / length;
		arr[1] = (double)vectory / length; 
		//System.out.println(arr[0] + "x");
		//System.out.println(arr[1] + "y");
		return arr;
	}
	
	public static double calculateAngle(double vectorx, double vectory) {
		double angle = Math.toDegrees(Math.acos(vectorx));
		if(vectory > 0) angle = 360 - angle; 
		return angle;
	}
	
	public static boolean isLocationAllowed(double arr[]) {
		boolean isAllowed = true;
		if(Math.abs(GamCalc.x - (int)arr[0]) < 100) isAllowed = false;
		if(Math.abs(GamCalc.y - (int)arr[1]) < 100) isAllowed = false; 
		return isAllowed;
	}
	
	public static int[] generateEnemyLocation() {
		int finalLocation[] = new int[2];
		double tempLocation[] = new double[2];
		
		boolean isAllowed = false;
		while(!isAllowed) {
			tempLocation[0] = Math.random() * 750;
			tempLocation[1] = Math.random() * 750;
			
			if(isLocationAllowed(tempLocation)) isAllowed = true;
		}
		finalLocation[0] = (int)tempLocation[0];
		finalLocation[1] = (int)tempLocation[1];
		
		return finalLocation;
	}
	
	public static double[] calculateSlideForce(double vector[]) {
		double[] slideForce = new double[4];
		slideForce[0] = vector[0] * 50;
		slideForce[1] = vector[1] * 50;
		slideForce[2] = slideForce[0] / 10;
		slideForce[3] = slideForce[1] / 10;
		return slideForce; 
	}
	
}
