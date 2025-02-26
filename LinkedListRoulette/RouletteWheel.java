import java.util.Random;

public class RouletteWheel {
    //instance variables
    private RouletteNode head;

    //constructor
    public RouletteWheel(){
        intializeWheel();
    }
    //initialize the wheel
    private void intializeWheel(){
        //numbers on the wheel in order of appearance
        int[] numbers = {0,28,9,26,30,11,7,20,32,17,5,22,34,15,3,24,36,13,1,27,10,25,29,12,8,19,31,18,6,21,33,16,4,23,35,14,2};
        RouletteNode tail = null;
        RouletteNode newNode;
        //fill the wheel with numbers and colors
        for(int i = 0; i<numbers.length; i++){

            if(numbers[i] == 0){
                newNode = new RouletteNode(numbers[i], "Green"); //create a green node
            }
            else if(i%2!=0){
                newNode = new RouletteNode(numbers[i], "Black"); //create a black node
            }
            else{
                newNode = new RouletteNode(numbers[i], "Red"); //create a red node
            }
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode; //connect the new node to the previous node
            }
            tail = newNode; //move tail to the new node
        }
        tail.next = head; //connect the last node to the head
    }
    /**
     * Spin the wheel a given number of steps
     * @param steps
     * @return the node that the wheel stops on
     */
    public RouletteNode spin(int steps) {
        RouletteNode current = head;
        for (int i = 0; i < steps; i++) {
            current = current.next;
        }
        return current;
    }
    /**
     * Spin the wheel a random number of steps
     * @return the node that the wheel stops on
     */
    public RouletteNode spinBall() {
        int steps = new Random().nextInt(37);
        return spin(steps);
    }
    /**
     * @return a string representation of the wheel
     */
    public String toString() {
        String result = "";
        RouletteNode current = head;
        while(current.next != head){
            result += current;
            if(current.next != null){
                 result += ", ";
            }
            current = current.next;
        }
        result += current;
        return result;
    }
}
