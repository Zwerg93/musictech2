package at.ac.htl.leonding.api;

import at.ac.htl.leonding.models.PlaylistDOT;
import at.ac.htl.leonding.models.UserDOT;
import at.ac.htl.leonding.workloads.user.User;
import at.ac.htl.leonding.workloads.user.UserRepoImpl;
import at.ac.htl.leonding.workloads.user.UserService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("application/json")
@Path("user")
@Consumes("application/json")
public class UserResource {

    private final UserService userService;
    @Inject
    private UserRepoImpl userRepo;
    private User user;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    //private final Playlist hobbyService;


    @POST
    @Transactional
    public Response addUser(UserDOT newUser) {
        return Response.ok(this.userService.addUser(
                newUser.getUsername(),
                newUser.getName(),
                newUser.getLastname(),
                newUser.getEmail(),
                newUser.getPassword())).build();
    }

    @POST
    @Transactional
    @Path("add/{id}/")
    public Response addPlaylist(@PathParam("id") Long id, PlaylistDOT newPlaylist) {

        User user = this.userService.getUser(id);
        if (user == null) {
            return Response.status(404).build();
        }

        this.userService.addPlaylist(user, newPlaylist.getName(), newPlaylist.getId());
        return Response.ok().build();
    }


    @GET
    @Path("all")
    public Response getallUser() {
        var allPeople = this.userService.getAll();
        return Response.ok(allPeople).build();
    }

    @GET
    @Path("getuser/{username}")
    public Response getUserByName(@PathParam("username") String username) {
        User user = this.userService.getUserbyname(username);
        System.out.println(username + " fasd tets");
        return Response.ok(user).build();
    }
    @GET
    @Path("getPlalist/{username}")
    public Response getPlaylistByUser(@PathParam("username") String username) {
        var allPlaylists = this.userService.getPlaylistbyname(username);
        System.out.println(username + " fasd tets");
        return Response.ok(allPlaylists).build();
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") Long id) {
        User user = this.userService.getUser(id);
        return (user == null
                ? Response.status(404)
                : Response.ok(user))
                .build();
    }

    @POST
    @Path("autorize/{username}/{value}")
    public Response login(@PathParam("value") String password,
                          @PathParam("username") String username) {
        //repo.getPassword(username);
        System.out.println(userRepo.getPassword(username) + " " + password);

        if (userRepo.getPassword(username).equals(password)) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }


    }

}
