package dev.rakesh.productservice.inheritencefortables.mappedsuperclass;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "msc_ta")
public class TA extends User {
    private String averageRating;
}
