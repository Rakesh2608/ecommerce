package dev.rakesh.productservice.inheritencefortables.tableperclass;


import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "tpc_ta")
public class TA extends User{
    private String averageRating;
}
