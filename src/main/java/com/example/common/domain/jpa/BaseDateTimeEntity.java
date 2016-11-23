package com.example.common.domain.jpa;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseDateTimeEntity extends BaseEntity {

    //<editor-fold desc="Fields">
    private LocalDateTime created = null;

    private LocalDateTime modified = null;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public BaseDateTimeEntity() {
    }
    //</editor-fold>

    //<editor-fold desc="Primitive accessors">
    @NotNull
    @Column(name = "created", nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @NotNull
    @Column(name = "modified", nullable = false)
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
    //</editor-fold>

}
