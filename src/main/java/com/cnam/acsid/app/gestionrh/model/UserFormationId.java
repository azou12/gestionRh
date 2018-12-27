package com.cnam.acsid.app.gestionrh.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserFormationId implements Serializable {
    @Column(name="user_id")
    int userId;

    @Column(name="formation_id")
    int formationId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFormationId() {
        return formationId;
    }

    public void setFormationId(int formationId) {
        this.formationId = formationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFormationId)) return false;
        UserFormationId that = (UserFormationId) o;
        return Objects.equals(getFormationId(), that.getFormationId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getFormationId());
    }
}
