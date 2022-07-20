package com.artemvasin.com.sensor.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Есть дождь или нет ? true/false ?")
    private Boolean raining;

    @ManyToOne(cascade = CascadeType.ALL)
    //   @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;


    @Column(name = "date_of_measurement")
    // @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private LocalDateTime dateOfMeasurement;

    public Registration() {
    }

    public Registration(Double value, Boolean raining) {
        this.value = value;
        this.raining = raining;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", dateOfMeasurement=" + dateOfMeasurement +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getDateOfMeasurement() {
        return dateOfMeasurement;
    }

    public void setDateOfMeasurement(LocalDateTime dateOfMeasurement) {
        this.dateOfMeasurement = dateOfMeasurement;
    }
}
