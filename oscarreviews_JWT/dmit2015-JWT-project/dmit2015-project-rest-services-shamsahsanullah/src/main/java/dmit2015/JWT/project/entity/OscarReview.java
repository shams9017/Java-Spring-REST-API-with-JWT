
/**
 * OscarReview.java - this is the entity OscarReview
 * @author  Shams Ahsanullah
 * @version 1.0
 */

package dmit2015.JWT.project.entity;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Entity                     // This class is map to database table with the same name as the class name
@Table(name = "oscarmovies")     // This entity class maps to a database table named oscarmovies
public class OscarReview implements Serializable {

    @Id                 // This is the primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // This primary key field is generated by the database
    private Long id;

    @Column(length = 200, nullable = false)
    @NotBlank(message = "The Title field is required.")
    @Size(min = 3, max = 200, message = "The field Title must be a string with a minimum length of {min} and a maximum length of {max}.")
    private String title;


    @Column(nullable = false, length = 200)
    @NotBlank(message = "The field Category is required.")
    private String category;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "The field nominee is required.")
    private String nominee;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "The field review is required.")
    private String review;

    // assignment 2
    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = true)
    private LocalDateTime lastModifiedDateTime;

    @Version
    private Integer version;

    @PrePersist
    private void beforePersist(){
        createdDateTime = LocalDateTime.now();
        lastModifiedDateTime = createdDateTime;
    }

    @PreUpdate
    private void beforeUpdate(){
        lastModifiedDateTime = LocalDateTime.now();

    }
}
