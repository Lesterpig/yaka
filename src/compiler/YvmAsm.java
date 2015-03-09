package compiler;

public class YvmAsm extends Yvm {

    public YvmAsm() {
        super();
    }

    private void addInstructionTab(String arg) {
        this.addInstruction("    " + arg);
    }

    @Override
    public void entete() {
        addInstruction(".model SMALL");
        addInstruction(".586");
        addInstruction(".CODE");
        addInstruction("debut:");
    }

    @Override
    public void ouvrePrinc(int arg) {
        addInstructionTab("mov bp,sp");
        addInstructionTab("sub sp," + arg);
    }

    @Override
    public void queue() {
        addInstructionTab("nop");
        addInstructionTab("exitcode");
        addInstructionTab("end debut");
    }

    // Arithmétiques

    @Override
    public void iadd() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("add ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void isub() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("sub ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void imul() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("imul bx");
        addInstructionTab("push ax");
    }

    @Override
    public void idiv() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("cwd");
        addInstructionTab("idiv bx");
        addInstructionTab("push ax");
    }

    @Override
    public void ineg() {
        addInstructionTab("pop ax");
        addInstructionTab("imul -1");
        addInstructionTab("push ax");
    }

    @Override
    public void ior() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("or ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void iand() {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("and ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void inot() {
        addInstructionTab("pop ax");
        addInstructionTab("not ax");
        addInstructionTab("push ax");
    }

    // Comparaisons

    private void comparaison(String instr) {
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("cmp ax,bx");
        addInstructionTab(instr + " $+6");
        addInstructionTab("push -1");
        addInstructionTab("jmp $+4");
        addInstructionTab("push 0");
    }

    @Override
    public void iinf() {
        comparaison("jge");
    }

    @Override
    public void isup() {
        comparaison("jle");
    }

    @Override
    public void iinfegal() {
       comparaison("jg");
    }

    @Override
    public void isupegal() {
        comparaison("jl");
    }

    @Override
    public void iegal() {
        comparaison("jne");
    }

    @Override
    public void idiff() {
        comparaison("je");
    }

    // Stockage et chargement

    @Override
    public void iload(int arg) {
        addInstructionTab("push word ptr [bp" + arg + "]");
    }

    @Override
    public void istore(int arg) {
        addInstructionTab("pop ax");
        addInstructionTab("mov word ptr [bp" + arg + "],ax");
    }

    @Override
    public void iconst(int arg) {
        addInstructionTab("push " + arg);
    }


}
