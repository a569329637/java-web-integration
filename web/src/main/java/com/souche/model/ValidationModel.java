package com.souche.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author guishangquan
 * @Description
 * @Package com.souche.model
 * @date 17/5/9
 **/
@Data
public class ValidationModel {

    private String userId;

    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @Pattern(regexp = "\\S{6,30}")
    private String password;

    @Length(min=2, max = 1000)
    private String realName;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @DecimalMin(value = "1000.00")
    @DecimalMax(value = "10000.00")
    @NumberFormat(pattern = "#,###.##")
    private Long salary;
}
