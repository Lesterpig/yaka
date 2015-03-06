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
            //dans la pratique, du fait de l'utilisation d'un objet
            //Expression de maniere statique, l'appel sur cet objet est
            //justifie car les operateurs sont uniquement lies a cette instance.
            op.valider(this);
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

}
