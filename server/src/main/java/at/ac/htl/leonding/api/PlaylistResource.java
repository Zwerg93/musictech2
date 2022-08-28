package at.ac.htl.leonding.api;

import at.ac.htl.leonding.models.PlaylistDOT;
import at.ac.htl.leonding.workloads.playlist.Playlist;
import at.ac.htl.leonding.workloads.playlist.PlaylistService;
import at.ac.htl.leonding.workloads.user.User;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("application/json")
@Path("user")
@Consumes("application/json")
public class PlaylistResource {
    private final PlaylistService playlistService;

    public PlaylistResource(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Path("all")
    public Response getAllPlaylists() {
        var allplaylists = this.playlistService.getAll();
        return Response.ok(allplaylists).build();
    }

    @GET
    @Path("{id}")
    public Response getPlaylistbyId(@PathParam("id") Long id) {
        Playlist playlist = this.playlistService.getPlaylistbyid(id);
        return (playlist == null ? Response.status(404) : Response.ok(playlist)).build();
    }


    @POST
    @Transactional
    @Path("add/{id}/{songid}")
    public Response addSong(@PathParam("id") Long id, @PathParam("songid") Long songid) {

        Playlist playlist = this.playlistService.getPlaylistbyid(id);
        if (playlist == null) {
            return Response.status(404).build();
        }

        this.playlistService.addSong(playlist, songid);
        return Response.ok().build();
    }


}
