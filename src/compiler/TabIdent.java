package compiler;

import java.util.HashMap;
import generated.Yaka;

public class TabIdent {
  private HashMap<String,Ident> table;

  public TabIdent() {
    table = new HashMap<String,Ident>();
  }

  public TabIdent(int size) {
    table = new HashMap<String,Ident>(size);
  }

  public Ident searchIdent(String key) {
    return table.get(key);
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
    return table.containsKey(key);
  }

  public void addIdent(String key, Ident id) {
    table.put(key, id);
  }

  public int getNbVars() {
    int i = 0;
    for (HashMap.Entry<String, Ident> entry : table.entrySet()) {
      if(entry.getValue() instanceof IdVar)
        i++;
    }
    return i;
  }

  public String toString() {
    String res = "\nTabIdent:\n";
    for (HashMap.Entry<String, Ident> entry : table.entrySet()) {
      res += "  ";
        if (entry.getValue() instanceof IdConst)
          res += "CONST ";
        else
          res += "VAR   ";

      res += entry.getKey() + entry.getValue().toString() + "\n";
    }

    return res;
  }

}

