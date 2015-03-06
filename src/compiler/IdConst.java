package compiler;

public class IdConst extends Ident {
  private int value;

  public IdConst (TypeList t, int v) {
    super(t);
	value = v;
  }
  
  public int getValue() {
    return value;
  }

  public void setValue(int v) {
    value=v;
  }
}
