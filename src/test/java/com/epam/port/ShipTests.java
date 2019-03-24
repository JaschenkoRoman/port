package com.epam.port;

import com.epam.port.entity.Ship;
import com.epam.port.state.EmptyState;
import com.epam.port.state.FullState;
import com.epam.port.state.LoadState;
import org.junit.Assert;
import org.junit.Test;

public class ShipTests {
    @Test
    public void testFullShipGetStateShouldReturnEmptyStateWhenChangeState(){
        Ship ship = new Ship(1, new FullState());
        ship.changeState();
        LoadState actual = ship.getState();
        Assert.assertEquals(EmptyState.class, actual.getClass());
    }
    @Test
    public void testEmptyShipGetStateShouldReturnFullStateWhenChangeState(){
        Ship ship = new Ship(1, new EmptyState());
        ship.changeState();
        LoadState actual = ship.getState();
        Assert.assertEquals(FullState.class, actual.getClass());
    }
}
