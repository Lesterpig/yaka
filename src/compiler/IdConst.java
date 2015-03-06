package compiler;

public class IdConst extends Ident {
  protected int value;

  public IdConst(boolean v) {
    super(TypeList.BOOLEEN);
    if (v) {
      value=-1;
    } else {
      value=0;
    }
  }

  public IdConst (int v) {
    super(TypeList.ENTIER);
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
