package com.example.user.validation.validator;

import com.example.user.domain.jpa.User;
import com.example.user.repository.jpa.UserRepository;
import com.example.user.validation.constraint.UniqueUserUsername;
import com.example.user.validation.definition.UserDefinitionUniqueUserUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserUsernameValidator implements ConstraintValidator<UniqueUserUsername, UserDefinitionUniqueUserUsernameValidator> {

    //<editor-fold desc="Fields">
    @Autowired
    private UserRepository userRepository = null;
    //</editor-fold>

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(UniqueUserUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDefinitionUniqueUserUsernameValidator value, ConstraintValidatorContext context) {
        // Bean Validation specification recommends to consider null values as being valid.
        // If null is not a valid value for an element, it should be annotated with @NotNull explicitly.
        if ((value == null) || (value.getUsername() == null)) {
            return true;
        }

        boolean isValid = false;

        User foundItem = userRepository.findByUsername(value.getUsername());
        if (foundItem == null) {
            isValid = true;
        } else {
            // check if it is same entity in case of "edit"
            if (value.getId() != null) {
                isValid = foundItem.getId().equals(value.getId());
            }
        }

        return isValid;
    }
    //</editor-fold>

}
