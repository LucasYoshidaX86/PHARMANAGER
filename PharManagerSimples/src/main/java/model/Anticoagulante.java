package model;

import javax.persistence.Entity;

	@Entity
	public class Anticoagulante extends Medicamento {
	    private String descricaoAnticoagulante;

	    public String getDescricaoAnticoagulante() {
	        return descricaoAnticoagulante;
	    }

	    public void setDescricaoAnticoagulante(String descricaoAnticoagulante) {
	        this.descricaoAnticoagulante = descricaoAnticoagulante;
	    }
	

}