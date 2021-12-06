package src;

import java.util.function.Function;

public class DoubleHashTable<V extends Hashable> implements OpenAddressTable<V> {

    private final Function h1;
    private final Function h2;
    private KeyPair <?> [] keyPairs;
    private int size;
    private int nElems = 0;

    public DoubleHashTable(int size, Function h1, Function h2) {

        this.h1 = h1;
        this.h2 = h2;
        this.size = size;

        initKeyPairs();
    }

    public void initKeyPairs() {

        keyPairs = new KeyPair[size];

        for (int i = 0; i < keyPairs.length; i++) {

            keyPairs[i] = new KeyPair<>(null, StatusValues.NULL_VALUE.getValue());
        }
    }

    public int getSize() {

        return this.size;
    }

    @Override
    public boolean isEmpty() {

        return nElems == 0;
    }

    public boolean isFull() {

        return nElems == size;
    }

    @Override
    public void insert(Hashable value) {

        if(value == null) throw new NullPointerException();
        if (isFull()) throw new TableIsFullException("The table is full, please delete a value first");
        if (find(value) != -1) throw new DuplicateElementException("No duplicate values allowed");

        int i = 0;

        do {

            int j = hash(value.key(), i++);

            if (keyPairs[j].getKey() == StatusValues.DELETED.getValue() || keyPairs[j].getValue() == null) {

                try {
                    keyPairs[j] = new KeyPair<>((V) value, value.key());
                } catch (ClassCastException e) {

                    System.out.println("Check data type");
                    break;
                }
                nElems++;
                break;
            }

        } while (i != size);

    }


    @Override
    public Hashable delete(Hashable value) {

        if(value == null) throw new NullPointerException();

        int index = find(value);

        if (index == -1) throw new ElementDoesNotExistException("Element does not exist");
        else {

            keyPairs[index] = new KeyPair<>(null, StatusValues.DELETED.getValue());
            nElems--;
        }

        return value;
    }

    @Override
    public int find(Hashable value) {

        if (nElems == 0) return -1;

        int i = 0;
        int j;

        do {

            j = hash(value.key(), i++);

            if (keyPairs[j].getKey() == value.key()) return j;

        } while ((keyPairs[j].getValue() != null || keyPairs[j].getKey() == StatusValues.DELETED.getValue()) && i != size);

        return -1;
    }

    @Override
    public int hash(int key, int probenumber) {

        int hash1 = ((int) (h1.apply(key))) % size;
        int hash2 = ((int) (h2.apply(key))) % size;

        return (hash1 + (probenumber * hash2)) % size;
    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < keyPairs.length; i++) {

            if (keyPairs[i].getValue() == null)
                output.append(i + " -> " + StatusValues.checkStatus(keyPairs[i].getKey()) + "; ");
            else
                output.append(i + " -> [" + keyPairs[i].getKey() + " , " + keyPairs[i].getValue() + "]; ");
        }

        return output.toString();
    }

    public static void main(String ... args) {

        Function<Integer, Integer> h1 = new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer integer) {

                return Integer.parseInt(String.valueOf(integer.toString().charAt(0)));
            }
        };

        Function<Integer, Integer> h2 = new Function<Integer, Integer>() {

            @Override
            public Integer apply(Integer integer) {

                String s = String.valueOf(integer);
                return Integer.parseInt(String.valueOf(s.charAt(s.length() - 1)));
            }
        };

        DoubleHashTable<HashableString> t = new DoubleHashTable<>(10, h1, h2);

    }
}
