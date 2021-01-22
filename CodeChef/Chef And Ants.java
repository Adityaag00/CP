import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Main {
   public static void main(String[] args) {
      FastScanner sc = new FastScanner();
      int t = sc.nextInt();

      while (t-- > 0) {
         int n = sc.nextInt();
         HashMap<Integer, Integer> mp = new HashMap<>();
         ArrayList<ArrayList<Integer>> ar = new ArrayList<>();
         for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < m; j++) {
               int k = sc.nextInt();
               tmp.add(k);
               if (mp.containsKey(Math.abs(k))) {
                  mp.put(Math.abs(k), mp.get(Math.abs(k)) + 1);
               } else {
                  mp.put(Math.abs(k), 1);
               }
            }
            ar.add(tmp);
         }
         long ans = 0;
         for (Entry<Integer, Integer> x : mp.entrySet()) {
            if (x.getValue() > 1) {
               ans += 1;
            }
         }
         for (ArrayList<Integer> a : ar) {
            ArrayList<Integer> neg = new ArrayList<>();
            ArrayList<Integer> pos = new ArrayList<>();
            for (int x : a) {
               if (x < 0)
                  neg.add(x);
               else
                  pos.add(x);
            }
            Collections.reverse(pos);
            int closest = 0;
            while (true) {
               if (neg.isEmpty() && pos.isEmpty())
                  break;
               else if (neg.isEmpty() && !pos.isEmpty())
                  closest = pos.get(pos.size() - 1);
               else if (!neg.isEmpty() && pos.isEmpty())
                  closest = neg.get(neg.size() - 1);
               else {
                  if (Math.abs(neg.get(neg.size() - 1)) < Math.abs(pos.get(pos.size() - 1))) {
                     closest = neg.get(neg.size() - 1);
                  } else
                     closest = pos.get(pos.size() - 1);
               }
               // if (mp.get(Math.abs(closest)) > 1) {
               // if (closest >= 0)
               // ans += pos.size() - 1;
               // else
               // ans += neg.size() - 1;
               // } else {
               // if (closest >= 0)
               // ans += neg.size();
               // else
               // ans += pos.size();
               // }

               if (closest > 0) {
                  if (mp.get(Math.abs(closest)) > 1) {
                     ans += pos.size() - 1;
                  } else {
                     ans += neg.size();
                  }
               } else {
                  if (mp.get(Math.abs(closest)) > 1) {
                     ans += neg.size() - 1;
                  } else {
                     ans += pos.size();
                  }
               }
               if (closest >= 0)
                  pos.remove(pos.size() - 1);
               else
                  neg.remove(neg.size() - 1);
            }
         }
         System.out.println(ans);
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
