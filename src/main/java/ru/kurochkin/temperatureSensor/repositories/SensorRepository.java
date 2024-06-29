package ru.kurochkin.temperatureSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kurochkin.temperatureSensor.model.Sensor;

import java.util.Optional;

/**
 * @author Oleg Kurochkin
 */
@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
