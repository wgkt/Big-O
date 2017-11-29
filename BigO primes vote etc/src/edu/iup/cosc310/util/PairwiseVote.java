package edu.iup.cosc310.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author David Smith
 * 
 *         Utility class to compute the pairwise winner of elections
 */
public class PairwiseVote {
	/**
	 * Get the placement order of a candidate in a rank ordered list of
	 * candidates
	 *
	 * @param votersVotes
	 *            an array of rank ordered candidates. First has the highest
	 *            rank.
	 */
	public static int getPlacement(int candidate, int[] votersVotes) {
		return votersVotes[candidate];
	}

	/**
	 * Get the candidate winner from a set of rank ordered ballots
	 *
	 * @param votes
	 *            - a two dimensional array, first dimension is the voter,
	 *            second dimension is the rank order ballot of candidates for
	 *            the given voter
	 */
	public static int getPairwiseWinner(int[][] votes) {
		int noVoters = votes.length;

		if (noVoters == 0) {
			return -1;
		}

		int noCandidates = votes[0].length;

		if (noCandidates == 0) {
			return -1;
		}

		// Compare every pair of candidates

		int noTimesCandidate1Wins = 0;
		int candidate1 = 0;
		for (int candidate2 = 1; candidate2 < noCandidates; candidate2++) {

			// Determine count the ballots for each candidate
			int candidate1votes = 0;
			int candidate2votes = 0;
			for (int voter = 0; voter < noVoters; voter++) {
				int placement1 = getPlacement(candidate1, votes[voter]);
				int placement2 = getPlacement(candidate2, votes[voter]);
				if (placement1 < placement2) {
					candidate1votes++;
					;
				} else {
					candidate2votes++;
					;
				}
			}

			if (candidate1votes < candidate2votes) {
				candidate1 = candidate2;
			}
		}
		// tests current "winner" against the candidates it has not tested
		// against yet
		for (int candidate2 = 0; candidate2 < noCandidates; candidate2++) {
			if (candidate1 == candidate2) {
				continue;
			}
		
			int candidate1votes = 0;
			int candidate2votes = 0;
			for (int voter = 0; voter < noVoters; voter++) {
				int placement1 = getPlacement(candidate1, votes[voter]);
				int placement2 = getPlacement(candidate2, votes[voter]);
				if (placement1 < placement2) {
					candidate1votes++;
					;
				} else {
					candidate2votes++;
					;
				}

			}
			if (candidate1votes < candidate2votes) {
				return -1;
			}
		}
		// No winner
		return candidate1;
	}

	static int electionNo = 0;

	/**
	 * Main - reads several test elections using the text file votes.txt. Each
	 * election begins with two number, the number of voters and the number of
	 * candidates, all followed by the ballots of each voter.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int noVoters;
		int noCandidates;

		Scanner in = new Scanner(new FileInputStream("votes.txt"));

		// read ballots for each election
		for (;;) {
			noVoters = in.nextInt();
			noCandidates = in.nextInt();

			if (noVoters == 0 && noCandidates == 0) {
				break;
			}

			final int[][] votes = new int[noVoters][noCandidates];

			// Read the ballots
			for (int i = 0; i < noVoters; i++) {
				for (int j = 0; j < noCandidates; j++) {
					int k = in.nextInt();
					votes[i][k] = j;
				}
			}

			new TimeExec(new Runnable() {
				public void run() {
					int winner = getPairwiseWinner(votes);
					if (winner >= 0) {
						System.out.printf("Winner of election %d is candidate %d\n", electionNo, winner);
					} else {
						System.out.printf("No winner for election %d\n", electionNo);
					}
				}
			}, "Election " + ++electionNo, System.out).start();
		}
		in.close();
	}
}