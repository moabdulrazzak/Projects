public class RouletteNode implements Comparable {
    /*
     * This class represents a node in a linked list that will be used to represent the roulette wheel.
     */
    private int number;
    private String color;
    public RouletteNode next;

    /**
     * Constructor for RouletteNode class
     * @param number 
     * @param color 
     */
    public RouletteNode(int number, String color){
        this.number = number;
        this.color = color;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }
    /**
     * @return a string representation of the node
     */
    public String toString(){

        return number +" "+  color;
    }

    /**
     * Compare two RouletteNode objects based on their numbers
     * @param o the other object to compare to
     * @return 1 if this number is greater than the other number, -1 if this number is less than the other number, 0 if they are equal
     */
    public int compareTo(Object o) {
        RouletteNode other = (RouletteNode) o;
        if(number > other.number)
            return 1;
        else if (number < other.number)
            return -1;
        else
            return 0;
    }
}
