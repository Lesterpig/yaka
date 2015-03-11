package compiler;

public abstract class Ident {
  protected TypeList type;

  public Ident(TypeList t) {
    type = t;
  }

  public TypeList getType() {
    return type;
  }

  public void setType(TypeList t) {
    type=t;
  }

  public String toString() {
    switch(type)
	{
	case BOOLEEN:
		return "BOOL";
	case ENTIER:
		return "ENT";
	default:
		return "ERR";
	}
  }
}
