package org.xyz.api.rest.authentication.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageResponse {

    public MessageResponse(String message){
        this.setMessage(message);
    }
    
    @JsonProperty("message")
    private String message;
}