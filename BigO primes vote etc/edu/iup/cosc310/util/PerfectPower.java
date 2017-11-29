package edu.iup.cosc310.util;

/**
 * @author dtsmith
 *
 * A utlity class to calculate the perfect power of an integer
 */
public class PerfectPower {
    public static void main(String[] args) {
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 17 is " + PerfectPower(17));
			}
		}, "Get Pth of 17", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 625 is " + PerfectPower(625));
			}
		}, "Get Pth of 625", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 1024 is " + PerfectPower(1024));
			}
		}, "Get Pth of 1024", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 10000 is " + PerfectPower(10000));
			}
		}, "Get Pth of 10000", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
			       System.out.println("Pth of 1073741824 is " + PerfectPower(1073741824));
			}
		}, "Get Pth of 1073741824", System.out).start();	
    }

    /**
     * Get the perfect power for a number.
     * @param x number for which to calculate the perfect power.
     */
    public static int PerfectPower(int x) {
        int largestP = 1;
                
        for (int p = 1; p < x; p++) {
            for (int b = 1; b < x; b++) {
                if (Math.pow(b,p) == x) {
                    largestP = p;
                }
           }           
        }
        
        return largestP;
    }

}
