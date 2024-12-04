package model;

import javax.persistence.Entity;

	@Entity
	public class Antifungico extends Medicamento {
	    private String descricaoAntifungico;

	    public String getDescricaoAntifungico() {
	        return descricaoAntifungico;
	    }

	    public void setDescricaoAntifungico(String descricaoAntifungico) {
	        this.descricaoAntifungico = descricaoAntifungico;
	    }
	

}
	