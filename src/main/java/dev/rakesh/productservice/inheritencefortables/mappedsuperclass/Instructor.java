package dev.rakesh.productservice.inheritencefortables.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "msc_instructor")
public class Instructor extends User {
    private String skills;
}
