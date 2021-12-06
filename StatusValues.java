package src;

import static java.lang.Integer.MIN_VALUE;

public enum StatusValues {

    DELETED(-1),
    NULL_VALUE(MIN_VALUE);

    private final int value;

    StatusValues(int value) {

        this.value = value;
    }

    public static String checkStatus(int toCheck) {

        if (toCheck == DELETED.value) return "Deleted";
        if (toCheck == NULL_VALUE.value) return "Null";
        else return null;
    }

    public int getValue() {

        return this.value;
    }


}
