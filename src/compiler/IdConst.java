package compiler;

public class IdConst extends Ident {
  protected int value;

  public IdConst(int v) {
    value=v;
  }

  public IdConst(boolean v) {
    if (v) {
      value=-1;
    } else {
      value=0;
    }
  }

  public IdConst (TypeList t, int v) {
    super(t);
	value = v;
  }
  
  public int getValue() {
    return value;
  }

  public boolean getValueBool() {
    return value == -1;
  }

  public String toString() {
    String res = " "+value;
    return super.toString() + res;
  }
}
