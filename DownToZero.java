package org.hackerrank.day7;

import java.util.*;

public class DownToZero {
    static int[] memo;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int q=sc.nextInt();
//        for(int i=0;i<q;i++){
//            int n=sc.nextInt();
//            memo = new int[n + 1];
//            Arrays.fill(memo, -1);
//            int res=topDown(n);
//            System.out.println(res);
//        }
        int[] query=new int[q];
        int maxN=0;
        for(int i=0;i<q;i++){
            query[i]=sc.nextInt();
            maxN=Math.max(maxN, query[i]);
        }
        int[] res=bottomUp(maxN);

        for (int i = 0; i < q; i++) {
            System.out.println(res[query[i]]);
        }
    }
    static int topDown(int n){
        if(n==0) return 0;
        if(memo[n]!=-1) return memo[n];
        int res=topDown(n-1)+1;
        for(int a=2;a*a<=n;a++){
            if(n%a==0){
                int b=n/a;
                int step=Math.max(a,b);
                res=Math.min(res, topDown(step)+1);
            }
        }
        memo[n]=res;
        return res;
    }

    static int[] bottomUp(int n){
        int[] dp=new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<=n;i++){
            dp[i]=dp[i-1]+1;
            for (int a = 2; a * a <= i; a++) {
                if (i % a == 0) {
                    int b = i / a;
                    if (a > 1 && b > 1) {
                        int step=Math.max(a,b);
                        dp[i] = Math.min(dp[i], dp[step] + 1);
                    }
                }
            }
        }
        return dp;
    }


    static class Pair{
        int first;
        int second;

        public Pair(int a,int b){
            first = a;
            second = b;
        }
    }

    public static int downToZero(int n) {
        // Write your code here
        Queue<Pair> q = new LinkedList<>();
        Set<Integer> vis = new HashSet<>();

        q.add(new Pair(n, 0));
        vis.add(n);

        while(!q.isEmpty()){
            Pair curr = q.poll();
            int num = curr.first;
            int moves = curr.second;

            if(num == 0) return moves;

            //first operation -- decrement ke liye
            if(!vis.contains(num-1)){
                q.add(new Pair(num-1, moves+1));
                vis.add(num-1);
            }

            //second operation -- factor ke liye
            for(int i=2 ; i*i <= num ; i++){
                if(num % i == 0){
                    int a = i;
                    int b = num/i;

                    int maxAB = Math.max(a,b); //max factor

                    if(!vis.contains(maxAB)){
                        q.add(new Pair(maxAB, moves+1));
                        vis.add(maxAB);
                    }
                }
            }
        }

        return -1;

    }
}