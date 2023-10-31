package dev.rakesh.productservice.inheritencefortables.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "sc_mentor")
@DiscriminatorValue(value = "3")
public class Mentor extends User {
    private String company;
}
