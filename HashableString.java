package src;

public class HashableString implements Hashable {

    private final String s;

    public HashableString(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public int key() {
        int h = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            h = 31 * h + aChar;
        }

        if (h < 0) h &= 0x7FFFFFFF;

        return h;
    }
}
