package dev.rakesh.productservice.inheritencefortables.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "msc_mentor")
public class Mentor extends User {
    private String company;
}
