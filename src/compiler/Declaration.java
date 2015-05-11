package compiler;
import generated.*;

public class Declaration {

	private TypeList type;
	private String ident;
  private int entier;
  private boolean booleen;

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

  public int getEntier() {
      return entier;
  }

  public void setEntier(int e) {
      type = TypeList.ENTIER;
      entier = e;
  }

  public void setBooleen(boolean b) {
    type = TypeList.BOOLEEN;
    booleen = b;
  }

  public void setConstanteExistante(String s) {
    if(Yaka.tabIdent.existIdent(s)) {
      Ident origConst = Yaka.tabIdent.searchIdent(s);

      if (origConst instanceof IdConst) {
        type = origConst.getType();
        if (type == TypeList.BOOLEEN) {
          booleen = ((IdConst)origConst).getValueBool();
        } else {
          entier = ((IdConst)origConst).getValue();
        }
      } else {
        Yaka.ajoutLog("Impossible d'affecter "+s+" à "+ident+" car "+s+" n'est pas une constante.");
      }
    } else {
      Yaka.ajoutLog("Impossible d'affecter "+s+" à "+ident+" car "+s+" n'existe pas.");
    }
  }

	public TabIdent getTabIdent() {
		return Yaka.tabIdent;
	}

	/*
	Teste si l'ident courant est deja present dans Yaka.tabIdent.
	Retourne faux s'il est present, vrai sinon.
	*/
	public boolean identValide() {
		return !(Yaka.tabIdent.existIdent(ident)); // TODO Autoriser variable locale et globale en même temps ?
	}

	/*
	Met a jour l'objet de type TabIdent de la classe Yaka...
	Ajoute une description de l'erreur a errorLog.
	*/
	public void ajoutVar(){
		if(!identValide())
			Yaka.ajoutLog("La variable "+ident+" a deja ete declare.");
		else {
			Yaka.tabIdent.addVariable(ident, type);
		}
	}

  public void ajoutConst() {
    if(!identValide())
		Yaka.ajoutLog("La constante "+ident+" a deja ete declare.");
	else {
        IdConst cons;
        if (type == TypeList.BOOLEEN)
            cons = new IdConst(booleen);
        else
            cons = new IdConst(entier);

        Yaka.tabIdent.addIdent(ident, cons);
    }
  }

  public void testAffectation(String s) {
    Ident ide = Yaka.tabIdent.searchIdent(s);
		if(ide == null) {
			Yaka.ajoutLog("Affectation impossible, l'identifiant " + s + " n'existe pas.");
    } else if (ide instanceof IdConst) {
      Yaka.ajoutLog("Affectation impossible, cet identifiant est une constante");
    }
  }

  public void testLecture(String s) {
		if(!Yaka.tabIdent.existIdent(s))
			Yaka.ajoutLog("Lecture impossible, l'identifiant " + s + " est invalide.");
  }

  public void typeInvalide(TypeList t) {
    Yaka.ajoutLog("Lecture impossible, le type est invalide (attendu ENTIER, lu" + t + ").");
  }
}
