package model;

import javax.persistence.Entity;

	@Entity
	public class Antidepressivo extends Medicamento {
	    private String descricaoAntidepressivo;

	    public String getDescricaoAntidepressivo() {
	        return descricaoAntidepressivo;
	    }

	    public void setDescricaoAntidepressivo(String descricaoAntidepressivo) {
	        this.descricaoAntidepressivo = descricaoAntidepressivo;
	    }
	

}

	