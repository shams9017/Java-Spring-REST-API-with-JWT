/**
 * OscarReviewResource.java - this is the resource file for the web API.
 * @author  Shams Ahsanullah
 * @version 1.0
 */


/**
 * read all movies
 curl -i -X GET http://localhost:8080/dmit2015-assignment02-shamsahsanullah/webapi/oscarreviews
 * read a single movie
 curl -i -X GET http://localhost:8080/dmit2015-assignment02-shamsahsanullah/webapi/oscarreviews/1
 * create a new Movie
 curl -i -X POST http://localhost:8080/dmit2015-assignment02-shamsahsanullah/webapi/oscarreviews \
 -d '{"title":"Face Off","category":"action","nominee":"worst ever","review":"cheesiest action movie"}' \
 -H 'Content-Type: application/json'
 * update the Movie we just created
 curl -i -X PUT http://localhost:8080/dmit2015-assignment02-shamsahsanullah/webapi/oscarreviews/5 \
 -d '{"id":5, "title":"Sharknado","category":"action","nominee":"Sci-Fi","review":"That was insane"}' \
 -H 'Content-Type: application/json'
 * delete the Movie we created
 curl -i -X DELETE http://localhost:8080/dmit2015-assignment02-shamsahsanullah/webapi/oscarreviews/5
 *
 */


package dmit2015.JWT.project.resource;

import dmit2015.JWT.project.entity.OscarReview;
import dmit2015.JWT.project.repository.OscarReviewRepo;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@Path("oscarreviews")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OscarReviewResource {
    @Inject
    private OscarReviewRepo _oscarReviewRepo;
    @RolesAllowed({"USER","ADMIN"})
    @GET
    public Response findAllReviews(){
        List<OscarReview> oscarreviews = _oscarReviewRepo.findAll();
        return Response.ok(oscarreviews).build();
    }

    @RolesAllowed({"USER","ADMIN"})
    @GET
    @Path("{id}")
    public Response findOneById(@PathParam("id") Long reviewId){
        if (reviewId == null)
        {
            throw new BadRequestException();
        }
        Optional<OscarReview> optionalReview = _oscarReviewRepo.findById(reviewId);
        if (optionalReview.isEmpty()){
            throw new NotFoundException();
        }
        OscarReview existingReview = optionalReview.get();
        return Response.ok(existingReview).build();
    }

    @RolesAllowed({"USER"})
    @POST
    public Response createReview(@Valid OscarReview newReview, @Context UriInfo uriInfo)
    {
        if (newReview == null)
        {
            throw new BadRequestException();
        }
        _oscarReviewRepo.add(newReview);
        URI locationUri = uriInfo.getAbsolutePathBuilder().path(newReview.getId().toString()).build();
        return Response.created(locationUri).build();
    }

    @RolesAllowed({"USER"})
    @PUT
    @Path("{id}")
    public Response updateReview(@PathParam("id") Long reviewId, @Valid OscarReview updatedReview){
        if (reviewId == null || updatedReview.getId() == null | !reviewId.equals(updatedReview.getId()))
        {
            throw new BadRequestException();
        }
        Optional<OscarReview> optionalReview = _oscarReviewRepo.findById(reviewId);
        if (optionalReview.isEmpty()){
            throw new NotFoundException();
        }
        OscarReview existingReview = optionalReview.get();
        existingReview.setReview(updatedReview.getReview());
        existingReview.setCategory(updatedReview.getCategory());
        existingReview.setNominee(updatedReview.getNominee());
        existingReview.setTitle(updatedReview.getTitle());
        existingReview.setVersion(updatedReview.getVersion());

        _oscarReviewRepo.update(existingReview);
        return Response.noContent().build();

    }

    @RolesAllowed({"ADMIN"})
    @DELETE
    @Path("{id}")
    public Response deleteReview(@PathParam("id") Long reviewId)
    {
        if(reviewId == null)
        {
            throw new BadRequestException();
        }

        Optional<OscarReview> optionalReview = _oscarReviewRepo.findById(reviewId);
        if (optionalReview.isEmpty()){
            throw new NotFoundException();
        }
        _oscarReviewRepo.remove(reviewId);
        return Response.noContent().build();
    }

}
