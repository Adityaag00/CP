import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main {
   @SuppressWarnings("unchecked")
   static ArrayList<Integer> seg[] = new ArrayList[4 * 30000];
   static int[] a = new int[30000];

   public static void main(String[] args) throws IOException {
      FastScanner sc = new FastScanner();
      int n = sc.nextInt();
      a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int qu = sc.nextInt();
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      build(0, n - 1, 0);
      while (qu-- > 0) {
         int i = sc.nextInt();
         int j = sc.nextInt();
         int k = sc.nextInt();
         bw.write("" + query(0, n - 1, 0, i - 1, j - 1, k) + "\n");
         bw.flush();
      }
   }

   static void build(int ss, int se, int idx) {
      if (ss == se) {
         ArrayList<Integer> tmp = new ArrayList<>(1);
         tmp.add(a[ss]);
         seg[idx] = tmp;
         return;
      }
      int mid = (ss + se) / 2;
      build(ss, mid, idx * 2 + 1);
      build(mid + 1, se, idx * 2 + 2);
      seg[idx] = merge(seg[idx * 2 + 1], seg[idx * 2 + 2]);
   }

   static int query(int ss, int se, int idx, int qs, int qe, int k) {
      if (qs <= ss && qe >= se) {
         ArrayList<Integer> tmp = seg[idx];
         if (tmp.size() == 1) {
            if (tmp.get(0) > k)
               return 1;
            else
               return 0;
         }
         int left = tmp.size();
         int l = 0;
         int h = tmp.size() - 1;
         while (l <= h) {
            int mid = (l + h) / 2;
            if (tmp.get(mid) > k) {
               left = mid;
               h = mid - 1;
            } else
               l = mid + 1;
         }
         return tmp.size() - left;
      }
      if (qs > se || qe < ss || se < ss) {
         return 0;
      }
      int mid = (ss + se) / 2;
      return query(ss, mid, idx * 2 + 1, qs, qe, k) + query(mid + 1, se, idx * 2 + 2, qs, qe, k);
   }

   static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b) {
      ArrayList<Integer> res = new ArrayList<>(a.size() + b.size());
      int i = 0, j = 0;

      while (i < a.size() && j < b.size()) {
         if (a.get(i) <= b.get(j)) {
            res.add(a.get(i));
            i++;
         } else {
            res.add(b.get(j));
            j++;
         }
      }
      while (i < a.size()) {
         res.add(a.get(i++));
      }
      while (j < b.size()) {
         res.add(b.get(j++));
      }
      return res;
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