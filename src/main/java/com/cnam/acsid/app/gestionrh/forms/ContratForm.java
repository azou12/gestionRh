package com.cnam.acsid.app.gestionrh.forms;

public class ContratForm {
    private String contratId;
    private String userId;
    private String type;
    private String dateDebut;
    private String dateFin;

    public String getContratId() {
        return contratId;
    }

    public void setContratId(String contratId) {
        this.contratId = contratId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}
