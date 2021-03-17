package by.ruslan.thread.entity;

import by.ruslan.thread.exception.ThreadException;
import by.ruslan.thread.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship implements Runnable{

    static final Logger logger = LogManager.getLogger();
    private long id;
    private Berth berth;
    private Port port;
    private final int maxCapacity;
    private int currentCapacity;

    public Ship(int maxCapacity, int currentCapacity){
        this.id = IdGenerator.generateShipId();
        port = Port.getInstance();
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    @Override
    public void run() {
        try {
            arriveAtBerth();
            unloadContainers();
            loadContainers();
            leaveBerth();
        } catch (ThreadException e) {
            logger.error("Failed to service the ship");
        }

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

    public boolean ableToAdd(){
        return currentCapacity < maxCapacity;
    }

    public boolean ableToDelete(){
        return currentCapacity > 0;
    }

    public boolean deleteContainer() {
        boolean isDeleted = true;
        if (currentCapacity> 0) {
            currentCapacity--;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }

    public boolean addContainer() {
        boolean isAdded = true;
        if (currentCapacity < maxCapacity) {
            currentCapacity++;
        } else {
            isAdded = false;
        }
        return isAdded;
    }

    private void arriveAtBerth() throws ThreadException {
        berth = port.getFreeBerth();
        logger.info("Ship with id=" + id + " got berth with id=" + berth.getId());
    }

    private void leaveBerth(){
        port.leaveBerth(berth);
        berth = null;
    }

    private void loadContainers() throws ThreadException {
        if (berth != null) {
            berth.loadContainers(this);
        }
    }

    private void unloadContainers() throws ThreadException {
        if (berth != null) {
            berth.unloadContainers(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return maxCapacity == ship.maxCapacity &&
                currentCapacity == ship.currentCapacity &&
                id == ship.id;

    }

    @Override
    public int hashCode() {
        final int hash = 31;
        int result = 1;
        result = result * hash + maxCapacity;
        result = result * hash + currentCapacity;
        result = (int) (result * hash + id);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ship{")
                .append("id=").append(id)
                .append(", berthId=").append(berth.getId())
                .append(", maxCapacity=").append(maxCapacity)
                .append(", currentCapacity=").append(currentCapacity)
                .append("}");
        return builder.toString();
    }
}
