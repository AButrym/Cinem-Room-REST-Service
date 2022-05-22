package cinema.exceptions;

public class WrongTokenException extends BusinessLogicException {
    public WrongTokenException() {
        super("Wrong token!");
    }
}
