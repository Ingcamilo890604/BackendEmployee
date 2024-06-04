package dev.camilo.general.excep;

public class OfficeNotFoundException extends RuntimeException{
    public OfficeNotFoundException(String message) {
        super(message);
    }
}
