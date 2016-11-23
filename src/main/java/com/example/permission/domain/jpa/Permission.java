package com.example.permission.domain.jpa;

import com.example.common.domain.jpa.BaseDateTimeEntity;
import com.example.common.validation.constraint.RegExWord;
import com.example.permission.validation.constraint.UniquePermissionName;
import com.example.permission.validation.definition.PermissionDefinitionUniquePermissionNameValidator;
import com.example.role.domain.jpa.Role;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@UniquePermissionName(message = "UniqueValue")
public class Permission extends BaseDateTimeEntity implements PermissionDefinitionUniquePermissionNameValidator {

    //<editor-fold desc="Constants">
    public static final String PERMISSION_PREFIX = "PERM_";
    public static final String PERMISSION_SUFFIX_READ = "_READ";
    public static final String PERMISSION_SUFFIX_WRITE = "_WRITE";

    public static final int CONSTRAINT_NAME_MIN_SIZE = 1;
    public static final int CONSTRAINT_NAME_MAX_SIZE = 255;

    private static final long serialVersionUID = 1L;
    //</editor-fold>

    //<editor-fold desc="Fields">
    private Boolean isSystem = null;

    private String name = null;

    private Set<Role> roles = new LinkedHashSet<>();
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public Permission() {
    }
    //</editor-fold>

    //<editor-fold desc="Primitive accessors">
    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_system", nullable = false)
    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    @NotBlank
    @Size(min = CONSTRAINT_NAME_MIN_SIZE, max = CONSTRAINT_NAME_MAX_SIZE)
    @RegExWord
    @Column(name = "name", length = CONSTRAINT_NAME_MAX_SIZE, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            name = name.toUpperCase();
        }

        this.name = name;
    }
    //</editor-fold>

    //<editor-fold desc="Association accessors">
    /*
     * ----- @ManyToMany
	 *
	 * Many permissions to many roles
	 *
	 * "Inverse side" - Permission
	 */

    @Valid
    @ManyToMany(mappedBy = "permissions")
    public Set<Role> getRoles() {
        if (this.roles == null) {
            this.roles = new LinkedHashSet<>();
        }

        return roles;
    }

    @SuppressWarnings("unused")
    private void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Valid
    public Role addRole(@Valid Role role) {
        if (roles == null) {
            roles = new LinkedHashSet<>();
        }

        if (!roles.contains(role)) {
            roles.add(role);
        }

        if (role.getPermissions() == null) {
            role.setPermissions(new LinkedHashSet<>());
        }

        if (!role.getPermissions().contains(this)) {
            role.getPermissions().add(this);
        }

        return role;
    }

    @Valid
    public Role removeRole(@Valid Role role) {
        if (roles == null) {
            roles = new LinkedHashSet<>();
        }

        if (roles.contains(role)) {
            roles.remove(role);
        }

        if (role.getPermissions() == null) {
            role.setPermissions(new LinkedHashSet<>());
        }

        if (role.getPermissions().contains(this)) {
            role.getPermissions().remove(this);
        }

        return role;
    }
    //</editor-fold>

    //<editor-fold desc="Object methods">
    @Override
    public String toString() {
        return "Permission{" +
            "isSystem=" + isSystem +
            ", name='" + name + '\'' +
            '}';
    }
    //</editor-fold>

}
