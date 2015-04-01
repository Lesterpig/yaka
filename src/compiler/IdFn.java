package compiler;

import generated.*;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class IdFn extends Ident {

  protected TypeList typeRes;
  protected LinkedHashMap<String, TypeList> parametres;
  protected HashMap<String, IdVar> computedParametres;

  /**
   * Construit une fonction
   * @param r Le type de retour de la fonction
   */
  public IdFn(TypeList r) {
    super(TypeList.FONCTION);
    parametres = new LinkedHashMap<String, TypeList>();
    typeRes = r;
  }


  public TypeList getTypeRes() {
    return typeRes;
  }

  /**
   * Ajout d'un nouveau paramètre à une fonction
   * @param key  Le nom du paramètre
   * @param type Le type du paramètre
   */
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
    int s = parametres.size()*2;
    for (Map.Entry<String, TypeList> entry : parametres.entrySet()) {
      int offset = s + 4 - (++i)*2;
      computedParametres.put(entry.getKey(), new IdVar(entry.getValue(), offset));
    }
  }


  /**
   * @return La liste des paramètres de la fonction munis de leur offset
   */
  public HashMap<String, IdVar> getParametres() {
    if(computedParametres == null)
      computeParametres();
    return computedParametres;
  }

}
