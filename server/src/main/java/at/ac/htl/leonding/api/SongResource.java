package at.ac.htl.leonding.api;


import at.ac.htl.leonding.models.SongDOT;
import at.ac.htl.leonding.workloads.song.Song;
import at.ac.htl.leonding.workloads.song.SongRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("application/json")
@Path("song")
@Consumes("application/json")
public class SongResource {

    @Inject
    SongRepo repo;


    @GET
    @Path("all")
    public Response getAllSongs() {
        var allSongs = repo.getAll();
        return Response.ok(allSongs).build();
    }

    @POST
    @Transactional
    public Response addSong(SongDOT newSong) {
        Song song = new Song(newSong.getName(), newSong.getUrl());
        repo.persist(song);

        return Response.ok(song).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") Long id) {
        Song song = repo.findById(id);
        return (song == null
                ? Response.status(404)
                : Response.ok(song))
                .build();
    }


}
