package dev.rakesh.productservice.client.AuthClient;
import dev.rakesh.productservice.client.AuthClient.dtos.ValidateResponseDto;
import dev.rakesh.productservice.client.AuthClient.dtos.ValidateTokenRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationClient {
    private final RestTemplateBuilder restTemplateBuilder;

    public ValidateResponseDto validate(String token,Long userId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ValidateTokenRequestDto validateTokenRequestDto=new ValidateTokenRequestDto();
        validateTokenRequestDto.setUserId(userId);
        validateTokenRequestDto.setToken(token);
        ResponseEntity<ValidateResponseDto> responseEntity=
                restTemplate.postForEntity("http://localhost:9000/auth/validate"
                ,validateTokenRequestDto,ValidateResponseDto.class);
        return responseEntity.getBody();
    }
}
