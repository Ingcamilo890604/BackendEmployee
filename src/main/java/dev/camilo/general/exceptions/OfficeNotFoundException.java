package dev.camilo.general.exceptions;

public class OfficeNotFoundException extends RuntimeException{
    public OfficeNotFoundException(String message) {
        super(message);
    }
}
