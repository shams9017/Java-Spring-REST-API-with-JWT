package oscarreviews;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class OscarReviewModelAssembler implements RepresentationModelAssembler<OscarReview, EntityModel<OscarReview>> {

    @Override
    public EntityModel<OscarReview> toModel(OscarReview oscarreview) {

        return EntityModel.of(oscarreview, //
                linkTo(methodOn(OscarReviewController.class).one(oscarreview.getId())).withSelfRel(),
                linkTo(methodOn(OscarReviewController.class).all()).withRel("oscarreviews"));
    }
}