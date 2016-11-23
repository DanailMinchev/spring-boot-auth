package com.example.common.validation.validator;

import com.example.common.validation.constraint.RegExGraph;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExGraphValidator implements ConstraintValidator<RegExGraph, String> {

    //<editor-fold desc="Constants">
    /**
     * A visible character: [\p{Alnum}\p{Punct}]
     * <p/>
     * \p{Alnum} - An alphanumeric character: [a-zA-Z0-9]
     * \p{Punct} - Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
     */
    private static final String PATTERN = "^[\\p{Graph}]*$";

    private static final Pattern PATTERN_COMPILED = Pattern.compile(PATTERN);
    //</editor-fold>

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(RegExGraph constraintAnnotation) {
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
