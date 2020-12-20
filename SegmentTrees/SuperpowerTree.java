import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class SuperpowerTree {

   public static long[] seg = new long[4 * 100000];
   public static long[] arr = new long[100000];

   public static void main(String[] args) throws IOException {
      int n, q;
      long m;
      FastScanner sc = new FastScanner();
      n = sc.nextInt();
      q = sc.nextInt();
      m = -sc.nextLong();

      arr = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
      build(0, 0, n - 1);

      while (q-- > 0) {
         char ch = sc.next().charAt(0);
         int x = sc.nextInt();
         long y = sc.nextLong();

         if (ch == 'q') {
            System.out.println((querry(0, 0, n - 1, x, (int) y)));
         } else
            update(0, 0, n - 1, x, y);
      }
   }

   static void update(int idx, int ss, int se, int ui, long uv) {
      if (ss == se) {
         seg[idx] = uv;
         arr[ui] = uv;
         return;
      }
      int mid = (ss + se) / 2;
      if (ui > mid) {
         update(idx * 2 + 2, mid + 1, se, ui, uv);
      } else {
         update(idx * 2 + 1, ss, mid, ui, uv);
      }
      seg[idx] = (long) Math.pow(seg[idx * 2 + 1], seg[idx * 2 + 2]);
   }

   static long querry(int idx, int ss, int se, int qs, int qe) {
      if (qs <= ss && qe >= se) {
         return seg[idx];
      }
      if (qs >= se || qe <= ss || ss > se)
         return 1;
      int mid = (ss + se) / 2;
      long a = querry(idx * 2 + 1, ss, mid, qs, qe);
      long b = querry(idx * 2 + 2, mid + 1, se, qs, qe);

      return (long) Math.pow(a, b);
   }

   static long superPower(int l, int r) {
      if (l == r)
         return arr[l];
      return (long) Math.pow(superPower(l, r - 1), arr[r]);
   }

   static void build(int idx, int ss, int se) {
      if (ss == se) {
         seg[idx] = arr[ss];
         return;
      }
      int mid = (ss + se) / 2;
      build(idx * 2 + 1, ss, mid);
      build(idx * 2 + 2, mid + 1, se);
      seg[idx] = superPower(ss, se);
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

