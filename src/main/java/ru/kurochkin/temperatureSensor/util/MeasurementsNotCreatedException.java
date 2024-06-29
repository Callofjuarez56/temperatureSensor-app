package ru.kurochkin.temperatureSensor.util;

public class MeasurementsNotCreatedException extends RuntimeException{
    public MeasurementsNotCreatedException(String msg){
        super(msg);
    }
}
