package ru.service.adapter.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class OperationRequest {
    @ApiModelProperty
    private String firstNumber;
    @ApiModelProperty
    private String secondNumber;

}
