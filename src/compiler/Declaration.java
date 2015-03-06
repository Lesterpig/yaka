package compiler;

public class Declaration {

	private TypeList type;
	private String ident;


	public Declaration() {
		type = TypeList.ERREUR;
		ident = "";
	}
}