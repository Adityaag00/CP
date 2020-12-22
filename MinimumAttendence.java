import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class MinimumAttendence {
   public static void main(String[] args) {
      FastScanner sc = new FastScanner();
      int t = sc.nextInt();

      while (t-- > 0) {
         int n = sc.nextInt();
         String bin = sc.next();
         int zer = 0, one = 0;

         for (char ch : bin.toCharArray()) {
            if (ch == '0') {
               zer++;
            } else {
               one++;
            }
         }
         double tmp = (120 - n) + one;
         double at = tmp / 120;
         if (at >= 0.75) {
            System.out.println("YES");
         } else
            System.out.println("NO");
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
