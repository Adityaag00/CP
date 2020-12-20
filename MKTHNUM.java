import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

   @SuppressWarnings("unchecked")
   static ArrayList<Long> seg[] = new ArrayList[4 * 100000];
   static long a[] = new long[100000];

   public static void main(String[] args) throws IOException {
      FastScanner sc = new FastScanner();
      int n = sc.nextInt();
      int m = sc.nextInt();
      a = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
      build(0, n - 1, 0);

      // for (int i = 0; i <4*n; i++) {
      // for (int j = 0; j < seg[i].size(); j++)
      // System.out.print(" " + seg[i].get(j));
      // System.out.println();

      // }
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      while (m-- > 0) {
         int i = sc.nextInt();
         int j = sc.nextInt();
         int k = sc.nextInt();
         i--;
         j--;
         ArrayList<Long> tmp = query(0, n - 1, i, j, 0);
         // for (int l = 0; l < tmp.size(); l++) {
         // System.out.println(tmp.get(l));
         // }
         bw.write("" + tmp.get(--k) + "\n");
         bw.flush();
      }
   }

   static ArrayList<Long> query(int ss, int se, int qs, int qe, int idx) {
      if (qs <= ss && qe >= se) {
         // for(int x=0;x<seg[idx].size();x++) {
         // System.out.println(seg[idx].get(x));
         // }
         return seg[idx];
      }
      if (qs > se || qe < ss || ss > se) {
         ArrayList<Long> tmp = new ArrayList<>(1);
         tmp.add((long) -1);
         return tmp;
      }
      int mid = (ss + se) / 2;
      ArrayList<Long> tmp = new ArrayList<>();
      ArrayList<Long> a = query(ss, mid, qs, qe, idx * 2 + 1);
      ArrayList<Long> b = query(mid + 1, se, qs, qe, idx * 2 + 2);
      // for (Long long1 : b) {
      // System.out.print(" " + long1);
      // }
      // for (Long long1 : a) {
      // System.out.print(" " + long1);
      // }
      // System.out.println();
      if (a.contains((long) -1) || b.contains((long) -1)) {
         if (!a.contains((long) -1))
            tmp.addAll(a);
         else if (!b.contains((long) -1))
            tmp.addAll(b);
      } else {
         tmp.addAll(merge(a, b));
      }
      return tmp;
   }

   static ArrayList<Long> merge(ArrayList<Long> a, ArrayList<Long> b) {
      ArrayList<Long> res = new ArrayList<>();
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

   static void build(int ss, int se, int idx) {
      if (ss == se) {
         ArrayList<Long> tmp = new ArrayList<>(1);
         tmp.add(a[ss]);
         seg[idx] = tmp;
         return;
      }
      int mid = (ss + se) / 2;
      build(ss, mid, idx * 2 + 1);
      build(mid + 1, se, idx * 2 + 2);
      seg[idx] = merge(seg[idx * 2 + 1], seg[idx * 2 + 2]);
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