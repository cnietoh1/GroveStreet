package es.carlosnh.grovestreet.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String ERRORES_TAG = "errores";

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                             HttpHeaders headers,
                                                             HttpStatusCode status, WebRequest request) {

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
//    List<String> errores = ex.getBindingResult().getFieldErrors().stream()
//        .map(x -> x.getDefaultMessage())
//        .toList();

    BindingResult result = ex.getBindingResult();
    problemDetail.setProperty("mensaje", "Validation failed for object='" + result.getObjectName()
        + "'. " + "Error count: " + result.getErrorCount());
    problemDetail.setProperty(ERRORES_TAG, result.getAllErrors());

    return ResponseEntity.badRequest().body(problemDetail);

  }

  @ExceptionHandler( {IllegalArgumentException.class, IllegalStateException.class} )
  public ProblemDetail handleIllegalArgumentException(Exception ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
  }

  @ExceptionHandler( DataIntegrityViolationException.class )
  public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMostSpecificCause().getMessage());
  }

  @ExceptionHandler( Exception.class )
  @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
  public ProblemDetail handleDefaultException(Exception ex) {
    log.warn("~~~~~~~~~~~~~~~~~~~~~~ APP HANDLING EXCEPTION ~~~~~~~~~~~~~~~~~~~~~~\n", ex);
    return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
  }

}
