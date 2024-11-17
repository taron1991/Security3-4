package org.example.security3.models;


import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String model;
    private int hp;
    private String vin;

    @Override
    public String toString() {
        return "Car{" +
                ", model='" + model + '\'' +
                ", hp=" + hp +
                ", vin='" + vin + '\'' +
                '}';
    }
}
