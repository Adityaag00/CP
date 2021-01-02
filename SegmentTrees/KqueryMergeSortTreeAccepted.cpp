#include <iostream>
#include <vector>
#include <bits/stdc++.h>

using namespace std;
vector<int> seg[4 * 30000];
int a[30000];

void merge(vector<int> &a, vector<int> &b, vector<int> &tmp)
{
   int i = 0, j = 0;
   while (i < a.size() && j < b.size())
   {
      if (a[i] <= b[j])
      {
         tmp.push_back(a[i]);
         i++;
      }
      else
      {
         tmp.push_back(b[j]);
         j++;
      }
   }
   while (i < a.size())
   {
      tmp.push_back(a[i++]);
   }
   while (j < b.size())
   {
      tmp.push_back(b[j++]);
   }
}

int query(int cur, int l, int r, int x, int y, int k)
{
   if (r < x || l > y)
   {
      return 0;
   }
   if (x <= l && r <= y)
   {
      return seg[cur].end() - upper_bound(seg[cur].begin(), seg[cur].end(), k);
   }
   int mid = l + (r - l) / 2;
   return query(2 * cur + 1, l, mid, x, y, k) + query(2 * cur + 2, mid + 1, r, x, y, k);
}

void build(int ss, int se, int idx)
{
   if (ss == se)
   {
      seg[idx].push_back(a[ss]);
      return;
   }
   int mid = (ss + se) / 2;
   build(ss, mid, idx * 2 + 1);
   build(mid + 1, se, idx * 2 + 2);
   merge(seg[idx * 2 + 1], seg[idx * 2 + 2], seg[idx]);
}

int main(int argc, char const *argv[])
{
   int n;
   cin >> n;
   for (int i = 0; i < n; i++)
   {
      cin >> a[i];
   }
   int q;
   cin >> q;
   build(0, n - 1, 0);
   while (q--)
   {
      int i, j, k;
      cin >> i >> j >> k;
      cout << query(0, 0, n - 1, i - 1, j - 1, k) << endl;
   }
   return 0;
}
