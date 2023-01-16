package ru.khamzin.project3client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        float maxTemperature = 45.0f;
        String sensorName = "Sensor N6";
        registerSensor(sensorName);


        randomizeMeasurements(maxTemperature, sensorName);
    }

    private static void randomizeMeasurements(float maxTemperature, String sensorName) {
        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            sendMeasurement((float) (random.nextDouble() * maxTemperature),
                    random.nextBoolean(), sensorName);
        }
    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        postRequest(url, jsonData);
    }

    private static void sendMeasurement(float value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        postRequest(url, jsonData);
    }

    private static void postRequest(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Measurement were sent successfully");
        } catch (HttpClientErrorException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }
}
