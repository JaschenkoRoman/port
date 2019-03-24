package com.epam.port.reader;

import com.epam.port.entity.Port;
import com.epam.port.entity.Ship;
import com.epam.port.exeption.ShipReaderException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ShipReader {
    private Logger logger = LoggerFactory.getLogger(Port.class);

    public List<Ship> readShips(String path) throws ShipReaderException {
        InputStream inJson = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        ObjectMapper mapper = new ObjectMapper();
        List<Ship> ships;
        try {
            ships = mapper.readValue(inJson, new TypeReference<List<Ship>>(){});
        } catch (IOException e) {
            logger.error("cannot read ships from " + path, e);
            throw new ShipReaderException("cannot read ships from " + path, e);
        }
        return ships;

    }
}
