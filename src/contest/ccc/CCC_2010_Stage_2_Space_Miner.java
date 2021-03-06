package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2010_Stage_2_Space_Miner {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Planet[] p = new Planet[n];
    for (int x = 0; x < n; x++) {
      p[x] = new Planet(readDouble(), readDouble(), readDouble(), readDouble(), readDouble());
    }
    int m = readInt();
    Point[] path = new Point[m];
    for (int x = 0; x < m; x++)
      path[x] = new Point(readDouble(), readDouble(), readDouble());
    double minD = readDouble();
    int total = 0;
    for (int i = 0; i < m - 1; i++) {
      Point d = new Point(path[i + 1].x - path[i].x, path[i + 1].y - path[i].y, path[i + 1].z - path[i].z);
      for (int j = 0; j < n; j++) {
        if (p[j].collected)
          continue;
        Point o = new Point(p[j].p.x - path[i].x, p[j].p.y - path[i].y, p[j].p.z - path[i].z);
        double a = (d.x * d.x) + (d.y * d.y) + (d.z * d.z);
        double b = -(2 * o.x * d.x + 2 * o.y * d.y + 2 * o.z * d.z);
        double c = (o.x * o.x) + (o.y * o.y) + (o.z * o.z);
        double x1 = -b / (2 * a);
        if (x1 < 0 || x1 > 1) {
          double dx1 = p[j].p.x - path[i].x;
          double dy1 = p[j].p.y - path[i].y;
          double dz1 = p[j].p.z - path[i].z;
          double dx2 = p[j].p.x - path[i + 1].x;
          double dy2 = p[j].p.y - path[i + 1].y;
          double dz2 = p[j].p.z - path[i + 1].z;
          double dist1 = Math.sqrt(dx1 * dx1 + dy1 * dy1 + dz1 * dz1);
          double dist2 = Math.sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2);
          if (dist1 <= minD + p[j].r || dist2 <= minD + p[j].r) {
            total += (int)p[j].v;
            p[j].collected = true;
          }
        } else {
          double dist = Math.sqrt(a * x1 * x1 + b * x1 + c);
          if (dist <= minD + p[j].r) {
            total += (int)p[j].v;
            p[j].collected = true;
          }
        }
      }
    }
    System.out.println(total);
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Planet {
    Point p;
    double v, r;
    boolean collected;

    Planet(double x, double y, double z, double v, double r) {
      collected = false;
      p = new Point(x, y, z);
      this.v = v;
      this.r = r;
    }
  }

  static class Point {
    double x, y, z;

    Point(double x, double y, double z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }
}
