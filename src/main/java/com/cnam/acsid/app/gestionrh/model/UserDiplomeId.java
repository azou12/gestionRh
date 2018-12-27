package com.cnam.acsid.app.gestionrh.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserDiplomeId implements Serializable {
    @Column(name="user_id")
    int userId;

    @Column(name="diplome_id")
    int diplomeId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDiplomeId() {
        return diplomeId;
    }

    public void setDiplomeId(int diplomeId) {
        this.diplomeId = diplomeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDiplomeId)) return false;
        UserDiplomeId that = (UserDiplomeId) o;
        return Objects.equals(getDiplomeId(), that.getDiplomeId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getDiplomeId());
    }
}
