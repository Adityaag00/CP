import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class SLIS {
   public static Num[] seg = new Num[4 * 100000];
   public static int[] lazy = new int[4 * 100000];
   public static int[] arr = new int[100000];

   public static void main(String[] args) throws IOException {
      FastScanner sc = new FastScanner();
      int n = sc.nextInt();
      int q = sc.nextInt();

      arr = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
      build(0, 0, n - 1);

      while (q-- > 0) {
         int x = sc.nextInt();
         if (x == 0) {
            int y = sc.nextInt();
            int z = sc.nextInt();
            update(0, 0, n - 1, y - 1, z - 1);
         } else {
            Num k = querry(0, 0, n - 1, 0, n - 1);
            System.out.println(Math.max(k.one, k.zero));
         }
      }
   }

   static void build(int idx, int ss, int se) {
      if (ss == se) {
         if (arr[ss] == 0) {
            seg[idx] = new Num(0, 1);
         } else {
            seg[idx] = new Num(1, 0);
         }
         return;
      }
      int mid = (ss + se) / 2;
      build(idx * 2 + 1, ss, mid);
      build(idx * 2 + 2, mid + 1, se);
      seg[idx] = new Num(seg[idx * 2 + 1].one + seg[idx * 2 + 2].one, seg[idx * 2 + 1].zero + seg[idx * 2 + 2].zero);
   }

   static void update(int idx, int ss, int se, int us, int ue) {
      if (lazy[idx] != 0) {
         for (int i = 0; i < lazy[idx]; i++) {
            int temp = seg[idx].one;
            seg[idx].one = seg[idx].zero;
            seg[idx].zero = temp;
         }
         if (ss != se) {
            lazy[idx * 2 + 1] += 1;
            lazy[idx * 2 + 2] += 1;
         }
         lazy[idx] = 0;
      }

      if (ss > se || ss > ue || se < us)
         return;

      if (ss >= us && se <= ue) {
         int temp = seg[idx].one;
         seg[idx].one = seg[idx].zero;
         seg[idx].zero = temp;

         if (ss != se) {
            lazy[idx * 2 + 1] += 1;
            lazy[idx * 2 + 2] += 1;
         }
         return;
      }
      int mid = (ss + se) / 2;
      update(idx * 2 + 1, ss, mid, us, ue);
      update(idx * 2 + 2, mid + 1, se, us, ue);
      seg[idx] = new Num(seg[idx * 2 + 1].one + seg[idx * 2 + 2].one, seg[idx * 2 + 1].zero + seg[idx * 2 + 2].zero);
   }

   static Num querry(int idx, int ss, int se, int qs, int qe) {
      if (lazy[idx] != 0) {
         for (int i = 0; i < lazy[idx]; i++) {
            int temp = seg[idx].one;
            seg[idx].one = seg[idx].zero;
            seg[idx].zero = temp;
         }
         if (ss != se) {
            lazy[idx * 2 + 1] += 1;
            lazy[idx * 2 + 2] += 1;
         }
         lazy[idx] = 0;
      }

      if (qs <= ss && qe >= se) {
         return seg[idx];
      }
      if (qs > se || qe < ss || ss > se)
         return new Num(0, 0);

      int mid = (ss + se) / 2;
      Num n1 = querry(idx * 2 + 1, ss, mid, qs, qe);
      Num n2 = querry(idx * 2 + 2, mid + 1, se, qs, qe);
      return new Num(n1.one + n2.one, n1.zero + n2.zero);
   }

   static class Num {
      int one = 0;
      int zero = 0;

      Num(int one, int zero) {
         this.one = one;
         this.zero = zero;
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


