package com.epam.port.state;

import com.epam.port.entity.Dock;

public class EmptyState implements LoadState {
    @Override
    public void process(Dock dock) throws InterruptedException {
        dock.loadShip();
    }

    @Override
    public String toString() {
        return "EmptyState";
    }
}
