/**
 * OscarReviewListController.java - this is the controller for list actions
 * @author  Shams Ahsanullah
 * @version 1.0
 */


package dmit2015.JWT.project.view;


import dmit2015.JWT.project.entity.OscarReview;
import dmit2015.JWT.project.repository.OscarReviewRepo;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("currentoscarreviewListController")
@ViewScoped
public class OscarReviewListController implements Serializable {

    @Inject
    private OscarReviewRepo _oscarreviewRepository;

    @Getter
    private List<OscarReview> oscarreviewList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            oscarreviewList = _oscarreviewRepository.findAll();
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}