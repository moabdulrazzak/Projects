public class InsufficientBalanceException extends Exception {
    // Exception class for when a player tries to place a bet with an amount that exceeds their available balance
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
