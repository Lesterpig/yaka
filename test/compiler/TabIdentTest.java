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
        Yaka.errorLog = "";
    }

    @Test
    public void searchFnTest() {
        IdFn i = new IdFn(TypeList.ENTIER);
        t.addFn("ripper", i);
        assertEquals(t.searchFn("ripper"), i);
    }

}
