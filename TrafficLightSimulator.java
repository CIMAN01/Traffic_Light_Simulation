/*** a computerized traffic light - a class that encapsulates the traffic light simulation ***/

// implementing Runnable is necessary because a separate thread is used to run each traffic light (this thread will cycle through the colors)
class TrafficLightSimulator implements Runnable {
    
    private TrafficLightColor tlc; // holds the traffic light color
    
    boolean stop = false; // set to true to stop the simulation
    boolean changed = false; // true when the light has changed
    
    private final Object tlcLock = new Object(); // use a dedicated monitor object
    private final Object stopLock = new Object(); // use a dedicated monitor object

    TrafficLightSimulator(TrafficLightColor col) {
        tlc = col;
    }

    // This method cycles the light through the different colors
    @Override
    public void run() {
        // while we haven't stopped running the traffic light simulation
        while(!stop) {
            // handle any exceptions
            try {
                // sleeps an appropriate amount of time, based on the current color
                Thread.sleep(tlc.getDelay()); // using ordinal values instead of relying on a switch-statement
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            // change to the next color in the sequence
            changeColor();
        }
    }

    // This method cycles to the next color in the sequence
    public synchronized void changeColor() {
        // use a switch-statement to assign the next color in the sequence
        switch(tlc) {
            case RED:
                tlc = TrafficLightColor.GREEN;
                break;
            case YELLOW:
                tlc = TrafficLightColor.RED;
                break;
            case GREEN:
                tlc = TrafficLightColor.YELLOW;
                break;
        }
        // set changed to true when a light has changed
        changed = true;
        // notify() method is synchronized and can therefor be called only from a synchronized context
        notify(); // signal that the light has changed (wakes up a single thread that is waiting on that object's monitor)
    }

    // This method waits until the color of the light is changed (this method simply calls wait() and won’t return until the color has changed)
    public synchronized void waitForChange() {
        while(!changed) {
            // handle any exceptions
            try {
                // this call won’t return until changeColor() executes a call to notify()
                wait(); // wait for light to change (causes the current thread to wait until another thread invokes the notify() method)
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        changed = false; // reset
    }

    // return the current color
    public TrafficLightColor getColor() {
        synchronized(tlcLock) {
            // return the current color
            return tlc;
        }
    }

    // stop the traffic light
    public void cancel() {
        synchronized(stopLock) {
            stop = true;
        }
    }

}
