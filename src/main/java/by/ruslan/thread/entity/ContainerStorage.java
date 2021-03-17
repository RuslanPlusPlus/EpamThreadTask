package by.ruslan.thread.entity;

import by.ruslan.thread.exception.ThreadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContainerStorage {
    static final Logger logger = LogManager.getLogger();
    static final int STORAGE_CAPACITY = 500;
    private AtomicInteger currentCapacity = new AtomicInteger();
    private static final ContainerStorage instance = new ContainerStorage();
    private final Lock lock = new ReentrantLock(true);

    public static ContainerStorage getInstance() {
        return instance;
    }

    public void loadContainers(Ship ship) throws ThreadException {
        try {
            lock.lock();
            while (currentCapacity.get() > 0 && ship.ableToAdd()) {
                logger.info("Ship with id=" + ship.getId() + " loads containers");
                TimeUnit.SECONDS.sleep(1);
                currentCapacity.decrementAndGet();
                ship.addContainer();
            }
            logger.info("Ship with id=" + ship.getId() + " completed loading");
            logger.info("Storage current fullness: " + currentCapacity.get());
        } catch (InterruptedException e) {
            throw new ThreadException(e);
        } finally {
            lock.unlock();
        }

    }

    public void unloadContainers(Ship ship) throws ThreadException {
        try {
            lock.lock();
            while (currentCapacity.get() < STORAGE_CAPACITY && ship.ableToDelete()) {
                logger.info("Ship with id=" + ship.getId() + " unloads containers");
                TimeUnit.SECONDS.sleep(1);
                currentCapacity.incrementAndGet();
                ship.deleteContainer();
            }
            logger.info("Ship with id=" + ship.getId() + " completed unloading");
            logger.info("Storage current fullness: " + currentCapacity.get());
        } catch (InterruptedException e) {
            throw new ThreadException(e);
        } finally {
            lock.unlock();
        }
    }
}
