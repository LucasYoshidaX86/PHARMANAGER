package model;

import javax.persistence.Entity;

	@Entity
	public class Diuretico extends Medicamento {
	    private String descricaoDiuretico;

	    public String getDescricaoDiuretico() {
	        return descricaoDiuretico;
	    }

	    public void setDescricaoDiuretico(String descricaoDiuretico) {
	        this.descricaoDiuretico = descricaoDiuretico;
	    }
	

}
	
