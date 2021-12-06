package src;

public class DuplicateElementException extends RuntimeException {

    public DuplicateElementException(String no_duplicate_values_allowed) {
        super(no_duplicate_values_allowed);
    }
}
