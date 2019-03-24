package com.epam.port.entity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Dock {
    private int id;
    private Ship currentShip;

    public Dock(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ship getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }

    public boolean isFree() {
        return currentShip == null;
    }

    public void release() {
        currentShip = null;
    }

    public void unloadShip() throws InterruptedException {
        System.out.println("Ship " + currentShip.getId() + " starts unloading");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Ship " + currentShip.getId() + " is unloaded in the dock " + id);
    }

    public void loadShip() throws InterruptedException {
        System.out.println("Ship " + currentShip.getId() + " starts loading");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Ship " + currentShip.getId() + " is loaded in the dock " + id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dock dock = (Dock) o;
        return id == dock.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dock {" +
                "id: " + id +
                ", currentShip: " + currentShip +
                '}';
    }
}
