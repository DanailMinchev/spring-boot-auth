package com.example.permission.validation.validator;

import com.example.permission.domain.jpa.Permission;
import com.example.permission.repository.jpa.PermissionRepository;
import com.example.permission.validation.constraint.UniquePermissionName;
import com.example.permission.validation.definition.PermissionDefinitionUniquePermissionNameValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePermissionNameValidator implements ConstraintValidator<UniquePermissionName, PermissionDefinitionUniquePermissionNameValidator> {

    //<editor-fold desc="Fields">
    @Autowired
    private PermissionRepository permissionRepository = null;
    //</editor-fold>

    //<editor-fold desc="ConstraintValidator methods">
    @Override
    public void initialize(UniquePermissionName constraintAnnotation) {
    }

    @Override
    public boolean isValid(PermissionDefinitionUniquePermissionNameValidator value, ConstraintValidatorContext context) {
        // Bean Validation specification recommends to consider null values as being valid.
        // If null is not a valid value for an element, it should be annotated with @NotNull explicitly.
        if ((value == null) || (value.getName() == null)) {
            return true;
        }

        boolean isValid = false;

        Permission foundItem = permissionRepository.findByName(value.getName());
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
