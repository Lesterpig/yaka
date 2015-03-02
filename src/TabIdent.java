import java.util.HashMap;

public class TabIdent {
  private HashMap<String,Ident> table;
  
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

}
