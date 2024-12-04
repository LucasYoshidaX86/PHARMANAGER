package model;

import javax.persistence.Entity;

	@Entity
	public class AntiInflamatorio extends Medicamento {
	    private String descricaoAntiInflamatorio;

	    public String getDescricaoAntiInflamatorio() {
	        return descricaoAntiInflamatorio;
	    }

	    public void setDescricaoAntiInflamatorio(String descricaoAntiInflamatorio) {
	        this.descricaoAntiInflamatorio = descricaoAntiInflamatorio;
	    }
	

}
