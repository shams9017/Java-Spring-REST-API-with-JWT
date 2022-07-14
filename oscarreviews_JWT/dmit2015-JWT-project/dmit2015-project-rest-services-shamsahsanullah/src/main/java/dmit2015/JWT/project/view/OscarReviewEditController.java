/**
 * OscarReviewEditController.java - this is the controller for edit actions
 * @author  Shams Ahsanullah
 * @version 1.0
 */

package dmit2015.JWT.project.view;


import dmit2015.JWT.project.entity.OscarReview;
import dmit2015.JWT.project.repository.OscarReviewRepo;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named("currentoscarreviewEditController")
@ViewScoped
public class OscarReviewEditController implements Serializable {

    @Inject
    private OscarReviewRepo _oscarreviewRepository;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private Long editId;

    @Getter
    private OscarReview existingOscarReview;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            Optional<OscarReview> optionalEntity = _oscarreviewRepository.findById(editId);
            optionalEntity.ifPresent(entity -> existingOscarReview = entity);
        }
    }

    public String onUpdate() {
        String nextPage = "";
        try {
            _oscarreviewRepository.update(existingOscarReview);
            Messages.addFlashGlobalInfo("Update was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Update was not successful.");
        }
        return nextPage;
    }

    public String onDelete() {
        String nextPage = "";
        try {
            _oscarreviewRepository.remove(existingOscarReview.getId());
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}