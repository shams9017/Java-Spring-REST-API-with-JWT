/**
 * OscarReview.java - this is the entity OscarReview
 * @author  Shams Ahsanullah
 * @version 1.0
 */
package oscarreviews;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table(name = "oscarmovies")
public class OscarReview implements Serializable {

    private @Id @GeneratedValue Long id;
    private String title;
    private String category;
    private String nominee;
    private String review;
    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = true)
    private LocalDateTime lastModifiedDateTime;

    @PrePersist
    private void beforePersist(){
        createdDateTime = LocalDateTime.now();
        lastModifiedDateTime = createdDateTime;
    }

    @PreUpdate
    private void beforeUpdate(){
        lastModifiedDateTime = LocalDateTime.now();

    }

    public OscarReview() {}

    OscarReview(String title, String category, String nominee, String review) {

        this.title = title;
        this.category = category;
        this.nominee = nominee;
        this.review = review;
        //this.createdDateTime = createdDateTime;
        //this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public String getNominee() {
        return this.nominee;
    }

    public String getReview() {
        return this.review;
    }

    public LocalDateTime getCreatedDateTime() {
        return this.createdDateTime;
    }

    public LocalDateTime getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public void setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof OscarReview))
            return false;
        OscarReview oscarreview = (OscarReview) o;
        return Objects.equals(this.id, oscarreview.id) && Objects.equals(this.title, oscarreview.title)
                && Objects.equals(this.category, oscarreview.category) && Objects.equals(this.nominee, oscarreview.nominee)
                && Objects.equals(this.review, oscarreview.review)
                && Objects.equals(this.createdDateTime, oscarreview.createdDateTime)
                && Objects.equals(this.lastModifiedDateTime, oscarreview.lastModifiedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.category, this.nominee, this.review, this.createdDateTime, this.lastModifiedDateTime);
    }



}
