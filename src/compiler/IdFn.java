package compiler;

import generated.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class IdFn extends IdVar {

  protected TypeList typeRes;
  protected LinkedHashMap<String, TypeList> parametres;
  protected HashMap<String, IdVar> computedParametres;

  public IdFn(TypeList t, int o, TypeList r) {
    super(t,o);
    parametres = new LinkedHashMap<String, TypeList>();
    typeRes = r;
  }

  public TypeList getTypeRes() {
    return typeRes;
  }

  public void addParametre(String key, TypeList type) {
    if(parametres.get(key) != null) {
      Yaka.ajoutLog("Le parametre " + key + " a deja ete defini dans la fonction.");
      return;
    }

    parametres.put(key, type);
  }

  /**
   * Only computed once internally : produces the right offset
   */
  private void computeParametres() {
    computedParametres = new HashMap<String, IdVar>();

    int i = 0;
    int s = parametres.size();
    for (Map.Entry<String, TypeList> entry : parametres.entrySet()) {
      int offset = s + 4 - (++i);
      computedParametres.put(entry.getKey(), new IdVar(entry.getValue(), offset));
    }
  }


  /**
   * @return An usable hashmap for parameters load in stack
   */
  public HashMap<String, IdVar> getParametres() {
    if(computedParametres == null)
      computeParametres();
    return computedParametres;
  }

}
