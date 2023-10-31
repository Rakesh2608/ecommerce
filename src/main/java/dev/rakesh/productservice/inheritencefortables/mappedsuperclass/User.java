package dev.rakesh.productservice.inheritencefortables.mappedsuperclass;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}
