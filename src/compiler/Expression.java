package compiler;

import java.util.Stack;
import java.util.EmptyStackException;

public class Expression {

    private Stack<TypeList> types;
    private Stack<Operateur> operateurs;

    private String errorLog;

    public Expression() {
        types = new Stack<TypeList>();
        operateurs = new Stack<Operateur>();
		errorLog = "";
    }

	public void reinitialiser() {
		types.clear();
		operateurs.clear();
	}
	
    public void ajoutOperateur(Operateur op) {
        operateurs.add(op);
    }

    public Operateur retraitOperateur() throws EmptyStackException {
        return operateurs.pop();
    }
	
    public void ajoutType(TypeList t) {
        types.add(t);
    }

    public TypeList retraitType() throws EmptyStackException {
        return types.pop();
    }

	public TypeList regardeType() {
		try {
			return types.peek();
		} catch (EmptyStackException e) {
			ajoutLog("La pile des types est vide. Impossible de consulter le sommet de pile.");
			return TypeList.ERREUR;
		}
	}
	
    public void traiterOperation() {
        try {

            Operateur op = retraitOperateur();

            //operateur unaire ?
            if(op.getNbArgs() == 1) {
                TypeList a = this.retraitType();
                this.ajoutType(op.typeValide(a, a));
            }
            //operateur binaire ?
            else if(op.getNbArgs() == 2) {
                TypeList a = this.retraitType();
                TypeList b = this.retraitType();
                this.ajoutType(op.typeValide(a, b));
            }

        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }
	
	public void ajoutLog(String s) {errorLog += s + "\n";}
	
	public void testAffectation(TypeList t1, TypeList t2) {
		if((t2 == TypeList.ERREUR ) || (t1 != t2))
			ajoutLog("L'affectation est impossible : erreur de type" + t1 + " =" + t2);
	}
	
}
