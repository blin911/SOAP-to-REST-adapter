package ru.service.adapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.service.adapter.model.OperationRequest;
import ru.service.adapter.validator.OperationRequestValidator;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTest {
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    static {
        messageSource.setBasename("message");
    }

    @Autowired
    private OperationRequestValidator operationRequestValidator;

    @Test
    public void testEmptyError(){
        final OperationRequest operationRequest = new OperationRequest();
        operationRequest.setFirstNumber("2");
        operationRequest.setSecondNumber("2");

        final DataBinder dataBinder = new DataBinder(operationRequest);
        dataBinder.addValidators(operationRequestValidator);
        dataBinder.validate();

        Assert.assertTrue(!dataBinder.getBindingResult().hasErrors());
    }

    @Test
    public void testEmptyValue(){
        final OperationRequest operationRequest = new OperationRequest();
        operationRequest.setFirstNumber("");
        operationRequest.setSecondNumber("3");

        final DataBinder dataBinder = new DataBinder(operationRequest);
        dataBinder.addValidators(operationRequestValidator);
        dataBinder.validate();

        List<ObjectError> errors = dataBinder.getBindingResult().getAllErrors();

        Assert.assertTrue(errors.size() == 1);
        Assert.assertTrue(errors.get(0) instanceof FieldError);
        Assert.assertTrue(((FieldError) errors.get(0)).getField().equals("firstNumber"));
        Assert.assertTrue(errors.get(0).getDefaultMessage().equals("Р·РЅР°С‡РµРЅРёРµ РЅРµ РјРѕР¶РµС‚ Р±С‹С‚СЊ РїСѓСЃС‚С‹Рј"));
    }

    @Test
    public void testNullValue(){
        final OperationRequest operationRequest = new OperationRequest();
        operationRequest.setFirstNumber("3");
        operationRequest.setSecondNumber(null);

        final DataBinder dataBinder = new DataBinder(operationRequest);
        dataBinder.addValidators(operationRequestValidator);
        dataBinder.validate();

        List<ObjectError> errors = dataBinder.getBindingResult().getAllErrors();

        Assert.assertTrue(errors.size() == 1);
        Assert.assertTrue(errors.get(0) instanceof FieldError);
        Assert.assertTrue(((FieldError) errors.get(0)).getField().equals("secondNumber"));
        Assert.assertTrue(errors.get(0).getDefaultMessage().equals("Р·РЅР°С‡РµРЅРёРµ РЅРµ РјРѕР¶РµС‚ РѕС‚СЃСѓС‚СЃС‚РІРѕРІР°С‚СЊ"));
    }

    @Test
    public void testValueIsNotInt(){
        final OperationRequest operationRequest = new OperationRequest();
        operationRequest.setFirstNumber("РґРІР°");
        operationRequest.setSecondNumber("2");

        final DataBinder dataBinder = new DataBinder(operationRequest);
        dataBinder.addValidators(operationRequestValidator);
        dataBinder.validate();

        List<ObjectError> errors = dataBinder.getBindingResult().getAllErrors();

        Assert.assertTrue(errors.size() == 1);
        Assert.assertTrue(errors.get(0) instanceof FieldError);
        Assert.assertTrue(((FieldError) errors.get(0)).getField().equals("firstNumber"));
        Assert.assertTrue(errors.get(0).getDefaultMessage().equals("Р·РЅР°С‡РµРЅРёРµ РґРѕР»Р¶РЅРѕ РёРјРµС‚СЊ С‚РёРї int"));
    }

    @Test
    public void testTwoErrors(){
        final OperationRequest operationRequest = new OperationRequest();
        operationRequest.setFirstNumber("");
        operationRequest.setSecondNumber(null);

        final DataBinder dataBinder = new DataBinder(operationRequest);
        dataBinder.addValidators(operationRequestValidator);
        dataBinder.validate();

        List<ObjectError> errors = dataBinder.getBindingResult().getAllErrors();

        Assert.assertTrue(errors.size() == 2);
    }


}
