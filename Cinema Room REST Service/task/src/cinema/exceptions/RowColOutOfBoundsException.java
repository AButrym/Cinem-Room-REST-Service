package cinema.exceptions;

public class RowColOutOfBoundsException extends BusinessLogicException {
    public RowColOutOfBoundsException() {
        super("The number of a row or a column is out of bounds!");
    }
}
