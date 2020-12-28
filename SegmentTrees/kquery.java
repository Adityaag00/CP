import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class kquery {
   static int[] seg = new int[4 * 30000];
   static int[] a = new int[30000];
   static query[] q = new query[200000];
   static int[] ans = new int[200000];

   public static void main(String[] args) throws IOException {
      FastScanner sc = new FastScanner();
      ArrayList<pair> aa = new ArrayList<>();
      int n = sc.nextInt();
      a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int qu = sc.nextInt();
      int back = qu;
      int x = 0;
      while (qu-- > 0) {
         int i = sc.nextInt();
         int j = sc.nextInt();
         int k = sc.nextInt();
         q[x++] = new query(i, j, k, x - 1);
      }
      Arrays.sort(q, 0, x);
      int temp = 0;
      for (int i = 0; i < a.length; i++) {
         aa.add(new pair(i, a[i]));
      }
      Collections.sort(aa);
      build(0, n - 1, 0);
      temp = 0;
      x = 0;
      for (query w : q) {
         if (w == null)
            break;
         while (temp < aa.size() && w.getK() >= aa.get(temp).getVal()) {
            update(0, n - 1, 0, aa.get(temp).getInd());
            temp++;
         }
         ans[w.getInd()] = query(0, n - 1, 0, w.getI() - 1, w.getJ() - 1);
      }
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      for (int i = 0; i < back; i++) {
         bw.write("" + ans[i]);
         bw.write("\n");
         bw.flush();
      }
   }

   static void build(int ss, int se, int ind) {
      if (ss == se) {
         seg[ind] = 1;
         return;
      }
      int mid = (ss + se) / 2;
      build(ss, mid, ind * 2 + 1);
      build(mid + 1, se, ind * 2 + 2);
      seg[ind] = seg[ind * 2 + 1] + seg[ind * 2 + 2];
   }

   static int query(int ss, int se, int ind, int qs, int qe) {
      if (qs <= ss && qe >= se) {
         return seg[ind];
      }
      if (se < qs || ss > qe)
         return 0;
      int mid = (ss + se) / 2;
      int q1 = query(ss, mid, ind * 2 + 1, qs, qe);
      int q2 = query(mid + 1, se, ind * 2 + 2, qs, qe);
      return q1 + q2;
   }

   static void update(int ss, int se, int ind, int qs) {
      if (ss == se) {
         seg[ind] = 0;
         return;
      }
      int mid = (ss + se) / 2;

      if (qs <= mid)
         update(ss, mid, ind * 2 + 1, qs);
      else
         update(mid + 1, se, ind * 2 + 2, qs);
      seg[ind] = seg[ind * 2 + 1] + seg[ind * 2 + 2];
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

class pair2 implements Comparable<pair2> {
   int ind, ans;

   public pair2(int ind, int ans) {
      this.ind = ind;
      this.ans = ans;
   }

   @Override
   public int compareTo(pair2 o) {
      return ind - o.ind;
   }
}

class query implements Comparable<query> {
   private int i;
   private int k;
   private int j;
   private int ind;

   query(int i, int j, int k, int ind) {
      this.i = i;
      this.j = j;
      this.k = k;
      this.ind = ind;
   }

   @Override
   public int compareTo(query o) {
      return this.k - o.k;
   }

   public int getI() {
      return i;
   }

   public void setI(int i) {
      this.i = i;
   }

   public int getK() {
      return k;
   }

   public void setK(int k) {
      this.k = k;
   }

   public int getJ() {
      return j;
   }

   public void setJ(int j) {
      this.j = j;
   }

   public int getInd() {
      return ind;
   }

   public void setInd(int ind) {
      this.ind = ind;
   }
}

class pair implements Comparable<pair> {
   private int ind;
   private int val;

   public pair(int ind, int val) {
      this.ind = ind;
      this.val = val;
   }

   public int getInd() {
      return ind;
   }

   public void setInd(int ind) {
      this.ind = ind;
   }

   public int getVal() {
      return val;
   }

   public void setVal(int val) {
      this.val = val;
   }

   @Override
   public int compareTo(pair o) {
      return val - o.val;
   }

   public String toString() {
      return ind + " " + val;
   }
}
