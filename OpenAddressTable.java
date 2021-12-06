package src;


public interface OpenAddressTable<V extends Hashable> extends Dictionary<V> {
    int hash(int key, int probenumber);
}
