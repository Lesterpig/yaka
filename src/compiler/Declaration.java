package compiler;
import generated.*;

public class Declaration {

	private TypeList type;
	private String ident;

	private int varOffset;
	private TabIdent tabIdent;
	
	public Declaration() {
		type = TypeList.ERREUR;
		ident = "";
		varOffset = -2;
		tabIdent = Yaka.tabIdent;
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
	TO DO : gestion de l'exception!!!
	*/
	public void ajoutlVar() throws IdentInvalideException {
		if(!identValide())
			throw new IdentInvalideException();
		else {
			IdVar var = new IdVar(type, varOffset);
			this.updateVarOffset();
			tabIdent.addIdent(ident, var);
		}
	}
	
	
}
