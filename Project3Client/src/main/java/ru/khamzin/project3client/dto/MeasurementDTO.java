package ru.khamzin.project3client.dto;

import lombok.Data;

@Data
public class MeasurementDTO {
    private Float value;
    private Boolean raining;
    private SensorDTO sensor;
}
