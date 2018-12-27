package com.cnam.acsid.app.gestionrh.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCongeId implements Serializable {
    @Column(name="user_id")
    int userId;

    @Column(name="conge_id")
    int congeId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCongeId() {
        return congeId;
    }

    public void setCongeId(int congeId) {
        this.congeId = congeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCongeId)) return false;
        UserCongeId that = (UserCongeId) o;
        return Objects.equals(getCongeId(), that.getCongeId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCongeId());
    }
}
