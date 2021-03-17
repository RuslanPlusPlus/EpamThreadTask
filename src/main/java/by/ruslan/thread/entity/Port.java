package by.ruslan.thread.entity;

import by.ruslan.thread.exception.ThreadException;
import by.ruslan.thread.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    static final Logger logger = LogManager.getLogger();
    static final int BERTH_CAPACITY = 5;
    private static Port instance;
    private static AtomicBoolean portCreated = new AtomicBoolean();
    private Queue<Berth> freeBerths = new LinkedList<>();
    private Queue<Berth> busyBerths = new LinkedList<>();
    private static final Lock lock = new ReentrantLock(true);
    private static final Lock berthLock = new ReentrantLock(true);
    private final Condition berthIsFree = berthLock.newCondition();

    public static Port getInstance() {
        if (!portCreated.get()){
            lock.lock();
            if (!portCreated.get()){
                instance = new Port();
                portCreated.set(true);
            }
            lock.unlock();
        }
        return instance;
    }

    private Port(){
        prepareBerths();
    }

    public Berth getFreeBerth() throws ThreadException {
        try {
            berthLock.lock();
            while (freeBerths.isEmpty()){
                berthIsFree.await();
            }
            Berth berth = freeBerths.poll();
            busyBerths.offer(berth);
            return berth;
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            throw new ThreadException("Thread is interrupted", e);
        }finally {
            berthLock.unlock();
        }
    }

    public void leaveBerth(Berth berth) {
        try {
            berthLock.lock();
            freeBerths.offer(berth);
            busyBerths.remove();
            berthIsFree.signal();
        } finally {
            berthLock.unlock();
        }
    }

    private void prepareBerths(){
        for (int i = 0; i < BERTH_CAPACITY; i++){
            long id = IdGenerator.generateBerthId();
            freeBerths.add(new Berth(id));
        }
    }
}
