package com.example.common.validation.constraint;

import com.example.common.validation.validator.RegExUuidValidator;

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
@Constraint(validatedBy = {RegExUuidValidator.class})
@Documented
public @interface RegExUuid {

    String message() default "{validation.constraint.RegExUuid.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
