/**
 * OscarReviewRepo.java - this is the repository class.
 * @author  Shams Ahsanullah
 * @version 1.0
 */

package dmit2015.JWT.project.repository;
import dmit2015.JWT.project.entity.OscarReview;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped //this enables the database to be stored in mem
@Transactional
public class OscarReviewRepo {
    @PersistenceContext(unitName = "h2database-jpa-pu")
    private EntityManager em;

    public void add(OscarReview newOscarReview) {
        //em.getTransaction().begin();
        //newOscarReview.setCreatedDateTime(LocalDateTime.of(2018, 2, 13, 15, 56));
        em.persist(newOscarReview);
        //em.getTransaction().commit();
    }

    public void update(OscarReview updatedReview) {
        Optional<OscarReview> optionalReview = findById(updatedReview.getId());
        if (optionalReview.isPresent()) {
            OscarReview existingReview = optionalReview.get();
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setCategory(updatedReview.getCategory());
            existingReview.setNominee(updatedReview.getNominee());
            existingReview.setReview(updatedReview.getReview());
            existingReview.setLastModifiedDateTime(updatedReview.getLastModifiedDateTime());
            em.merge(existingReview);
            em.flush();
        }
    }

    public void remove(OscarReview existingReview) {
        em.remove(em.merge(existingReview));
        em.flush();
    }

    public void remove(Long reviewID) {
        Optional<OscarReview> optionalReview = findById(reviewID);
        if (optionalReview.isPresent()) {
            OscarReview existingReview = optionalReview.get();
            remove(existingReview);
        }
    }

    public Optional<OscarReview> findById(Long movieID) {
        Optional<OscarReview> optionalMovie = Optional.empty();
        try {
            OscarReview querySingleResult = em.find(OscarReview.class, movieID);
            if (querySingleResult != null) {
                optionalMovie = Optional.of(querySingleResult);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return optionalMovie;
    }

    public List<OscarReview> findAll() {
        return em.createQuery(
                "SELECT m FROM OscarReview m "
                , OscarReview.class)
                .getResultList();
    }

    public List<OscarReview> findAllOrderByTitle() {
        return em.createQuery(
                "SELECT m FROM OscarReview m ORDER BY m.title"
                , OscarReview.class)
                .getResultList();
    }
    public void insertCreatedDateTime(OscarReview newReviewDT) {
        Optional<OscarReview> optionalReview = findById(newReviewDT.getId());
        if (optionalReview.isPresent()) {
            OscarReview existingReview = optionalReview.get();
            existingReview.setCreatedDateTime(newReviewDT.getCreatedDateTime());
            //existingReview.setCreatedDateTime(LocalDateTime.of(2018, 2, 13, 15, 56));
            em.merge(existingReview);
            em.flush();
        }
    }



}
