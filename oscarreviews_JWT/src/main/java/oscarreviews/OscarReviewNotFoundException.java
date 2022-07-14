package oscarreviews;

class OscarReviewNotFoundException extends RuntimeException {

    OscarReviewNotFoundException(Long id) {
        super("Could not find oscar review " + id);
    }
}