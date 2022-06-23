package elements_of_programming_interviews;

public class PrimitiveTypes {
  public static short countBits(int x) {
    short numBits = 0;
    while (x != 0) {
      numBits += (x & 1);
      x >>>= 1;
    }
    return numBits;
  }

  public static short parity(int x) { // O(n)
    short numBits = 0;
    while (x != 0) {
      numBits ^= (x & 1);
      x >>>= 1;
    }
    return numBits;
  }

  public static short parity(long x) { // O(k), k - number of bits = 1
    short result = 0;
    while (x != 0) {
      result ^= 1;
      x &= (x - 1); // Drops the lowest set bit of x.
    }
    return result;
  }

  public static void main(String[] args) {}
}
