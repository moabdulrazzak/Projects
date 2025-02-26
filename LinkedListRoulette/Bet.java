public class Bet {
    String type; // "Number", "Color", "Odd/Even"
    Object value; // Bet value (e.g., number, "Red")
    int amount; // Bet amount

    /**
     * Constructor for Bet class
     * @param type
     * @param value
     * @param amount
     */
    public Bet(String type, Object value, int amount) {
        this.type = type;
        this.value = value;
        this.amount = amount;
    }

    /**
     * Check if the bet is a winning bet
     * @param result
     * @return true if the bet is a winning bet, false otherwise
     */
    public boolean isWinningBet(RouletteNode result) {
        switch (type) {
            case "Number":
                return result.getNumber() == (int) value;
            case "Color":
                return result.getColor().equals(value);
            case "Odd/Even":
                return (result.getNumber() % 2 == 0) == value.equals("Even");
            default:
                return false;
        }
    }
    /**
     * Returns a string representation of the Bet object
     */
    public String toString(){
        return type + " " + value + " " + amount;
    }
}
