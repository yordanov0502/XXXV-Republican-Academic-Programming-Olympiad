package bg.tu_varna.sit;

import java.util.*;

public class L
{
    static int[] high = new int[101];
    static int[] low = new int[101];
    static List<Pair<Integer,Integer>>[] l = new ArrayList[101];

    static class Pair<T1,T2>
    {
        T1 first;
        T2 second;

        public Pair(T1 first,T2 second)
        {
            this.first=first;
            this.second=second;
        }
    }

    public static int input()
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        for(int i = 1; i<=N;i++)
        {
            high[i] = Integer.MAX_VALUE;
            low[i] = Integer.MAX_VALUE;
            l[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++)
        {
            int U = scanner.nextInt();
            int V = scanner.nextInt();
            int W = scanner.nextInt();
            l[U].add(new Pair<>(V,W));
            l[V].add(new Pair<>(U,W));
        }
        return N;
    }

    public static void calculate(int N)
    {
        high[1]=0;
        low[1]=0;
        int A,B,C,D;
        PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p->p.first));
        pq.offer(new Pair<>(0,-1));

        while(!pq.isEmpty())
        {
            D = pq.peek().first;
            A = pq.peek().second;
            pq.poll();
            if(A > 0 && D > high[A]) continue;
            if(A < 0 && D > low[-A]) continue;

            for(Pair<Integer,Integer> i : l[Math.abs(A)])
            {
                B = i.first;
                C = i.second;

                if(B > -A && A < 0 && high[B] > low[-A] + C)
                {
                    high[B] = C + low[-A];
                    pq.offer(new Pair<>(high[B], B));
                }
                if(B < A && A > 0 && low[B] > high[A] + C)
                {
                    low[B] = C + high[A];
                    pq.offer(new Pair<>(low[B], -B));
                }
            }
        }
        System.out.println(high[N]);
    }

    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int T = scanner.nextInt();

       while (T-- > 0)
       {
           calculate(input());
       }
    }
}
