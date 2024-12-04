package model;

import javax.persistence.Entity;

	@Entity
	public class Analgesico extends Medicamento {
	    private String descricaoAnalgesico;

	    public String getDescricaoAnalgesico() {
	        return descricaoAnalgesico;
	    }

	    public void setDescricaoAnalgesico(String descricaoAnalgesico) {
	        this.descricaoAnalgesico = descricaoAnalgesico;
	    }
	

}

