package com.epam.port.entity;

import com.epam.port.state.EmptyState;
import com.epam.port.state.FullState;
import com.epam.port.state.LoadState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class Ship implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Ship.class);
    private int id;
    private Port port;
    private LoadState state;

    public Ship(int id, LoadState state){
        this.id = id;
        this.state = state;
        this.port = Port.getInstance();
    }

    public Ship(){
        this.port = Port.getInstance();
    }

    public int getId() {
        return id;
    }

    public Port getPort() {
        return port;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoadState getState() {
        return state;
    }

    public void setState(LoadState state) {
        this.state = state;
    }

    public void changeState(){
        if(state instanceof EmptyState) {
            state = new FullState();
        } else {
            state = new EmptyState();
        }
    }

    public void run() {
        Dock dock = null;
        try {
            System.out.println("Ship " + id + " arrives to the port");
            dock = port.getFreeDock(this);
            state.process(dock);
            changeState();
        } catch (InterruptedException e) {
            logger.error("InterruptedException occurred in the ship " + id, e);
        } finally {
            port.releaseDock(dock);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ship ship = (Ship) o;
        return id == ship.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ship {" +
                "id: " + id +
                ", port: " + port +
                ", loadState: " + state +
                '}';
    }
}
