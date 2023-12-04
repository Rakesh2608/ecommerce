package dev.rakesh.productservice.client.AuthClient.dtos;

import dev.rakesh.productservice.client.AuthClient.models.SessionStatus;
import dev.rakesh.productservice.client.AuthClient.models.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;

}
