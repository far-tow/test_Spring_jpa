package se.fartow.test_spring.exceptions;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String message) {
        super(message);
    }
}