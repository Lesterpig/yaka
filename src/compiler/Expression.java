package compiler;

import java.util.Stack;
import java.util.EmptyStackException;

import generated.YakaTokenManager;

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

    public void traiterOperation(Yvm yvm) {
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

            if(yvm != null) {
                switch(op.getOp()) {
                    case SUP: yvm.isup(); break;
                    case INF: yvm.iinf(); break;
                    case SUPEG: yvm.isupegal(); break;
                    case INFEG: yvm.iinfegal(); break;
                    case EG: yvm.iegal(); break;
                    case DIFF: yvm.idiff(); break;
                    case PLUS: yvm.iadd(); break;
                    case MOINS: yvm.isub(); break;
                    case OU: yvm.ior(); break;
                    case FOIS: yvm.imul(); break;
                    case DIV: yvm.idiv(); break;
                    case ET: yvm.iand(); break;
                    case NEG: yvm.ineg(); break;
                    case NON: yvm.inot(); break;
                }
            }

        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

	public void ajoutLog(String s) {errorLog += "Ligne " + YakaTokenManager.nbLig + " : " + s + "\n";}

	public void testAffectation(TypeList t1, TypeList t2) {
		if((t2 == TypeList.ERREUR ) || (t1 != t2))
			ajoutLog("Affectation impossible, erreur de type" + t1 + " =" + t2);
	}

}
