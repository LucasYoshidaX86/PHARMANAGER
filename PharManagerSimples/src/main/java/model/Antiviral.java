package model;

import javax.persistence.Entity;

	@Entity
	public class Antiviral extends Medicamento {
	    private String descricaoAntiviral;

	    public String getDescricaoAntiviral() {
	        return descricaoAntiviral;
	    }

	    public void setDescricaoAntiviral(String descricaoAntiviral) {
	        this.descricaoAntiviral = descricaoAntiviral;
	    }
	

}
	
