package compiler;

public class Yvm {

    protected String out;
    protected boolean commentMode = false;
	private Stack<Integer> pileFaireCounters;
	private int faireCounter = 1;

    public Yvm() {
        this.out = "";
		pileFaireCounters = new Stack<Integer>();
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
	
	public void majFaireCounter() {
		faireCounter++;
	}
	
	public void empileFaire() {
		pileFaireCounters.push(faireCounter);
		majFaireCounter();
	}
	
	public int regardeFaire() throws EmptyStackException {
		return pileFaireCounters.peek();
		//Yaka.ajoutLog("Pile des FAIRE vide. Impossible d'acceder au sommet de pile.");
	}
	
	public void depileFaire() throws EmptyStackException {
		pileFaireCounters.pop();
		//Yaka.ajoutLog("Pile des FAIRE vide. Impossible de depiler le sommet de pile.");
	}
	
	public void ouvreFaire() {
		empileFaire();
		addInstruction("FAIRE"+regardeFaire()+":");
	}
	
	public void fermeFaire() {
		addInstruction("FAIT"+regardeFaire()+":");
		depileFaire();
	}
}
