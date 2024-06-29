package ru.kurochkin.temperatureSensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kurochkin.temperatureSensor.model.Measurements;
import ru.kurochkin.temperatureSensor.service.MeasurementsService;
import ru.kurochkin.temperatureSensor.service.SensorService;

@Component
public class MeasurementsValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurements.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurements measurements = (Measurements) o;

        if (measurements.getSensor() == null) return;

        if (sensorService.getSensorByName(measurements.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "Сенсор с таким названием уже существует");
    }
}
