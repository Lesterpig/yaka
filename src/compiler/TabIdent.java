package compiler;

import java.util.HashMap;
import java.util.Map;
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
    return locaux.get(key);
  }

  public void computeIdent(String ident) {
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
    globaux.put(key, id);
  }

  public void clear() {
    locaux.clear();
    varNb = 0;
  }

  public int getNbVars() {
    return varNb;
  }

  // TODO Add a "loadFn()" function when a function is called

}
