package compiler;

public class IdVar extends Ident {
  private int offset;

  public IdVar(TypeList t, int o) {
    super(t);
	offset = o;
  }
  
  public int getOffset() {
    return offset;
  }

  public void setOffset(int o) {
    offset=o;
  }
}
