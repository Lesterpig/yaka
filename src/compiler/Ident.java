package compiler;

enum TypeList { BOOLEEN, ENTIER, ERREUR };

public abstract class Ident {
  private TypeList type;

  public Ident(TypeList t) {
    type = t;
  }
  
  public TypeList getType() {
    return type;
  }

  public void setType(TypeList t) {
    type=t;
  }
}
