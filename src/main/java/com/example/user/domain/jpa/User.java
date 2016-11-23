package com.example.user.domain.jpa;

import com.example.common.domain.jpa.BaseDateTimeEntity;
import com.example.common.validation.constraint.RegExDigit;
import com.example.common.validation.constraint.RegExGraph;
import com.example.common.validation.constraint.RegExUuid;
import com.example.role.domain.jpa.Role;
import com.example.user.validation.constraint.MandatoryUserEmailOrUserPhoneNumber;
import com.example.user.validation.constraint.UniqueUserEmail;
import com.example.user.validation.constraint.UniqueUserPhoneNumber;
import com.example.user.validation.constraint.UniqueUserUsername;
import com.example.user.validation.definition.UserDefinitionMandatoryUserEmailOrUserPhoneNumberValidator;
import com.example.user.validation.definition.UserDefinitionUniqueUserEmailValidator;
import com.example.user.validation.definition.UserDefinitionUniqueUserPhoneNumberValidator;
import com.example.user.validation.definition.UserDefinitionUniqueUserUsernameValidator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
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
@Table(name = "users")
@UniqueUserUsername(message = "UniqueValue")
@UniqueUserEmail(message = "UniqueValue")
@UniqueUserPhoneNumber(message = "UniqueValue")
@MandatoryUserEmailOrUserPhoneNumber(message = "MandatoryValue")
public class User extends BaseDateTimeEntity implements
    UserDefinitionUniqueUserUsernameValidator,
    UserDefinitionUniqueUserEmailValidator,
    UserDefinitionUniqueUserPhoneNumberValidator,
    UserDefinitionMandatoryUserEmailOrUserPhoneNumberValidator {

    //<editor-fold desc="Constants">
    public static final int CONSTRAINT_USERNAME_MIN_SIZE = 36;
    public static final int CONSTRAINT_USERNAME_MAX_SIZE = 36;

    public static final int CONSTRAINT_PASSWORD_ENCRYPTED_MIN_SIZE = 1;
    public static final int CONSTRAINT_PASSWORD_ENCRYPTED_MAX_SIZE = 60;

    public static final int CONSTRAINT_PASSWORD_PLAIN_MIN_SIZE = 6;
    public static final int CONSTRAINT_PASSWORD_PLAIN_MAX_SIZE = 100;

    public static final int CONSTRAINT_EMAIL_MAX_SIZE = 255;

    public static final int CONSTRAINT_PHONE_NUMBER_MAX_SIZE = 255;

    public static final int CONSTRAINT_NAME_MIN_SIZE = 1;
    public static final int CONSTRAINT_NAME_MAX_SIZE = 255;

    public static final int CONSTRAINT_PROFILE_PICTURE_ID_MAX_SIZE = 255;

    private static final long serialVersionUID = 1L;
    //</editor-fold>

    //<editor-fold desc="Fields">
    private Boolean isSystem = null;

    private Boolean isActive = null;

    private String username = null;

    private String password = null;

    private String email = null;

    private Boolean isEmailVerified = null;

    private String phoneNumber = null;

    private Boolean isPhoneNumberVerified = null;

    private String name = null;

    private String profilePictureId = null;

    private Set<Role> roles = new LinkedHashSet<>();
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public User() {
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

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_active", nullable = false)
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @NotBlank
    @Size(min = CONSTRAINT_USERNAME_MIN_SIZE, max = CONSTRAINT_USERNAME_MAX_SIZE)
    @RegExUuid
    @Column(name = "username", length = CONSTRAINT_USERNAME_MAX_SIZE, unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null) {
            username = username.toLowerCase();
        }

        this.username = username;
    }

    @NotBlank
    @Size(min = CONSTRAINT_PASSWORD_ENCRYPTED_MIN_SIZE, max = CONSTRAINT_PASSWORD_ENCRYPTED_MAX_SIZE)
    @RegExGraph
    @Column(name = "password", length = CONSTRAINT_PASSWORD_ENCRYPTED_MAX_SIZE, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Size(max = CONSTRAINT_EMAIL_MAX_SIZE)
    @Email
    @Column(name = "email", length = CONSTRAINT_EMAIL_MAX_SIZE, unique = true, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            if (email.isEmpty()) {
                email = null;
            } else {
                email = email.toLowerCase();
            }
        }

        this.email = email;
    }

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_email_verified", nullable = false)
    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    @Size(max = CONSTRAINT_PHONE_NUMBER_MAX_SIZE)
    @RegExDigit
    @Column(name = "phone_number", length = CONSTRAINT_PHONE_NUMBER_MAX_SIZE, unique = true, nullable = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null) {
            if (phoneNumber.isEmpty()) {
                phoneNumber = null;
            }
        }

        this.phoneNumber = phoneNumber;
    }

    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_phone_number_verified", nullable = false)
    public Boolean getIsPhoneNumberVerified() {
        return isPhoneNumberVerified;
    }

    public void setIsPhoneNumberVerified(Boolean isPhoneNumberVerified) {
        this.isPhoneNumberVerified = isPhoneNumberVerified;
    }

    @NotBlank
    @Size(min = CONSTRAINT_NAME_MIN_SIZE, max = CONSTRAINT_NAME_MAX_SIZE)
    @Column(name = "name", length = CONSTRAINT_NAME_MAX_SIZE, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = CONSTRAINT_PROFILE_PICTURE_ID_MAX_SIZE)
    @Column(name = "profile_picture_id", length = CONSTRAINT_PROFILE_PICTURE_ID_MAX_SIZE, nullable = true)
    public String getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(String profilePictureId) {
        this.profilePictureId = profilePictureId;
    }
    //</editor-fold>

    //<editor-fold desc="Association accessors">
    /*
     * ----- @ManyToMany
     *
     * Many users to many roles
     *
     * "Owning side" - User
     */

    @Valid
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        if (this.roles == null) {
            this.roles = new LinkedHashSet<>();
        }

        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(@Valid Role role) {
        if (roles == null) {
            roles = new LinkedHashSet<>();
        }

        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

    public void removeRole(@Valid Role role) {
        if (roles == null) {
            roles = new LinkedHashSet<>();
        }

        if (roles.contains(role)) {
            roles.remove(role);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Object methods">
    @Override
    public String toString() {
        return "User{" +
            "isSystem=" + isSystem +
            ", isActive=" + isActive +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", isEmailVerified=" + isEmailVerified +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", isPhoneNumberVerified=" + isPhoneNumberVerified +
            ", name='" + name + '\'' +
            '}';
    }
    //</editor-fold>

}
