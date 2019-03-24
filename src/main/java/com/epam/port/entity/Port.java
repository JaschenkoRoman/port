package com.epam.port.entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Port {
    private Logger logger = LoggerFactory.getLogger(Port.class);
    private static Port instance;
    private static  AtomicInteger shipCount = new AtomicInteger(0);
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static Condition hasFreeDocks = lock.newCondition();
    private List<Dock> docks = Arrays.asList(
            new Dock(1),
            new Dock(2),
            new Dock(3)
    );

    public static Port getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {
                    instance = new Port();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Dock getFreeDock(Ship ship) {
        lock.lock();
        Dock freeDock = null;
        try {
            while (shipCount.get() == docks.size()) {
                hasFreeDocks.await();
            }
            for (Dock dock : docks) {
                if (dock.isFree()) {
                    freeDock = dock;
                    freeDock.setCurrentShip(ship);
                    System.out.println("Ship " + ship.getId() + " assigned to dock " + dock.getId());
                    break;
                }
            }
            shipCount.incrementAndGet();
        } catch (InterruptedException e) {
            logger.error("InterruptedException occurred while ship " + ship.getId() + " was trying to get free dock ", e);
        } finally {
            lock.unlock();
        }
        return freeDock;
    }

    public void releaseDock(Dock dock) {
        lock.lock();
        try {
            dock.release();
            shipCount.decrementAndGet();
            hasFreeDocks.signal();
        } finally {
            lock.unlock();
        }
    }
}
