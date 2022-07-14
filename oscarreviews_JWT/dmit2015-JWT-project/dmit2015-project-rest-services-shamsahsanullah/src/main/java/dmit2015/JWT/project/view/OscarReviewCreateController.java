/**
 * OscarReviewCreateController.java - this is the controller for create actions
 * @author  Shams Ahsanullah
 * @version 1.0
 */

package dmit2015.JWT.project.view;


import dmit2015.JWT.project.entity.OscarReview;
import dmit2015.JWT.project.repository.OscarReviewRepo;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("currentoscarreviewCreateController")
@RequestScoped
public class OscarReviewCreateController {

    @Inject
    private OscarReviewRepo _oscarreviewRepository;

    @Getter
    private OscarReview newOscarReview = new OscarReview();

    public String onCreate() {
        String nextPage = "";
        try {
            _oscarreviewRepository.add(newOscarReview);
            Messages.addFlashGlobalInfo("Create was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful.");
        }
        return nextPage;
    }

}