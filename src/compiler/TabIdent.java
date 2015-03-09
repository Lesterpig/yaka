package compiler;

import java.util.HashMap;

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

  public boolean existIdent(String key) {
    return table.containsKey(key);
  }

  public void addIdent(String key, Ident id) {
    table.put(key, id);
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

