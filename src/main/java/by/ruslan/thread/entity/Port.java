package by.ruslan.thread.entity;

import by.ruslan.thread.exception.ThreadException;
import by.ruslan.thread.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    static final Logger logger = LogManager.getLogger();
    private static final int BERTH_CAPACITY = 5;
    private static Port instance = new Port();

    private Queue<Berth> freeBerths = new LinkedList<>();
    private Queue<Berth> busyBerths = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition berthIsFree = lock.newCondition();

    public static Port getInstance() {
        return instance;
    }

    private Port(){
        prepareBerths();
    }

    public Berth getFreeBerth() throws ThreadException {
        lock.lock();
        try {
            while (freeBerths.isEmpty()){
                berthIsFree.await();
            }
            Berth berth = freeBerths.poll();
            busyBerths.offer(berth);
            return berth;
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }finally {
            lock.unlock();
        }
        throw new ThreadException("Failed to give free berth");
    }

    public void leaveBerth(Berth berth) {
        try {
            lock.lock();
            freeBerths.offer(berth);
            busyBerths.remove();
            berthIsFree.signalAll();
        } finally {
            lock.unlock();
        }
    }

    private void prepareBerths(){
        for (int i = 0; i < BERTH_CAPACITY; i++){
            long id = IdGenerator.generateId(EntityType.BERTH);
            freeBerths.add(new Berth(id));
        }
    }
}
