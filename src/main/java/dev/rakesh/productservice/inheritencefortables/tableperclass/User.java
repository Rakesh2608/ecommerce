package dev.rakesh.productservice.inheritencefortables.tableperclass;

import jakarta.persistence.*;
import lombok.Data;

@Data
//@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
}
