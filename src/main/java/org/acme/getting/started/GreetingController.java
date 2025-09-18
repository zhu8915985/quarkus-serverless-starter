package org.acme.getting.started;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/hello")
public class GreetingController {

    @Inject
    GreetingService service;
    
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
    
    // 获取所有用户
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    // 根据ID获取用户
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }
    
    // 创建新用户
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserResource(User user) {
        try {
            User newUser = userService.createUser(user);
            return Response.status(Response.Status.CREATED).entity(newUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error creating user: " + e.getMessage()).build();
        }
    }
    
    // 更新用户
    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") Long id, User userDetails) {
        User user = userService.updateUser(id, userDetails);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found").build();
        }
        return Response.ok(user).build();
    }
    
    // 删除用户
    @DELETE
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User not found").build();
        }
        return Response.noContent().build();
    }
}