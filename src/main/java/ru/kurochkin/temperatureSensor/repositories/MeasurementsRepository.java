package ru.kurochkin.temperatureSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kurochkin.temperatureSensor.model.Measurements;
import ru.kurochkin.temperatureSensor.model.Sensor;

import java.util.Optional;

/**
 * @author Oleg Kurochkin
 */
@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {
}
