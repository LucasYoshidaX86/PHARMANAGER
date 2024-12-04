package model;

import javax.persistence.Entity;

	@Entity
	public class Antibiotico extends Medicamento {
	    private String descricaoAntibiotico;

	    public String getDescricaoAntibiotico() {
	        return descricaoAntibiotico;
	    }

	    public void setDescricaoAntibiotico(String descricaoAntibiotico) {
	        this.descricaoAntibiotico = descricaoAntibiotico;
	    }
	

}
