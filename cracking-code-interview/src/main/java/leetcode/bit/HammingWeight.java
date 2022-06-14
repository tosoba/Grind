package leetcode.bit;

public class HammingWeight {
    private boolean getBit(int n, int i) {
        return (n & (1 << i)) != 0;
    }

    public int hammingWeight(int n) {
        int ans = 0;
        for (var i = 0; i < 32; ++i) {
            if (getBit(n, i)) ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new HammingWeight().hammingWeight(128));
    }
}
