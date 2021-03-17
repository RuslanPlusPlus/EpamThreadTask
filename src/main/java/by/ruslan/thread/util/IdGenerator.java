package by.ruslan.thread.util;

public class IdGenerator {
    private static int SHIP_ID_COUNTER = 1;
    private static int BERTH_ID_COUNTER = 1;
    //private static int CONTAINER_ID_COUNTER = 1;

    public static long generateShipId(){
        return SHIP_ID_COUNTER++;
    }

    public static long generateBerthId(){
        return BERTH_ID_COUNTER++;
    }

    /*public static long generateContainerId(){
        return CONTAINER_ID_COUNTER++;
    }*/
}
