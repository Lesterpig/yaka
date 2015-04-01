package compiler;

import java.util.Stack;
import java.util.EmptyStackException;

public class Yvm {

    protected String out;
    protected boolean commentMode = false;

    //ITERATION
    protected Stack<Integer> pileFaire;
    protected int indexFaire;

    //CONDITIONNELLE
    protected Stack<Integer> pileCond;
    protected int indexCond;

    //FONCTIONS
    protected Stack<String> pileFonc;
    protected int indexParam;
    
    public Yvm() {
        this.out = "";
        //ITERATION
        this.indexFaire = 0;
        this.pileFaire = new Stack<Integer>();
        //CONDITIONNELLE
        this.indexCond = 0;
        this.pileCond = new Stack<Integer>();
        //FONCTIONS
        this.pileFonc = new Stack<String>();
        this.indexParam = 0;
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


    //CONDITIONNELLE

    public int siCond() {
        pileCond.push(this.indexCond);
        return this.indexCond++;
    }

    public int sinonCond() {
        int i = this.pileCond.peek();
        addInstruction("iffaux SINON"+i);
        return i;
    }

    public int goToFsiCond() {
        int i = this.pileCond.peek();
        addInstruction("goto FSI"+i);
        addInstruction("SINON"+i+":");
        return i;
    }

    public int fsiCond() {
        int i = this.pileCond.pop();
        addInstruction("FSI"+i+":");
        return i;
    }

    //Etiquettes FAIRE

    public int ouvreFaire() {
        pileFaire.push(this.indexFaire);
        addInstruction("FAIRE"+this.indexFaire+":");
        return this.indexFaire++;
    }

    public int iffauxFaire() {
        int i = this.pileFaire.peek();
        addInstruction("iffaux FAIT"+i);
        return i;
    }

    public int fermeFaire() {
        int i = this.pileFaire.pop();
        addInstruction("FAIT"+(i)+":");
        return i;
    }

    public int goToFaire() {
        int i = this.pileFaire.peek();
        addInstruction("goto FAIRE"+i);
        return i;
    }

    //FONCTIONS
    //PILE
    public void ajoutFonc(String nom) {
        pileFonc.push(nom);
    }
    
    public String retraitFonc() {
       return pileFonc.pop();
    }
    
    public String regardeFonc() {
        return pileFonc.peek();
    }
    
    public void incIndexParam() {
        indexParam++;
    }
    
    public int getIndexParam() {
        return indexParam;
    }
    
    public void resetIndexParam() {
        indexParam = 0;
    }
    
    //METHODES
    public void principal() {
        addInstruction("main:");
    }
    
    public void ecrireFonc(String nom) {
        addInstruction(nom + ":");
    }
    
    //prend en param le nb de var locales
    public void ouvreBloc(int n) {
        addInstruction("ouvreBloc "+2*n);
    }
    
    //prend en param le nb de param
    public void fermeBloc(int n) {
        addInstruction("fermeBloc "+2*n);
    }
    
    public void reserveRetour() {
        addInstruction("reserveRetour");
    }
    
    public void callFonc() {
        addInstruction("call "+regardeFonc());
    }
    
    //prend en param le nb de param
    public void retourne(int  n) {
        addInstruction("ireturn "+ (n+2)*2);
    }
}
