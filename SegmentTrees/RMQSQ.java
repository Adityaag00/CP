import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

class RMQSQ {

   static int[] seg = new int[4 * 100000];
   static int[] ar = new int[100000];

   public static void main(String[] args) throws IOException {
      FastScanner sc = new FastScanner();
      int n = sc.nextInt();
      ar = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      build(0, n - 1, 0);
      int q = sc.nextInt();

      while (q-- > 0) {
         int x = sc.nextInt();
         int y = sc.nextInt();
         System.out.println(query(0, n - 1, 0, x, y));
      }
   }

   static int query(int ss, int se, int ind, int qs, int qe) {
      if (qs <= ss && qe >= se) {
         return seg[ind];
      }
      if (qs > se || qe < ss || ss > se) {
         return Integer.MAX_VALUE;
      }
      int mid = (ss + se) / 2;
      return Math.min(query(ss, mid, ind * 2 + 1, qs, qe), query(mid + 1, se, ind * 2 + 2, qs, qe));
   }

   static void build(int ss, int se, int ind) {
      if (ss == se) {
         seg[ind] = ar[ss];
         return;
      }
      int mid = (ss + se) / 2;
      build(ss, mid, ind * 2 + 1);
      build(mid + 1, se, ind * 2 + 2);

      seg[ind] = Math.min(seg[ind * 2 + 1], seg[ind * 2 + 2]);
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
