package org.example.security3.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarDto {

    @Size(min = 2,max = 20)
    @NotBlank
    private String model;
    @Min(value = 50)
    @Max(value = 2000)
    private Integer hp;
    @Size(min = 2,max = 20)
    @NotBlank
    private String vin;
}
