package ru.khamzin.springcourse.SpringRESTSensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khamzin.springcourse.SpringRESTSensor.models.Measurement;
import ru.khamzin.springcourse.SpringRESTSensor.repositories.MeasurementsRepository;
import ru.khamzin.springcourse.SpringRESTSensor.repositories.SensorsRepository;
import ru.khamzin.springcourse.SpringRESTSensor.util.SensorNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsRepository sensorsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsRepository sensorsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorsRepository.findByName(measurement.getSensor().getName()).get());

        measurementsRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public List<Measurement> findAllBySensorName(String sensorName) {
        return sensorsRepository.findByName(sensorName)
                .orElseThrow(SensorNotFoundException::new)
                .getMeasurements();
    }

    public Integer countOfRainyDays() {
        List<Measurement> measurements = measurementsRepository.findAll();
        int numberOfRainyDays = 0;
        for (Measurement measurement : measurements) {
            if (measurement.getRaining())
                numberOfRainyDays++;
        }
        return numberOfRainyDays;
    }

}