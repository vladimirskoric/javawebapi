package org.xyz.api.rest.authentication;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_BAD_REQUEST;

import org.xyz.api.rest.authentication.Request.LoginRequest;
import org.xyz.api.rest.authentication.Response.MessageResponse;
import org.xyz.core.component.application.service.LoginService;
import org.xyz.exception.JsonError;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    LoginService loginService;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        Response response = Response.ok().build();
        var email = request.getEmail();
        var password = request.getPassword();

        if(email.isEmpty() || password.isEmpty())
        {
            JsonError error = JsonError.builder()
                             .status("400").title("Invalid Request")
                             .detail("Please check email or password")
                             .build();

            return Response.status(SC_BAD_REQUEST).entity(error).build();
        }

        try{
            var result = loginService.login(email, password);
            var loginResponse = new MessageResponse(result);       
            response = Response.ok(loginResponse).build();
        }catch(Exception e){
            JsonError error = JsonError.builder()
                              .status("500")
                              .title("An Unknown Error has occurred")
                              .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }
        

        return response;
    }
}