package se.emisten.movieguy.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.emisten.movieguy.dto.MovieDto;
import se.emisten.movieguy.entity.Movie;
import se.emisten.movieguy.mapper.Mapper;
import se.emisten.movieguy.repository.MovieRepository;

import java.net.URI;
import java.util.List;

@Path("/movies")

public class MovieController {

    @Inject
    MovieRepository movieRepository;

    @Inject
    Mapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getAll(@QueryParam("name") String name) {
        if (name == null)
            return mapper.map(movieRepository.findAll());

        return mapper.map(movieRepository.findAllByName(name));

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns movie object",
                    content = @Content(schema = @Schema(implementation = MovieDto.class))),
            @ApiResponse(responseCode = "404", description = "Id not found")})
    public Response getOne(@PathParam("id") Long id) {
        var movie = movieRepository.findOne(id);
        if (movie.isPresent())
            return Response.ok().entity(movie.get()).build();
        throw new NotFoundException("Id: " + id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(@Valid Movie movie) {
        movieRepository.insertMovie(movie);
        return Response.created(URI.create("movies/" + movie.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id) { movieRepository.deleteMovie(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upDate(@PathParam("id") Long id, MovieDto movieDto) {
        return Response.ok().entity(mapper.map(movieRepository.update(id, mapper.map(movieDto)))).build();
    }

}



















