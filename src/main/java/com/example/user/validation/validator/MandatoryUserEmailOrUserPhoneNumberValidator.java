package com.example.user.validation.validator;

import com.example.user.validation.constraint.MandatoryUserEmailOrUserPhoneNumber;
import com.example.user.validation.definition.UserDefinitionMandatoryUserEmailOrUserPhoneNumberValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MandatoryUserEmailOrUserPhoneNumberValidator implements ConstraintValidator<MandatoryUserEmailOrUserPhoneNumber, UserDefinitionMandatoryUserEmailOrUserPhoneNumberValidator> {

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(MandatoryUserEmailOrUserPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDefinitionMandatoryUserEmailOrUserPhoneNumberValidator value, ConstraintValidatorContext context) {
        // Bean Validation specification recommends to consider null values as being valid.
        // If null is not a valid value for an element, it should be annotated with @NotNull explicitly.
        if (value == null) {
            return true;
        }

        boolean isValid = (value.getEmail() != null) || (value.getPhoneNumber() != null);

        return isValid;
    }
    //</editor-fold>

}
