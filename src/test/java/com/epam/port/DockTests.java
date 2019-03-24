package com.epam.port;

import com.epam.port.entity.Dock;
import com.epam.port.entity.Ship;
import com.epam.port.state.EmptyState;
import org.junit.Assert;
import org.junit.Test;

public class DockTests {
    @Test
    public void testIsFreeShouldReturnTrueWhenCurrentShipIsNotAssigned(){
        Dock dock = new Dock(1);
        boolean isFree = dock.isFree();
        Assert.assertTrue(isFree);
    }
    @Test
    public void testIsFreeShouldReturnTrueWhenCurrentShipIsAssigned(){
        Dock dock = new Dock(1);
        Ship ship = new Ship(1, new EmptyState());
        dock.setCurrentShip(ship);
        boolean isFree = dock.isFree();
        Assert.assertFalse(isFree);
    }
}
