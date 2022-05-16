
/*** a computerized traffic light - a class that encapsulates the traffic light simulation ***/

// implementing Runnable is necessary because a separate thread is used to run each traffic light (this thread will cycle through the colors)
class TrafficLightSimulator implements Runnable {
    private TrafficLightColor tlc; // holds the traffic light color
    boolean stop = false; // set to true to stop the simulation
    boolean changed = false; // true when the light has changed
    TrafficLightSimulator(TrafficLightColor col) {
        tlc = col;
    }

    // a method that begins running the traffic light
    @Override
    public void run() { // start up the light
        // while we haven't stopped running the traffic light simulation
        while(!stop) {
            // handle potential exceptions
            try {
                // cycles the light through the colors
                Thread.sleep(tlc.getDelay()); // use ordinal values instead of relying on a switch-statement
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }
            changeColor(); // calls changeColor( ) to change to the next color in the sequence
        }
    }

    // a method that changes color
    synchronized void changeColor() {
        // assigns the next color in the sequence using a switch-statement
        switch(tlc) {
            case RED:
                tlc = TrafficLightColor.GREEN;
                break;
            case YELLOW:
                tlc = TrafficLightColor.RED;
                break;
            case GREEN:
                tlc = TrafficLightColor.YELLOW;
        }

        changed = true; // true if light has changed

        // notify() method is synchronized and can therefor be called only from a synchronized context
        notify(); // signal that the light has changed
    }

    // a method that waits for the light to change - this method simply calls wait() and won’t return until the color has changed
    synchronized void waitForChange() {  // wait until a light change occurs
        while(!changed) {
            // handle potential exceptions
            try {
                // this call won’t return until changeColor() executes a call to notify()
                wait(); // wait for light to change
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }
        }
        changed = false; // reset
    }

    synchronized TrafficLightColor getColor() {
        // return the current color
        return tlc;
    }

    // stops the traffic light thread
    synchronized void cancel() {
        stop = true;
    }

}
