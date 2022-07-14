package oscarreviews;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import oscarreviews.OscarReviewNotFoundException;

@ControllerAdvice
class OscarReviewNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(OscarReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(OscarReviewNotFoundException ex) {
        return ex.getMessage();
    }
}