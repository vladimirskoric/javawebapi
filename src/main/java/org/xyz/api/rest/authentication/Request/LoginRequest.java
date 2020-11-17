package org.xyz.api.rest.authentication.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
