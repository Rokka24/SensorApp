package ru.khamzin.springcourse.SpringRESTSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
@Data
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(value = -100, message = "Temperature should be more than -100")
    @Max(value = 100, message = "Temperature should be less than 100")
    @NotNull(message = "Temperature should not be empty")
    private Float value;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;
    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement(float value, boolean raining) {
        this.value = value;
        this.raining = raining;
    }
}
