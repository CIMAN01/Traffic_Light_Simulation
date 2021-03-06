// A simulation of a traffic light that uses an enumeration to describe the light's color.

// main class
class TrafficLightDemo {

    // main method
    public static void main(String[] args) {
        // create a new traffic light object and pass in the color green
        TrafficLightSimulator trafficLight = new TrafficLightSimulator(TrafficLightColor.GREEN);
        // create a new thread and pass in the traffic light object
        Thread thread = new Thread(trafficLight);
        // run the threat
        thread.start();
        // use a for-loop to log from 0 to less than 9
        for (int i = 0; i < 9; i++) {
            // display the current traffic light color
            System.out.println(trafficLight.getColor());
            // wait for the traffic light to change
            trafficLight.waitForChange();
        }
        // cancel the traffic light simulation
        trafficLight.cancel();
    }

    /*

    GREEN
    YELLOW
    RED
    GREEN
    YELLOW
    RED
    GREEN
    YELLOW
    RED

    */
}
