package com.cnam.acsid.app.gestionrh.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserCompetenceId implements Serializable {
    @Column(name="user_id")
    int userId;

    @Column(name="competence_id")
    int competenceId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(int competenceId) {
        this.competenceId = competenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCompetenceId)) return false;
        UserCompetenceId that = (UserCompetenceId) o;
        return Objects.equals(getCompetenceId(), that.getCompetenceId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCompetenceId());
    }
}
