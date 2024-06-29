package ru.kurochkin.temperatureSensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurochkin.temperatureSensor.model.Measurements;
import ru.kurochkin.temperatureSensor.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository,
                               SensorService sensorService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurements measurements){
        enrichMeasurements(measurements);
        measurementsRepository.save(measurements);
    }

    public List<Measurements> findAll(){
        return measurementsRepository.findAll();
    }

    private void enrichMeasurements(Measurements measurements){
        measurements.setSensor(sensorService.getSensorByName(measurements.getSensor().getName()).get());
        measurements.setMeasurementDateTime(LocalDateTime.now());
    }
}
