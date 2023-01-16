package ru.khamzin.springcourse.SpringRESTSensor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;
}
