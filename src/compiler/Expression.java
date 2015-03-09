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

}
