package dev.rakesh.productservice.inheritencefortables.singleclass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "sc_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private String averageRating;
}
