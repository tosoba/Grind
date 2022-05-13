package leetcode.dnc;

public class FirstBadVersion {
    static boolean isBadVersion(int version) {
        return version >= 1702766719;
    }

    static int start = 1;

    public static int firstBadVersion(int n) {
        long mid = ((long) n + (long) start) / 2;
        boolean isBad = isBadVersion((int) mid);
        if (isBad) {
            if (!isBadVersion((int) mid - 1)) return (int) mid;
            return firstBadVersion((int) mid - 2);
        } else {
            if (isBadVersion((int) mid + 1)) return (int) mid + 1;
            start = (int) mid + 1;
            return firstBadVersion(n);
        }
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(2126753390));
    }
}
