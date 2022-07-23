package td;

// https://techiedelight.com/practice/?problem=InterleavingStringII

public class IsInterleaving {
  public static boolean isInterleaving(String X, String Y, String S) {
    if (S.length() != X.length() + Y.length()) return false;
    return isInterleaving(X, Y, S, 0, 0, 0);
  }

  private static boolean isInterleaving(
      String X, String Y, String S, int startX, int startY, int startS) {
    int ix = startX, iy = startY, is = startS;
    while (ix < X.length() || iy < Y.length()) {
      if (ix < X.length()
          && X.charAt(ix) != S.charAt(is)
          && iy < Y.length()
          && Y.charAt(iy) != S.charAt(is)) return false;
      if ((ix < X.length() && iy < Y.length() && X.charAt(ix) != Y.charAt(iy))
          || (ix == X.length() || iy == Y.length())) {
        if (ix < X.length() && X.charAt(ix) == S.charAt(is)) {
          ++ix;
          ++is;
        } else if (iy < Y.length() && Y.charAt(iy) == S.charAt(is)) {
          ++iy;
          ++is;
        } else {
          return false;
        }
      } else {
        return isInterleaving(X, Y, S, ix + 1, iy, is + 1)
            || isInterleaving(X, Y, S, ix, iy + 1, is + 1);
      }
    }
    return ix == X.length() && iy == Y.length();
  }

  public static void main(String[] args) {
    System.out.println(isInterleaving("AB", "CD", "ACDB"));
    System.out.println(isInterleaving("ABC", "ACD", "ACDABC"));
  }
}
