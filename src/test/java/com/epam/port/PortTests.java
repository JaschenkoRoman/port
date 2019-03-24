package com.epam.port;

import com.epam.port.entity.Port;
import org.junit.Assert;
import org.junit.Test;

public class PortTests {
    @Test
    public void testPortShouldReturnAlwaysTheSameInstance(){
        Port port1 = Port.getInstance();
        Port port2 = Port.getInstance();
        Assert.assertEquals(port1, port2);
    }
}
