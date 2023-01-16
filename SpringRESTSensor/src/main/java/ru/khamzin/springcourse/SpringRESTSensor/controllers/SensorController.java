package ru.khamzin.springcourse.SpringRESTSensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khamzin.springcourse.SpringRESTSensor.dto.SensorDTO;
import ru.khamzin.springcourse.SpringRESTSensor.models.Sensor;
import ru.khamzin.springcourse.SpringRESTSensor.services.SensorsService;
import ru.khamzin.springcourse.SpringRESTSensor.util.MeasurementErrorResponse;
import ru.khamzin.springcourse.SpringRESTSensor.util.MeasurementException;
import ru.khamzin.springcourse.SpringRESTSensor.util.SensorValidator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ru.khamzin.springcourse.SpringRESTSensor.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    public List<SensorDTO> findAll() {
        return sensorsService.findAll()
                .stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("registration")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid SensorDTO sensorDTO,
                                           BindingResult bindingResult) {
        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        sensorsService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
