package compiler;

public class Yvm {

    protected String out;

    public Yvm() {
        this.out = "";
    }

    public String getOut() {
        return this.out;
    }

    protected void addInstruction(String method) {
        this.out += method + "\n";
    }

    private void addInstruction(String method, String arg) {
        this.out += method + " " + arg + "\n";
    }

    private void addInstruction(String method, int arg) {
        this.out += method + " " + arg + "\n";
    }

    /**
     * YVM Operations
     */

    public void entete() {
        addInstruction("entete");
    }

    public void ouvrePrinc(int arg) {
        addInstruction("ouvrPrinc", arg);
    }

    public void queue() {
        addInstruction("queue");
    }

    // Arithmétique

    //// Opérations sur entiers

    public void iadd() {
        addInstruction("iadd");
    }

    public void idiv() {
        addInstruction("idiv");
    }

    public void isub() {
        addInstruction("isub");
    }

    public void imul() {
        addInstruction("imul");
    }

    public void ineg() {
        addInstruction("ineg");
    }

    //// Opérations sur booleens

    public void ior() {
        addInstruction("ineg");
    }

    public void iand() {
        addInstruction("ineg");
    }

    public void inot() {
        addInstruction("ineg");
    }


    // Comparaisons

    public void iinf() {
        addInstruction("iinf");
    }

    public void isup() {
        addInstruction("isup");
    }

    public void iinfegal() {
        addInstruction("iinfegal");
    }

    public void isupegal() {
        addInstruction("isupegal");
    }

    public void iegal() {
        addInstruction("iegal");
    }

    public void idiff() {
        addInstruction("idiff");
    }

    // Stockage et chargement

    public void iload(int arg) {
        addInstruction("iload", arg);
    }

    public void istore(int arg) {
        addInstruction("istore", arg);
    }

    public void iconst(int arg) {
        addInstruction("iconst", arg);
    }

}
