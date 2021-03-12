package by.ruslan.thread.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    static final Logger logger = LogManager.getLogger();
    static final int STORAGE_CAPACITY = 500;
    private static final Storage instance = new Storage();
    private Queue<ShipContainer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition storageNotFull = lock.newCondition();
    private final Condition storageNotEmpty = lock.newCondition();

    public static Storage getInstance() {
        return instance;
    }

    public void loadContainers(Ship ship){

    }

    public void unloadContainers(Ship ship){

    }
}
