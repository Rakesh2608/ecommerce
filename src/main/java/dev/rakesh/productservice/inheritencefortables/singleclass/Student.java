package dev.rakesh.productservice.inheritencefortables.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "sc_student")
@DiscriminatorValue(value = "2")
public class Student extends User {
    private int psp;
}
