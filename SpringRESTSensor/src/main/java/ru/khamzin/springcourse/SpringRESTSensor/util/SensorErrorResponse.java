package ru.khamzin.springcourse.SpringRESTSensor.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorErrorResponse {
    private String name;
    private String date;
}
