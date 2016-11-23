package com.example.common.validation.constraint;

import com.example.common.validation.validator.RegExWordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
    ElementType.TYPE,
    ElementType.FIELD,
    ElementType.METHOD,
    ElementType.PARAMETER,
    ElementType.CONSTRUCTOR,
    ElementType.LOCAL_VARIABLE,
    ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RegExWordValidator.class})
@Documented
public @interface RegExWord {

    String message() default "{validation.constraint.RegExWord.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
