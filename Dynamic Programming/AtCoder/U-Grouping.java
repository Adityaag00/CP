import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AtCoderGrouping {
   static long mod = 1000000007;
   static int inf=Integer.MAX_VALUE;

   public static void main(String[] args) throws Exception {
	  FastScanner in = new FastScanner();
	  PrintWriter out = new PrintWriter(System.out);

     int n = in.nextInt();

     int a[][] = new int[n][n];
     for (int i = 0; i < n; i++) {
        for(int j=0;j<n;j++) {
        	a[i][j]=in.nextInt();
        }
     }
     for(int i=0;i<(1<<16);i++) {
    	 sum[i]=0;
    	 dp[i]=inf;
     }
         
     preCompute(a, n);
     System.out.println(group(a,(1<<n)-1));
      out.close();
   }
   
   static long sum[] = new long[1<<16];
   static long dp[] = new long[1<<16];
   
   static void  preCompute(int a[][],int n) {
	   for(int i=1;i<(1<<n);i++) {
		   sum[i]=calc(a,i);
	   }
   }
   
   static long calc(int a[][],int x) {
	   long ans=0;
	   
	   for(int i=0;i<=16;i++) {
		   for(int j=i+1;j<=16;j++) {
			   if((x&(1<<i))!=0 && (x&(1<<j))!=0) {
				   ans+=a[i][j];
			   }
		   }
	   }
	   return ans;
   }
   
   static long group(int a[][],int set) {
	   if(dp[set]!=inf) {
		   return dp[set];
	   }
	   if(set==0)
		   return 0;
	   
	   long max=0;
	   for(int mask=set;mask!=0;mask=((mask-1)&set)) {
		   max=Math.max(max, sum[mask]+group(a,mask^set));
	   }
	   return dp[set]=max;
   }

   public static long gcd(long x, long y) {
      if (x % y == 0)
         return y;
      else
         return gcd(y, x % y);
   }

   public static long pow(long n, long p, long m) {
      long result = 1;
      if (p == 0)
         return 1;
      if (p == 1)
         return n;
      while (p != 0) {
         if (p % 2 == 1)
            result *= n;
         if (result >= m)
            result %= m;
         p >>= 1;
         n *= n;
         if (n >= m)
            n %= m;
      }
      return result;
   }

   static class Pair implements Comparable<Pair> {
      int a, b;

      Pair(int a, int b) {
         this.a = a;
         this.b = b;
      }

      public int compareTo(Pair o) {
         if (this.a != o.a)
            return Integer.compare(this.a, o.a);
         else
            return Integer.compare(this.b, o.b);
         // return 0;
      }

      public boolean equals(Object o) {
         if (o instanceof Pair) {
            Pair p = (Pair) o;
            return p.a == a && p.b == b;
         }
         return false;
      }

      public int hashCode() {
         return new Integer(a).hashCode() * 31 + new Integer(b).hashCode();
      }
   }

   public static class Debug {
		//change to System.getProperty("ONLINE_JUDGE")==null; for CodeForces
//		public static final boolean LOCAL = System.getProperty("LOCAL")!=null;
		private static <T> String ts(T t) {
			if(t==null) {
				return "null";
			}
			try {
				return ts((Iterable) t);
			}catch(ClassCastException e) {
				if(t instanceof int[]) {
					String s = Arrays.toString((int[]) t);
					return "{"+s.substring(1, s.length()-1)+"}";
				}else if(t instanceof long[]) {
					String s = Arrays.toString((long[]) t);
					return "{"+s.substring(1, s.length()-1)+"}";
				}else if(t instanceof char[]) {
					String s = Arrays.toString((char[]) t);
					return "{"+s.substring(1, s.length()-1)+"}";
				}else if(t instanceof double[]) {
					String s = Arrays.toString((double[]) t);
					return "{"+s.substring(1, s.length()-1)+"}";
				}else if(t instanceof boolean[]) {
					String s = Arrays.toString((boolean[]) t);
					return "{"+s.substring(1, s.length()-1)+"}";
				}
				try {
					return ts((Object[]) t);
				}catch(ClassCastException e1) {
					return t.toString();
				}
			}
		}
		private static <T> String ts(T[] arr) {
			StringBuilder ret = new StringBuilder();
			ret.append("{");
			boolean first = true;
			for(T t: arr) {
				if(!first) {
					ret.append(", ");
				}
				first = false;
				ret.append(ts(t));
			}
			ret.append("}");
			return ret.toString();
		}
		private static <T> String ts(Iterable<T> iter) {
			StringBuilder ret = new StringBuilder();
			ret.append("{");
			boolean first = true;
			for(T t: iter) {
				if(!first) {
					ret.append(", ");
				}
				first = false;
				ret.append(ts(t));
			}
			ret.append("}");
			return ret.toString();
		}
		public static void dbg(Object... o) {
//			if(LOCAL) {
				System.err.print("Line #"+Thread.currentThread().getStackTrace()[2].getLineNumber()+": [");
				for(int i = 0; i<o.length; i++) {
					if(i!=0) {
						System.err.print(", ");
					}
					System.err.print(ts(o[i]));
				}
				System.err.println("]");
//			}
		}
	}
   
   static class FastScanner {
      BufferedReader br;
      StringTokenizer st;

      public FastScanner() {
         br = new BufferedReader(new InputStreamReader(System.in));
      }

      String next() {
         while (st == null || !st.hasMoreElements()) {
            try {
               st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         return st.nextToken();
      }

      int nextInt() {
         return Integer.parseInt(next());
      }

      long nextLong() {
         return Long.parseLong(next());
      }

      String nextLine() throws IOException {
         String st = br.readLine();
         return st;
      }
   }
}
