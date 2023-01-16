package ru.khamzin.springcourse.SpringRESTSensor.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorsUtil {
    private String name;
    private String date;

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorResponse = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorResponse.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        throw new MeasurementException(errorResponse.toString());
    }
}
