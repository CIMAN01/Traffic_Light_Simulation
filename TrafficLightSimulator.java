/**
 An improved version of the traffic light simulation that stores the light delay in TrafficLightColor

 There are two major improvements:

 First, a light's delay is now linked with its enumeration value, which gives more structure to the code.

 Second, the run() method no longer needs to use a switch statement to determine the length of the delay. Instead, sleep() is passedtlc.getDelay(),
 which causes the delay associated with the current color to be used automatically.
**/

/*
// an enumeration of the colors of a traffic light (whenever the color of the light is needed, its enumeration value is used)
enum TrafficLightColor {
    RED, GREEN, YELLOW
}
*/

// an enumeration of the colors of a traffic light (whenever the color of the light is needed, its enumeration value is used)
enum TrafficLightColor {
    RED(12000), GREEN(10000), YELLOW(2000);
    // a private instance variable for the delay (duration of sleep)
    private final int delay;
    // a constructor for the duration of sleep
    TrafficLightColor(int d) {
        delay = d;
    }
    // a getter that return the delay
    public int getDelay() {
        return delay;
    }
}


// a computerized traffic light - a class that encapsulates the traffic light simulation
class TrafficLightSimulator implements Runnable { // implements Runnable is necessary because a separate thread is used to run each traffic light (this thread will cycle through the colors)
    // a variable for the traffic light color
    private TrafficLightColor tlc; // holds the traffic light color
    // boolean variables initialized to false
    boolean stop = false; // set to true to stop the simulation
    boolean changed = false; // true when the light has changed
    // a constructor with one parameter that lets you specify the initial light color
    TrafficLightSimulator(TrafficLightColor initial) {
        tlc = initial;
    }
    // a second constructor (with no parameters) that defaults to red
    TrafficLightSimulator() {
        tlc = TrafficLightColor.RED;
    }

    // a method that begins running the traffic light
    public void run() { // start up the light
        // while we haven't stopped running the traffic light simulation
        while(!stop) {
            // try-catch block
            try {
                // cycles the light through the colors
                Thread.sleep(tlc.getDelay()); // code has been simplified - using ordinal values instead of relying on a switch-statement
/*
                // cycles the light through the colors using a switch-statement
                switch(tlc) {
                    case GREEN:
                        // sleeps an appropriate amount of time, based on the current color
//                        Thread.sleep(10000); // green for 10 seconds
                        Thread.sleep(TrafficLightColor.GREEN.getDelay()); // yellow for 2 seconds
                        break;
                    case YELLOW:
                        // sleeps an appropriate amount of time, based on the current color
//                        Thread.sleep(2000); // yellow for 2 seconds
                        Thread.sleep(TrafficLightColor.YELLOW.getDelay()); // yellow for 2 seconds
                        break;
                    case RED:
                        // sleeps an appropriate amount of time, based on the current color
//                        Thread.sleep(12000); // red for 12 seconds
                        Thread.sleep(TrafficLightColor.RED.getDelay()); // yellow for 2 seconds
                        break;
                }

 */
            }
            // catch any errors
            catch(InterruptedException exc) {
                System.out.println(exc);
            }
            // calls changeColor( ) to change to the next color in the sequence
            changeColor();
        }
    }

    // a method that changes color
    synchronized void changeColor() {
        // assigns the next color in the sequence using a switch-statement
        switch(tlc) {
            // if the light is red
            case RED:
                // change light to green
                tlc = TrafficLightColor.GREEN;
                break;
            // if light is yellow
            case YELLOW:
                // change light to red
                tlc = TrafficLightColor.RED;
                break;
            // if light is green
            case GREEN:
                // change light to yellow
                tlc = TrafficLightColor.YELLOW;
        }
        // if the light has been changed, update boolean variable to true
        changed = true;
        // signal that the light has changed
        notify(); // notify() method is synchronized and can therefor be called only from a synchronized context
    }

    // a method that waits for the light to change - this method simply calls wait() and won’t return until the color has changed
    synchronized void waitForChange() {  // wait until a light change occurs
        // try-catch block
        try {
            // while the light hasn't changed
            while(!changed)
                // wait for light to change
                wait(); // this call won’t return until changeColor() executes a call to notify()
            // reset changed to false (reset boolean condition)
            changed = false;
        }
        // catch any errors
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
    }

    // a getter method for retrieving the current color
    synchronized TrafficLightColor getColor() {
        // return the current color
        return tlc;
    }

    // a method that stops the traffic light
    synchronized void cancel() {
        // stops the traffic light thread by setting stop to true
        stop = true;
    }

}
