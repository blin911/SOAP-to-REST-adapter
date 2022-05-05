package ru.service.adapter.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.service.adapter.model.error.Error;

import java.util.*;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class RequestValueException extends RuntimeException {

    private List<Error> errors;

    public RequestValueException(List<Error> errors) {

        this.errors = errors;
    }


}
