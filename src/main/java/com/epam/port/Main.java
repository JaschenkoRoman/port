package com.epam.port;

import com.epam.port.entity.Ship;
import com.epam.port.exeption.ShipReaderException;
import com.epam.port.reader.ShipReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws ShipReaderException {

        ShipReader shipReader = new ShipReader();
        List<Ship> ships = shipReader.readShips("data.json");
        ExecutorService service = Executors.newFixedThreadPool(10);
        for ( Ship ship: ships) {
            service.execute(ship);
        }


    }
}
