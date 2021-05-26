package net.josephmbuku.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//  When a record is not found in the database, this exception is thrown.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(String message){
        //  Pass this message to the super class
        super(message);
    }

}
