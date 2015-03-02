enum TypeList { BOOLEEN, ENTIER, ERREUR };

public abstract class Ident {
  private TypeList type;

  public TypeList getType() {
    return type;
  }

  public void setType(TypeList t) {
    type=t;
  }
}
