package compiler;

import java.util.Stack;
import java.util.EmptyStackException;

public class Yvm {

    protected String out;
    protected boolean commentMode = false;
	protected Stack<Integer> pileFaire;
	protected int indexFaire;

    public Yvm() {
        this.out = "";
        this.indexFaire = 0;
		this.pileFaire = new Stack<Integer>();
    }

    public String getOut() {
        return this.out;
    }
	
    private void addInstruction(String method) {
        if(this.commentMode) {
            this.out += "\n    ; ";
        }
        this.out += method + "\n";
    }

    /**
     * YVM Operations
     */

    public void entete() {
        addInstruction("entete");
    }

    public void ouvrePrinc(int arg) {
        addInstruction("ouvrPrinc " + arg);
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
        addInstruction("iload " + arg);
    }

    public void istore(int arg) {
        addInstruction("istore " + arg);
    }

    public void iconst(int arg) {
        addInstruction("iconst " + arg);
    }

    public void ecrireType(TypeList t) {
        switch(t) {
        case ENTIER:
            addInstruction("ecrireEnt");
            break;
        case BOOLEEN:
            addInstruction("ecrireBool");
            break;
        default:
            break;
        }
    }

    public void ecrireChaine(String s) {
        addInstruction("ecrireChaine " + s);
    }

    public void aLaLigne() {
        addInstruction("aLaLigne");
    }

    public void lireEnt(int o){
        addInstruction("lireEnt "+o);
    }
	
	
	//Etiquettes FAIRE
	
	public void ouvreFaire() {
		pileFaire.push(this.indexFaire);
		addInstruction("FAIRE"+this.indexFaire+":");
        this.indexFaire++;
	}
	
	public void iffauxFaire() {
		int i = this.pileFaire.peek();
		addInstruction("iffaux FAIT"+i);
	}
	
	public void fermeFaire() {
		int i = this.pileFaire.peek();
		addInstruction("goto FAIRE"+i);
		addInstruction("FAIT"+(i)+":");
		this.pileFaire.pop();
	}
}
