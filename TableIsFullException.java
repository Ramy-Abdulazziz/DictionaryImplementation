package src;

public class TableIsFullException extends RuntimeException {

    public TableIsFullException(String errorMessage) {
        super(errorMessage);
    }

}
