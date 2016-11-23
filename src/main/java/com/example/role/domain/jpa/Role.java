package com.example.role.domain.jpa;

import com.example.common.domain.jpa.BaseDateTimeEntity;
import com.example.common.validation.constraint.RegExWord;
import com.example.permission.domain.jpa.Permission;
import com.example.role.validation.constraint.UniqueRoleName;
import com.example.role.validation.constraint.UniqueRoleTitle;
import com.example.role.validation.definition.RoleDefinitionUniqueRoleNameValidator;
import com.example.role.validation.definition.RoleDefinitionUniqueRoleTitleValidator;
import com.example.user.domain.jpa.User;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@UniqueRoleName(message = "UniqueValue")
@UniqueRoleTitle(message = "UniqueValue")
public class Role extends BaseDateTimeEntity implements
    RoleDefinitionUniqueRoleNameValidator,
    RoleDefinitionUniqueRoleTitleValidator {

    //<editor-fold desc="Constants">
    public static final String ROLE_PREFIX = "ROLE_";

    /**
     * Name of system "Root" role
     */
    public static final String ROOT_ROLE_NAME = "ROOT";
    /**
     * Name of system "Staff" role
     */
    public static final String STAFF_ROLE_NAME = "STAFF";
    /**
     * Name of system "User" role
     */
    public static final String USER_ROLE_NAME = "USER";

    public static final int CONSTRAINT_NAME_MIN_SIZE = 1;
    public static final int CONSTRAINT_NAME_MAX_SIZE = 255;

    public static final int CONSTRAINT_TITLE_MIN_SIZE = 1;
    public static final int CONSTRAINT_TITLE_MAX_SIZE = 255;

    public static final int CONSTRAINT_DESCRIPTION_MAX_SIZE = 10000;

    private static final long serialVersionUID = 1L;
    //</editor-fold>

    //<editor-fold desc="Fields">
    private Boolean isSystem = null;

    private String name = null;

    private String title = null;

    private String description = null;

    private Set<Permission> permissions = new LinkedHashSet<>();

    private Set<User> users = new LinkedHashSet<>();
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public Role() {
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

    @NotBlank
    @Size(min = CONSTRAINT_TITLE_MIN_SIZE, max = CONSTRAINT_TITLE_MAX_SIZE)
    @Column(name = "title", length = CONSTRAINT_TITLE_MAX_SIZE, unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(max = CONSTRAINT_DESCRIPTION_MAX_SIZE)
    @Column(name = "description", length = CONSTRAINT_DESCRIPTION_MAX_SIZE, nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            description = description.trim();
        }

        this.description = description;
    }
    //</editor-fold>

    //<editor-fold desc="Association accessors">
    /*
	 * ----- @ManyToMany
	 *
	 * Many roles to many permissions
	 *
	 * "Owning side" - Role
	 */

    @Valid
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id"))
    public Set<Permission> getPermissions() {
        if (this.permissions == null) {
            this.permissions = new LinkedHashSet<>();
        }

        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(@Valid Permission permission) {
        if (permissions == null) {
            permissions = new LinkedHashSet<>();
        }

        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }

    public void removePermission(@Valid Permission permission) {
        if (permissions == null) {
            permissions = new LinkedHashSet<>();
        }

        if (permissions.contains(permission)) {
            permissions.remove(permission);
        }
    }

	/*
	 * ----- @ManyToMany
	 *
	 * Many roles to many users
	 *
	 * "Inverse side" - Role
	 */

    @Valid
    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        if (this.users == null) {
            this.users = new LinkedHashSet<>();
        }

        return users;
    }

    @SuppressWarnings("unused")
    private void setUsers(Set<User> users) {
        this.users = users;
    }

    @Valid
    public User addUser(@Valid User user) {
        if (users == null) {
            users = new LinkedHashSet<>();
        }

        if (!users.contains(user)) {
            users.add(user);
        }

        if (user.getRoles() == null) {
            user.setRoles(new LinkedHashSet<>());
        }

        if (!user.getRoles().contains(this)) {
            user.getRoles().add(this);
        }

        return user;
    }

    @Valid
    public User removeUser(@Valid User user) {
        if (users == null) {
            users = new LinkedHashSet<>();
        }

        if (users.contains(user)) {
            users.remove(user);
        }

        if (user.getRoles() == null) {
            user.setRoles(new LinkedHashSet<>());
        }

        if (user.getRoles().contains(this)) {
            user.getRoles().remove(this);
        }

        return user;
    }
    //</editor-fold>

    //<editor-fold desc="Object methods">
    @Override
    public String toString() {
        return "Role{" +
            "isSystem=" + isSystem +
            ", name='" + name + '\'' +
            ", title='" + title + '\'' +
            '}';
    }
    //</editor-fold>

}
