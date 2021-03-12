package by.ruslan.thread.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Ship implements Runnable{

    static final Logger logger = LogManager.getLogger();
    private Long id;
    private Berth berth;
    private Port port;
    private final int maxCapacity;
    private int currentCapacity;

    public Ship(Long id, int maxCapacity, int currentCapacity){
        this.id = id;
        port = Port.getInstance();
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    @Override
    public void run() {

    }

    public Long getId() {
        return id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    private void arriveAtBerth(){

    }

    private void leaveBerth(){

    }

    private void loadContainers(){

    }

    private void unloadContainers(){

    }
}
