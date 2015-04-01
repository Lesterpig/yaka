package compiler;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import generated.Yaka;

public class TabIdentTest {

    private TabIdent t;

    @Before
    public void init() {
        this.t = new TabIdent();
    }

    @Test
    public void searchIdentTest() {
        t.addVariable("john", TypeList.ENTIER);
        Ident i = t.searchIdent("john");
        assertEquals(i.getType(), TypeList.ENTIER);
        assertEquals(Yaka.errorLog, "");

        t.addVariable("fail", TypeList.FONCTION);
        assertEquals(t.searchIdent("fail"), null);
        assertFalse(Yaka.errorLog.equals(""));
    }

    @Test
    public void searchFnTest() {
        IdFn i = new IdFn(TypeList.ENTIER);
        t.addFn("ripper", i);
        assertEquals(t.searchFn("ripper"), i);
    }

}
