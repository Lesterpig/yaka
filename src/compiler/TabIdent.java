package compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import generated.Yaka;

public class TabIdent {
  private HashMap<String,IdFn> globaux;
  private HashMap<String,Ident> locaux;

  private int varNb;

  public TabIdent() {
    globaux = new HashMap<String,IdFn>();
    locaux  = new HashMap<String,Ident>();
    varNb = 0;
  }

  public TabIdent(int size) {
    globaux = new HashMap<String,IdFn>(size);
    locaux  = new HashMap<String,Ident>(size);
    varNb = 0;
  }

  public Ident searchIdent(String key) {
    Ident local = locaux.get(key);
    if(local != null)
      return local;

    IdFn currentFn = searchFn(Yaka.fonctionActive);

    if(currentFn != null) {
      return currentFn.searchParametre(key);
    }

    return null;
  }

  public IdFn searchFn(String key) {
    return globaux.get(key);
  }

  public void computeIdent(String ident) {

    IdFn fn = searchFn(ident);

    if(fn != null)
      return;

    Ident id = searchIdent(ident);

    if(id == null) {
      Yaka.ajoutLog("La variable " + ident + " n'existe pas.");
      Yaka.expression.ajoutType(TypeList.ERREUR);
      return;
    }

    Yaka.expression.ajoutType(id.getType());

    if(id instanceof IdVar) {
      Yaka.yvm.iload(((IdVar) id).getOffset());
    } else if(id instanceof IdConst) {
      Yaka.yvm.iconst(((IdConst) id).getValue());
    }

  }

  public boolean existIdent(String key) {
    return searchIdent(key) != null;
  }

  public void addIdent(String key, Ident id) {
    locaux.put(key, id);
  }

  public void addVariable(String key, TypeList type) {
    if(type == TypeList.FONCTION) {
      Yaka.ajoutLog("La variable locale " + key + " ne peut pas être une fonction");
      return;
    }

    IdVar id = new IdVar(type, -2 * (++varNb));
    addIdent(key, id);
  }

  public void addFn(String key, IdFn id) {
    if(globaux.get(key) != null) {
      Yaka.ajoutLog("La fonction " + key + " est déjà déclarée");
      return;
    }
    globaux.put(key, id);
  }

  public void clear() {
    locaux.clear();
    varNb = 0;
  }

  public int getNbVars() {
    return varNb;
  }

  public int getNbParamsFn(String key) {
    IdFn fn = searchFn(key);
    if(fn == null) {
      Yaka.ajoutLog("La fonction " + key + " n'a pas ete declaree.");
      return -1;
    }

    else
      return fn.getParametres().size();
  }

  public TypeList getTypeOfParam(String fonc, int pos) {
    try {
      IdFn fn = searchFn(fonc);
      return (new ArrayList<TypeList>(fn.parametres.values())).get(pos);
    } catch (IndexOutOfBoundsException e) {
      Yaka.ajoutLog("Trop de parametres pour la fonction " + fonc);
      return TypeList.ERREUR;
    }
  }
}
