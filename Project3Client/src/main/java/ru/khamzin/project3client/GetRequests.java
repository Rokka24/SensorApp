package ru.khamzin.project3client;

import org.springframework.web.client.RestTemplate;
import ru.khamzin.project3client.dto.MeasurementDTO;
import ru.khamzin.project3client.dto.MeasurementsResponse;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetRequests {
    public static void main(String[] args) {
        List<Float> temperatures = getTemperaturesFromServer();
        temperatures.forEach(System.out::println);
    }

    private static List<Float> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        MeasurementsResponse jsonResponse = restTemplate.getForObject(url, MeasurementsResponse.class);

        if (jsonResponse == null || jsonResponse.getMeasurements() == null)
            return Collections.emptyList();

        return jsonResponse.getMeasurements()
                .stream()
                .map(MeasurementDTO::getValue)
                .collect(Collectors.toList());
    }
}
