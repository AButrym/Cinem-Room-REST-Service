package cinema.exceptions;

public class WrongPasswordException extends AuthException {
    public WrongPasswordException() {
        super("The password is wrong!");
    }
}
