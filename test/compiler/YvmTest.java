package compiler;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class YvmTest {

    private Yvm a;

    @Before
    public void init() {
        this.a = new Yvm();
    }

    @Test
    public void noArgTest() {
        a.isub();
        assertEquals("isub\n", a.getOut());
    }

    @Test
    public void oneArgTest() {
        a.iload(-2);
        assertEquals("iload -2\n", a.getOut());
    }

    @Test
    public void globalTest() {
        a.ineg();
        a.iinf();
        a.iload(4);
        assertEquals("ineg\niinf\niload 4\n", a.getOut());
    }

    @Test
    public void faireTest() {
        a.ouvreFaire();
        a.ouvreFaire();
        a.fermeFaire();
        a.ouvreFaire();
        a.fermeFaire();
        a.fermeFaire();
        assertEquals("FAIRE0:\nFAIRE1:\nFAIT1:\nFAIRE2:\nFAIT2:\nFAIT0:\n", a.getOut());
    }

}
