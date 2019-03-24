package com.epam.port.state;

import com.epam.port.entity.Dock;

public class FullState implements LoadState {
    @Override
    public void process(Dock dock) throws InterruptedException {
        dock.unloadShip();
    }

    @Override
    public String toString() {
        return "FullState";
    }
}
