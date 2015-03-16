package compiler;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class YvmAsmTest {

    private YvmAsm a;

    @Before
    public void init() {
        this.a = new YvmAsm();
    }

    @Test
    public void imulTest() {
        a.imul();
        assertEquals("\n    ; imul\n    pop bx\n    pop ax\n    imul bx\n    push ax\n", a.getOut());
    }

    @Test
    public void iinfegalTest() {
        a.iinfegal();
        assertEquals("\n    ; iinfegal\n    pop bx\n    pop ax\n    cmp ax,bx\n    jg $+6\n    push -1\n    jmp $+4\n    push 0\n", a.getOut());
    }

    @Test
    public void istoreTest() {
        a.istore(-16);
        assertEquals("\n    ; istore -16\n    pop ax\n    mov word ptr [bp-16],ax\n", a.getOut());
    }

}
