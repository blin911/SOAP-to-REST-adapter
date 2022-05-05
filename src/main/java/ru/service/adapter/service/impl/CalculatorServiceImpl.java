package ru.service.adapter.service.impl;


import org.springframework.stereotype.Service;
import ru.service.adapter.client.*;
import ru.service.adapter.exception.SoapResultException;
import ru.service.adapter.model.error.Error;
import ru.service.adapter.service.CalculatorService;
import ru.service.adapter.wsdl.*;


@Service

public class CalculatorServiceImpl implements CalculatorService {

    private static final String NO_RESULT_IN_RESPONSE_FROM_SOAP_CALCULATOR_SERVICE = "No result in response from SOAP calculator service";

    private CalculatorSOAPClient calculatorSOAPClient;

    public CalculatorServiceImpl(CalculatorSOAPClient calculatorSOAPClient){
        this.calculatorSOAPClient = calculatorSOAPClient;

    }

    public int add(int firstNumber, int secondNumber) {
        Add add = new Add();

        add.setIntA(firstNumber);
        add.setIntB(secondNumber);

        AddResponse response = calculatorSOAPClient.add(add);

        if (response == null)
            throw new SoapResultException(
                    new Error(NO_RESULT_IN_RESPONSE_FROM_SOAP_CALCULATOR_SERVICE)
            );

        return response.getAddResult();
    }

    public int divide(int firstNumber, int secondNumber) {
        Divide divide = new Divide();
        divide.setIntA(firstNumber);
        divide.setIntB(secondNumber);
        DivideResponse response = calculatorSOAPClient.divide(divide);

        if (response == null)
            throw new SoapResultException(
                    new Error(NO_RESULT_IN_RESPONSE_FROM_SOAP_CALCULATOR_SERVICE)
            );

        return response.getDivideResult();
    }

    public int multiply(int firstNumber, int secondNumber) {
        Multiply multiply = new Multiply();
        multiply.setIntA(firstNumber);
        multiply.setIntB(secondNumber);
        MultiplyResponse response = calculatorSOAPClient.multiply(multiply);

        if (response == null)
            throw new SoapResultException(
                    new Error(NO_RESULT_IN_RESPONSE_FROM_SOAP_CALCULATOR_SERVICE)
            );

        return response.getMultiplyResult();
    }

    public int subtract(int firstNumber, int secondNumber) {
        Subtract subtract = new Subtract();
        subtract.setIntA(firstNumber);
        subtract.setIntB(secondNumber);
        SubtractResponse response = calculatorSOAPClient.subtract(subtract);

        if (response == null)
            throw new SoapResultException(
                    new Error(NO_RESULT_IN_RESPONSE_FROM_SOAP_CALCULATOR_SERVICE)
            );

        return response.getSubtractResult();
    }

}
