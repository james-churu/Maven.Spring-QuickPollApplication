package io.zipcoder.tc_spring_poll_application.dtos.error;

import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class RestExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Not Found");
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setDetail("Exception Message :" + rnfe.getMessage());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDeveloperMessage("Error cause : " + rnfe.getCause());

        return new ResponseEntity<>(errorDetail , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(  MethodArgumentNotValidException manve, HttpServletRequest request){

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTitle("Not Found");
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setDetail("Exception Message :" + manve.getMessage());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDeveloperMessage("Error cause : " + manve.getCause());

        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors){
            List<ValidationError> vErrorList = errorDetail.getErrors().get(fe.getField());
            if(vErrorList == null){
                vErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(),vErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe,null));
            vErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
    }

}
