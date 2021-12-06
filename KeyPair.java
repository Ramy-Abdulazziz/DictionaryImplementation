package src;

public class KeyPair<V> {

    private final V value;
    private final int key;

    public KeyPair(V value, int key) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public int getKey() {
        return key;
    }
}
