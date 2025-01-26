package gr.uom.primeride.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import gr.uom.primeride.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle @Valid errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withPath(request.getDescription(false))
                .withMessage("Entity Validation Failed due to Invalid Request Body");

        for (FieldError error : ex.getBindingResult().getFieldErrors())
        {
            errorBuilder.addDetail(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    // Handle missing headers in request
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(
            MissingRequestHeaderException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withPath(request.getDescription(false))
                .withMessage("Required request header is missing");

        errorBuilder.addDetail(ex.getHeaderName(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    @ExceptionHandler(FormException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleFormException(
            FormException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.CONFLICT.value())
                .withPath(request.getDescription(false))
                .withMessage(ex.getMessage());

        ex.getDetails().forEach(errorBuilder::addDetail);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBuilder.build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withPath(request.getDescription(false))
                .withMessage(ex.getMessage());

        ex.getDetails().forEach(errorBuilder::addDetail);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withPath(request.getDescription(false))
                .withMessage("Empty Required Parameters in Requests");

        // Extract detailed errors from the ConstraintViolations
        for (var violation : ex.getConstraintViolations())
        {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errorBuilder.addDetail(field, message);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request)
    {
        var errorBuilder = new ErrorResponse.Builder()
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withPath(request.getDescription(false))
                .withMessage("Invalid request body");

        // Extract detailed error from the exception message
        Throwable rootCause = ex.getRootCause();

        if (rootCause instanceof InvalidFormatException)
        {
            InvalidFormatException invalidFormatException = (InvalidFormatException) rootCause;
            String field = invalidFormatException.getPath().stream()
                    .map(reference -> reference.getFieldName())
                    .reduce((first, second) -> second) // Get the last field name in the path
                    .orElse("Unknown field");
            String acceptedValues = invalidFormatException.getTargetType().isEnum()
                    ? String.join(", ", ((Class<? extends Enum<?>>) invalidFormatException.getTargetType()).getEnumConstants().toString())
                    : "Invalid value";
            errorBuilder.addDetail(field, "Invalid value provided");
        }
        else
        {
            errorBuilder.addDetail("error", "Malformed JSON or invalid input");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBuilder.build());
    }

}
