package ru.khamzin.project3client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MeasurementsResponse {
    private List<MeasurementDTO> measurements;
}
