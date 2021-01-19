package main.java.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumJobDifficulty {
    public int minDifficulty(int[] A, int D) {
        int n = A.length;
        if (n < D) return -1;
        int[] dp = new int[n], dp2 = new int[n], tmp;
        Arrays.fill(dp, 1000);
        Deque<Integer> stack = new ArrayDeque<Integer>();

        for (int d = 0; d < D; ++d) {
            stack.clear();
            for (int i = d; i < n; i++) {
                dp2[i] = i > 0 ? dp[i - 1] + A[i] : A[i];
                while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                    int j = stack.pop();
                    dp2[i] = Math.min(dp2[i], dp2[j] - A[j] + A[i]);
                }
                if (!stack.isEmpty()) {
                    dp2[i] = Math.min(dp2[i], dp2[stack.peek()]);
                }
                stack.push(i);
            }
            tmp = dp;
            dp = dp2;
            dp2 = tmp;
        }
        return dp[n - 1];
    }
    public int minDifficulty2(int[] jobDifficulty, int D) {
        final int N = jobDifficulty.length;
        if(N < D) return -1;
        int[][] dp = new int[D][N];

        dp[0][0] = jobDifficulty[0];
        for(int j = 1; j < N; ++j){
            dp[0][j] = Math.max(jobDifficulty[j], dp[0][j - 1]);
        }

        for(int d = 1; d < D; ++d){
            for(int len = d; len < N; ++len){
                int localMax = jobDifficulty[len];
                dp[d][len] = Integer.MAX_VALUE;
                for(int schedule = len; schedule >= d; --schedule){
                    localMax = Math.max(localMax, jobDifficulty[schedule]);
                    dp[d][len] = Math.min(dp[d][len], dp[d - 1][schedule - 1] + localMax);
                }
            }
        }

        return dp[D - 1][N - 1];
    }

    public static void main(String[] args) {
        MinimumJobDifficulty minimumJobDifficulty = new MinimumJobDifficulty();
        System.out.println(minimumJobDifficulty.minDifficulty2(new int[]{6,5,4,3,2,1},2));

    }

}
