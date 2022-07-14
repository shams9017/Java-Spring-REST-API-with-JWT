package oscarreviews;
/*
 * curl -v -X GET localhost:8080/oscarreviews -H 'Content-Type:application/json'
 * curl -v -X GET localhost:8080/oscarreviews/1 -H 'Content-Type:application/json'
 * curl -v -X POST localhost:8080/oscarreviews -H 'Content-Type:application/json' -d '{"title": "The Green Book", "category": "Drama", "nominee":"Best Actor", "review":"I liked it"}'
 * curl -v -X PUT localhost:8080/oscarreviews/3 -H 'Content-Type:application/json' -d '{"title": "The Green Book", "review": "too good"}'
 * curl -v -X DELETE localhost:8080/oscarreviews/1
 * */


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class OscarReviewController {

    private final OscarReviewRepository repository;

    private final OscarReviewModelAssembler assembler;


    OscarReviewController(OscarReviewRepository repository, OscarReviewModelAssembler assembler) {

        this.repository = repository;
        this.assembler = assembler;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/oscarreviews")
    CollectionModel<EntityModel<OscarReview>> all() {

        List<EntityModel<OscarReview>> oscarreviews = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(oscarreviews, linkTo(methodOn(OscarReviewController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/oscarreviews")
    ResponseEntity<?> newOscarReview(@RequestBody OscarReview newOscarReview) {

        EntityModel<OscarReview> entityModel = assembler.toModel(repository.save(newOscarReview));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item

    @GetMapping("/oscarreviews/{id}")
    EntityModel<OscarReview> one(@PathVariable Long id) {

        OscarReview oscarreview = repository.findById(id) //
                .orElseThrow(() -> new OscarReviewNotFoundException(id));

        return assembler.toModel(oscarreview);
    }

    @PutMapping("/oscarreviews/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody OscarReview newOscarReview, @PathVariable Long id) {

        OscarReview updatedOscarReview = repository.findById(id) //
                .map(oscarreview -> {
                    oscarreview.setTitle(newOscarReview.getTitle());
                    oscarreview.setCategory(newOscarReview.getCategory());
                    oscarreview.setNominee(newOscarReview.getNominee());
                    oscarreview.setReview(newOscarReview.getReview());
                    oscarreview.setLastModifiedDateTime(newOscarReview.getLastModifiedDateTime());

                    return repository.save(oscarreview);
                }) //
                .orElseGet(() -> {
                    newOscarReview.setId(id);
                    return repository.save(newOscarReview);
                });

        EntityModel<OscarReview> entityModel = assembler.toModel(updatedOscarReview);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/oscarreviews/{id}")
    ResponseEntity<?> deleteOscarReview(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}