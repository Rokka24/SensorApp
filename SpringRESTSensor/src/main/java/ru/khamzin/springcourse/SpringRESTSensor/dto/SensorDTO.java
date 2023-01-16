package ru.khamzin.springcourse.SpringRESTSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SensorDTO {

    @NotEmpty(message = "Sensor name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be in range of 3 to 30 symbols")
    private String name;
}
