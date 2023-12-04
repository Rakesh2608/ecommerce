package dev.rakesh.productservice.client.AuthClient.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private List<Role> roles;
}
