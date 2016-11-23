package com.example.role.validation.validator;

import com.example.role.domain.jpa.Role;
import com.example.role.repository.jpa.RoleRepository;
import com.example.role.validation.constraint.UniqueRoleName;
import com.example.role.validation.definition.RoleDefinitionUniqueRoleNameValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, RoleDefinitionUniqueRoleNameValidator> {

    //<editor-fold desc="Fields">
    @Autowired
    private RoleRepository roleRepository = null;
    //</editor-fold>

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
    }

    @Override
    public boolean isValid(RoleDefinitionUniqueRoleNameValidator value, ConstraintValidatorContext context) {
        // Bean Validation specification recommends to consider null values as being valid.
        // If null is not a valid value for an element, it should be annotated with @NotNull explicitly.
        if ((value == null) || (value.getName() == null)) {
            return true;
        }

        boolean isValid = false;

        Role foundItem = roleRepository.findByName(value.getName());
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
