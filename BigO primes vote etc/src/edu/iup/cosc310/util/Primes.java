package edu.iup.cosc310.util;

/**
 * @author David Smith
 * 
 * Utility class to create an array of primes
 */
public class Primes {

	/**
	 * Get a set of prime numbers.
	 * @param no the number of primes to create
	 * @return an array containing the requested number
	 * of primes
	 */
	public static int[] getPrimes(int no) {
		int[] primes = new int[no];
		int[] primesSqr = new int[no];
		primes[0] = 2;
		primesSqr[0] = 4;
		int noPrimes = 1;
		int prime = 1;
		
		
		boolean isPrime = false;
		while ((prime += 2) <= no) {
			isPrime = true;
			for (int i = 0; i < noPrimes && primesSqr[i] <= prime ; i++) {
				if (prime % primes[i] == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primesSqr[noPrimes] = prime * prime;
				primes[noPrimes++] = prime;
			}
		}
		
		return primes;
	}
/**
 * Main class to print up to 1,000,000 prime numbers.
 * @param args
 */
	public static void main(String[] args) {
		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(1000);
			}
		}, "Get 1,000 primes", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(10000);
			}
		}, "Get 10,000 primes", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(100000);
			}
		}, "Get 100,000 primes", System.out).start();
		
		new TimeExec(new Runnable() {
			public void run() {
				int[] primes = getPrimes(1000000);
			}
		}, "Get 1,000,000 primes", System.out).start();
	}
}