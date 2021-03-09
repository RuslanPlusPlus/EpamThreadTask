package by.ruslan.thread.entity;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private List<Berth> berthList;
    private static final int CONTAINER_CAPACITY = 500;
    private static Port instance = new Port();

    private final Lock lock = new ReentrantLock();
    private final Condition portNotFull = lock.newCondition();
    private final Condition portNotEmpty = lock.newCondition();

    public static Port getInstance() {
        return instance;
    }


    public Berth getBerth(){
        //todo realization
    }
}
