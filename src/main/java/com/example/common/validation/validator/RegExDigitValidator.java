package com.example.common.validation.validator;

import com.example.common.validation.constraint.RegExDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExDigitValidator implements ConstraintValidator<RegExDigit, String> {

    //<editor-fold desc="Constants">
    private static final String PATTERN = "^[0-9]*$";

    private static final Pattern PATTERN_COMPILED = Pattern.compile(PATTERN);
    //</editor-fold>

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(RegExDigit constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Bean Validation specification recommends to consider null values as being valid.
        // If null is not a valid value for an element, it should be annotated with @NotNull explicitly.
        if (value == null) {
            return true;
        }

        boolean isValid = false;

        Matcher matcher = PATTERN_COMPILED.matcher(value);
        isValid = matcher.matches();

        return isValid;
    }
    //</editor-fold>

}
