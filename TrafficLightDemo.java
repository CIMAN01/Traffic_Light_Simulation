// A simulation of a traffic light that uses an enumeration to describe the light's color.

class TrafficLightDemo {

    public static void main(String[] args) {
        // create a new traffic light object that uses the color green
        TrafficLightSimulator trafficLight = new TrafficLightSimulator(TrafficLightColor.GREEN);
        // create a new thread that uses the traffic light object
        Thread thread = new Thread(trafficLight);
        // start the thread
        thread.start();
        // iterate from 1 to 9 (8 color cycles from green to red)
        for(int i = 0; i < 9; i++) {
            // print the current traffic light color
            System.out.println(trafficLight.getColor());
            // wait for the traffic light to change
            trafficLight.waitForChange();
        }
        // cancel the traffic light simulation via cancel() method
        trafficLight.cancel();
    }

    /* OUTPUT:
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
