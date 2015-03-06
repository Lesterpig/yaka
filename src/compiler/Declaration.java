package compiler;
import generated.*;

public class Declaration {

	private TypeList type;
	private String ident;

	private int varOffset;
	private TabIdent tabIdent;
	
	private String errorLog;
	
	public Declaration() {
		type = TypeList.ERREUR;
		ident = "";
		varOffset = -2;
		tabIdent = Yaka.tabIdent;
		errorLog = "";
	}
	
	public TypeList getType () {
		return type;
	}
	
	public void setType(TypeList t) {
		type = t;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public void setIdent(String i) {
		ident = i;
	}
	
	public int getVarOffset() {
		return varOffset;
	}
	
	public void setVarOffset(int i) {
		varOffset = i;
	}
	
	public TabIdent getTabIdent() {
		return tabIdent;
	}
	
	public void updateVarOffset() {
		varOffset += -2;
	}
	
	/*
	Teste si l'ident courant est deja present dans Yaka.tabIdent.
	Retourne faux s'il est present, vrai sinon.
	*/
	public boolean identValide() {
		return !(tabIdent.existIdent(this.getIdent()));
	}
	
	/*
	Met a jour l'objet de type TabIdent de la classe Yaka...
	Ajoute une description de l'erreur a errorLog.
	*/
	public void ajoutVar(){
		if(!identValide())
			errorLog += "L'ident "+ident+" a deja ete declare.\n";
		else {
			IdVar var = new IdVar(type, varOffset);
			this.updateVarOffset();
			tabIdent.addIdent(ident, var);
		}
	}
	
	
}
