import java.util.TreeMap;

public class RouletteWheelTester {
    public static void main(String[] args) {
        RouletteWheel wheel = new RouletteWheel();
        TreeMap<RouletteNode,Integer> NodeCount = new TreeMap<>();
        System.out.println("Wheel: " + wheel);
        System.out.println("Node after 1 step, expected result: 28 Black \nactual: " + wheel.spin(1));
        System.out.println("Node after 37 steps, expected result: 0 Green \nactual: " + wheel.spin(37));
        System.out.println("Node after 37000 steps, expected result: 0 Green \nactual: " + wheel.spin(37000));

        for(int i = 0; i<=100000; i++){
            RouletteNode value = wheel.spinBall();
            if(NodeCount.containsKey(value)){
                int count = NodeCount.get(value);
                NodeCount.put(value, count+1); 
            }
            else
                NodeCount.put(value, 1);
        }
        System.out.println("Distibution of results after 100000 spins: " + NodeCount);

    }
}
