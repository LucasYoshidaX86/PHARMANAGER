package model;

import javax.persistence.Entity;

	@Entity
	public class AntiHipertensivo extends Medicamento {
	    private String descricaoAntiHipertensivo;

	    public String getDescricaoAntiHipertensivo() {
	        return descricaoAntiHipertensivo;
	    }

	    public void setDescricaoAntiHipertensivo(String descricaoAntiHipertensivo) {
	        this.descricaoAntiHipertensivo = descricaoAntiHipertensivo;
	    }
	

}
