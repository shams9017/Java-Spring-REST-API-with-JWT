package oscarreviews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oscarreviews.OscarReview;
import oscarreviews.OscarReviewRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OscarReviewRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new OscarReview("The Lion King", "Animation", "Best Animation", "Simba rules (literally)")));
            log.info("Preloading " + repository.save(new OscarReview("Saving Private Ryan", "War", "Best picture", "Tom Hanks was amazing as always" )));
        };
    }
}