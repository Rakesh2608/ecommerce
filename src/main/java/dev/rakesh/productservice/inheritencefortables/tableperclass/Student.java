package dev.rakesh.productservice.inheritencefortables.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
//@Entity(name = "tpc_student")
public class Student extends User{
    private int psp;
}
