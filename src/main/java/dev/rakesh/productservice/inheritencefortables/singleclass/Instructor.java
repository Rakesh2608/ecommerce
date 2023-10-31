package dev.rakesh.productservice.inheritencefortables.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "sc_instructor")
@DiscriminatorValue(value = "4")
public class Instructor extends User {
    private String skills;
}
