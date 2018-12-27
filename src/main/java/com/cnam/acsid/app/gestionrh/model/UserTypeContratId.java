package com.cnam.acsid.app.gestionrh.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserTypeContratId implements Serializable {
    @Column(name="user_id")
    int userId;

    @Column(name="contrat_id")
    int contratId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContratId() {
        return contratId;
    }

    public void setContratId(int contratId) {
        this.contratId = contratId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTypeContratId)) return false;
        UserTypeContratId that = (UserTypeContratId) o;
        return Objects.equals(getContratId(), that.getContratId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getContratId());
    }
}
