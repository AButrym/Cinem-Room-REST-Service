package cinema.exceptions;

public class AlreadyPurchasedException extends BusinessLogicException {
    public AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
