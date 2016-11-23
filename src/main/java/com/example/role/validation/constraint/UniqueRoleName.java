package com.example.role.validation.constraint;

import com.example.role.validation.validator.UniqueRoleNameValidator;

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
@Constraint(validatedBy = {UniqueRoleNameValidator.class})
@Documented
public @interface UniqueRoleName {

    String message() default "{validation.constraint.UniqueRoleName.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
