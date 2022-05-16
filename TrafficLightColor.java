
// an enumeration of the colors of a traffic light (whenever the color of the light is needed, its enumeration value is used)
enum TrafficLightColor {
    RED(12000), GREEN(10000), YELLOW(2000);
    private final int delay; // duration of sleep
    TrafficLightColor(int delay) {
        this.delay = delay;
    }
    public int getDelay() {
        return delay;
    }

}