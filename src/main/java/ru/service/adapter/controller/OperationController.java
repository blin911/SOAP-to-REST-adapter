package ru.service.adapter.controller;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.service.adapter.exception.RequestValueException;
import ru.service.adapter.mapper.Mapper;
import ru.service.adapter.model.OperationRequest;
import ru.service.adapter.model.OperationResponse;
import ru.service.adapter.service.CalculatorService;

import javax.validation.Valid;

//@Slf4j
@RestController
@RequestMapping("/calculator")

public class OperationController {
    private Validator requestValidator;

    private CalculatorService calculatorService;

    private Mapper mapper;

    public OperationController(CalculatorService calculatorService, Mapper mapper, @Qualifier("operationRequestValidator") Validator requestValidator){
        this.calculatorService = calculatorService;
        this.mapper = mapper;
        this.requestValidator = requestValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(requestValidator);
    }

    @PostMapping(path = "/add", produces = "application/json")
    public OperationResponse addNumbers(@RequestBody @Valid  OperationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestValueException(
                    mapper.objectErrorsToValidationErrors(
                            bindingResult.getAllErrors()
                    )
            );

        int first, second;
        first= Integer.parseInt(request.getFirstNumber());
        second = Integer.parseInt(request.getSecondNumber());

  //      log.info("{}",request);
  //      log.info("{}",first,second);

        return new OperationResponse(
                calculatorService.add(
                        Integer.parseInt(request.getFirstNumber()),
                        Integer.parseInt(request.getSecondNumber())
                )

        );
    }

    @PostMapping(path = "/divide", produces = "application/json")
    public OperationResponse divideNumbers(@RequestBody @Valid OperationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestValueException(
                    mapper.objectErrorsToValidationErrors(
                            bindingResult.getAllErrors()
                    )
            );

        return new OperationResponse(
                calculatorService.divide(
                        Integer.valueOf(request.getFirstNumber()),
                        Integer.valueOf(request.getSecondNumber())
                )
        );
    }

    @PostMapping(path = "/multiply", produces = "application/json")
    public OperationResponse multiplyNumbers(@RequestBody @Valid OperationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestValueException(
                    mapper.objectErrorsToValidationErrors(
                            bindingResult.getAllErrors()
                    )
            );

        return new OperationResponse(
                calculatorService.multiply(
                        Integer.valueOf(request.getFirstNumber()),
                        Integer.valueOf(request.getSecondNumber())
                )
        );
    }

    @PostMapping(path = "/subtract", produces = "application/json")
    public OperationResponse subtractNumbers(@RequestBody @Valid OperationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new RequestValueException(
                    mapper.objectErrorsToValidationErrors(
                            bindingResult.getAllErrors()
                    )
            );

        return new OperationResponse(
                calculatorService.subtract(
                        Integer.valueOf(request.getFirstNumber()),
                        Integer.valueOf(request.getSecondNumber())
                )
        );
    }


}
