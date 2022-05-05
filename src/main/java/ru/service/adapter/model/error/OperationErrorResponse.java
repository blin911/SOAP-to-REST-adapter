package ru.service.adapter.model.error;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.*;


@Data
@AllArgsConstructor
public class OperationErrorResponse {

    private List<Error> errors ;

}

