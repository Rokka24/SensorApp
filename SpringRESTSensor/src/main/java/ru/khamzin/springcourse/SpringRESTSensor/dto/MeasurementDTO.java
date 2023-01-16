package ru.khamzin.springcourse.SpringRESTSensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDTO {

    @Min(value = -100, message = "Temperature should be more than -100")
    @Max(value = 100, message = "Temperature should be less than 100")
    @NotNull(message = "Temperature should not be empty")
    private Float value;
    @NotNull(message = "Rain status should not be empty")
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;
}
