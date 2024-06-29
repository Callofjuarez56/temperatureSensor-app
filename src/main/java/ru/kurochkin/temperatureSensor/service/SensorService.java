package ru.kurochkin.temperatureSensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurochkin.temperatureSensor.model.Sensor;
import ru.kurochkin.temperatureSensor.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Oleg Kurochkin
 */
@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        enrichSensor(sensor);
        sensorRepository.save(sensor);
    }

    private void enrichSensor(Sensor sensor){
        sensor.setCreatedAt(LocalDateTime.now());
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }
}