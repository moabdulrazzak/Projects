import java.util.ArrayList;

public class Player {
    private int balance;
    private ArrayList<Bet> bets;

    /**
     * Constructor for Player class
     * @param initialBalance
     */
    public Player(int initialBalance) {
        this.balance = initialBalance;
        this.bets = new ArrayList<>();
    }

    /**
     * Place a bet
     * @param bet
     * @throws InsufficientBalanceException
     */
    public void placeBet(Bet bet) throws InsufficientBalanceException{
        if (bet.amount <= balance) {
            bets.add(bet);
            balance -= bet.amount;
        }
        else
            throw new InsufficientBalanceException("Bet amount exceeds available balance!"); // Throw exception if bet amount exceeds available balance
    }

    /**
     * Evaluate bets based on the result of the spin
     * @param result
     */
    public void evaluateBets(RouletteNode result) {
        for (Bet bet : bets) {
            if (bet.isWinningBet(result)) {
                balance += calculatePayout(bet);
            }
        }
        bets.clear();
    }

    /**
     * Calculate the payout for a winning bet
     * @param bet
     * @return payout amount
     */
    private int calculatePayout(Bet bet) {
        // Define payout rules
        switch (bet.type) {
            case "Number":
                return bet.amount * 35;
            case "Color":
                return bet.amount * 2;
            case "Odd/Even":
                return bet.amount * 2;
            default:
                return 0;
        }
    }

    /**
     * Get the player's balance
     * @return balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Get the player's bets
     * @return bets
     */
    public ArrayList<Bet> getBets() {
        return bets;
    }
}
