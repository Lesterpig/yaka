package compiler;

public class YvmAsm extends Yvm {

    private int messCounter = 0;

    public YvmAsm() {
        super();
        this.commentMode = true;
    }

    private void addInstruction(String method) {
        this.out += method + "\n";
    }

    private void addInstructionTab(String arg) {
        this.addInstruction("    " + arg);
    }

    @Override
    public void entete() {
        super.entete();
        addInstruction("extrn lirent:proc, ecrent:proc");
        addInstruction("extrn ecrbool:proc");
        addInstruction("extrn ecrch:proc, ligsuiv:proc");
        addInstruction(".model SMALL");
        addInstruction(".586");
        addInstruction(".CODE");
        addInstruction("debut:");
        addInstructionTab("STARTUPCODE");
    }

    @Override
    public void ouvrePrinc(int arg) {
        super.ouvrePrinc(arg);
        addInstructionTab("mov bp,sp");
        addInstructionTab("sub sp," + arg);
    }

    @Override
    public void queue() {
        super.queue();
        addInstructionTab("nop");
        addInstructionTab("exitcode");
        addInstructionTab("end debut");
    }

    // Arithmétiques

    @Override
    public void iadd() {
        super.iadd();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("add ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void isub() {
        super.isub();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("sub ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void imul() {
        super.imul();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("imul bx");
        addInstructionTab("push ax");
    }

    @Override
    public void idiv() {
        super.idiv();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("cwd");
        addInstructionTab("idiv bx");
        addInstructionTab("push ax");
    }

    @Override
    public void ineg() {
        super.ineg();
        addInstructionTab("pop ax");
        addInstructionTab("mov bx,-1");
        addInstructionTab("imul bx");
        addInstructionTab("push ax");
    }

    @Override
    public void ior() {
        super.ior();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("or ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void iand() {
        super.iand();
        addInstructionTab("pop bx");
        addInstructionTab("pop ax");
        addInstructionTab("and ax,bx");
        addInstructionTab("push ax");
    }

    @Override
    public void inot() {
        super.inot();
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
        super.iinf();
        comparaison("jge");
    }

    @Override
    public void isup() {
        super.isup();
        comparaison("jle");
    }

    @Override
    public void iinfegal() {
        super.iinfegal();
        comparaison("jg");
    }

    @Override
    public void isupegal() {
        super.isupegal();
        comparaison("jl");
    }

    @Override
    public void iegal() {
        super.iegal();
        comparaison("jne");
    }

    @Override
    public void idiff() {
        super.idiff();
        comparaison("je");
    }

    // Stockage et chargement

    @Override
    public void iload(int arg) {
        super.iload(arg);
        addInstructionTab("push word ptr [bp" + arg + "]");
    }

    @Override
    public void istore(int arg) {
        super.istore(arg);
        addInstructionTab("pop ax");
        addInstructionTab("mov word ptr [bp" + arg + "],ax");
    }

    @Override
    public void iconst(int arg) {
        super.iconst(arg);
        addInstructionTab("push " + arg);
    }

    // Entrees/Sorties

    //// Sortie

    @Override
    public void ecrireType(TypeList t) {
        super.ecrireType(t);
        switch(t) {
            case ENTIER:
                addInstructionTab("call ecrent");
                break;
            case BOOLEEN:
                addInstructionTab("call ecrbool");
                break;
            default:
                break;
        }
    }

    @Override
    public void ecrireChaine(String s) {
        super.ecrireChaine(s);
        addInstruction(".DATA");
        s = s.substring(1, s.length()-1);
        addInstructionTab("mess" + messCounter + " DB \"" + s + "$\"");
        addInstruction(".CODE");
        addInstructionTab("lea dx,mess"+messCounter++);
        addInstructionTab("push dx");
        addInstructionTab("call ecrch");
    }

    @Override
    public void aLaLigne() {
        super.aLaLigne();
        addInstructionTab("call ligsuiv");
    }

    //// Entree

    @Override
    public void lireEnt(int arg) {
        super.lireEnt(arg);
        addInstructionTab("lea dx,[bp"+arg+"]");
        addInstructionTab("push dx");
        addInstructionTab("call lirent");
    }

    //CONDITIONNELLE
    
    @Override
    public int goToFsiCond() {
      int i = super.goToFsiCond();
      addInstructionTab("jmp FSI"+i);
      addInstruction("SINON"+i+":");
      return i;
    }

    @Override 
    public int sinonCond() {
      int i = super.sinonCond();
      addInstructionTab("pop ax");
      addInstructionTab("cmp ax,0");
      addInstructionTab("je SINON"+i);
      return i;
    }
    
    @Override 
    public int fsiCond() {
      int i = super.fsiCond();
      addInstructionTab("FSI"+i+":");
      return i;
    }

    //// ITERATIONS
    
    @Override
    public int ouvreFaire() {
        int i = super.ouvreFaire();
        addInstruction("FAIRE"+i+":");
        return i;
    }

    @Override
    public int fermeFaire() {
        int i = super.fermeFaire();
        addInstruction("FAIT"+i+":");
        return i;
    }

    @Override
    public int goToFaire() {
        int i = super.goToFaire();
        addInstructionTab("jmp FAIRE"+i);
        return i;
    }

    @Override
    public int iffauxFaire() {
        int i = super.iffauxFaire();
        addInstructionTab("pop ax");
        addInstructionTab("cmp ax,0");
        addInstructionTab("je FAIT"+i);
        return i;
    }

    //Fonctions
    //@Override
    //public int principal() {
    //}

    //@Override
    //public void ecrireFonc() {

    //}
    
}
