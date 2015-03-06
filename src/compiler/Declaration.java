package compiler;

public class Declaration {

	private TypeList type;
	private String ident;

	public Declaration() {
		type = TypeList.ERREUR;
		ident = "";
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
	
}