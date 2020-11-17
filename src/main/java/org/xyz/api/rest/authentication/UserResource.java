package org.xyz.api.rest.authentication;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_BAD_REQUEST;

import org.xyz.api.rest.authentication.Response.MessageResponse;
import org.xyz.api.rest.authentication.Request.UserRequest;
import org.xyz.core.component.application.service.UserService;
import org.xyz.exception.JsonError;
import org.xyz.utils.Mapper;

@Path("/users")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/")
    public Response users() {
        var users = userService.GetUsers();
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") final int id) {
        var user = userService.GetUser(id);
        var result = user != null? user : new MessageResponse("User not found.");
        return Response.ok(result).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteUserById(@PathParam("id") final int id) {
        var result = userService.RemoveUser(id);
        var message = new MessageResponse(result);
        return Response.ok(message).build();
    }

    @POST
    @Path("/")
    public Response addUser(UserRequest userRequest) {
        Response response = null;
        if(userRequest.getName().isEmpty() || userRequest.getEmail().isEmpty() || userRequest.getPassword().isEmpty())
        {
            JsonError error = JsonError.builder()
                             .status("400").title("Invalid Request")
                             .detail("Email, password, and name must never be empty.")
                             .build();

            return Response.status(SC_BAD_REQUEST).entity(error).build();
        }

        try{
            var userDto = Mapper.toUserDTO(userRequest);
            var id = userService.CreateUpdateUser(userDto);
            response = Response.ok(id).build();
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