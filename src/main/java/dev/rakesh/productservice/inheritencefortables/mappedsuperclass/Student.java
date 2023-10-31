package dev.rakesh.productservice.inheritencefortables.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "msc_student")
public class Student extends User {
    private int psp;
}
