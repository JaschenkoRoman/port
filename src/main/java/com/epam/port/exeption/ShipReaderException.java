package com.epam.port.exeption;

public class ShipReaderException extends Exception {
    public ShipReaderException() {
    }

    public ShipReaderException(String message) {
        super(message);
    }

    public ShipReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipReaderException(Throwable cause) {
        super(cause);
    }
}
