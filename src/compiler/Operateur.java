package compiler;

import java.util.EmptyStackException;

enum OpList { SUP, INF, SUPEG, INFEG, EG, DIFF, PLUS, MOINS, OU, FOIS, DIV, ET, NEG, NON };

public class Operateur {
    private OpList operateur;
    private int nbArgs;

    public Operateur(OpList o) {
        operateur = o;
        if( o == OpList.NEG ||  o == OpList.NON)
            nbArgs = 1;
        else
            nbArgs = 2;
    }

    public boolean equals(Operateur e) {
        return this.operateur == e.operateur;
    }

    public OpList getOp() {
        return operateur;
    }

    public void setOp(OpList o) {
        operateur = o;
    }

    public int getNbArgs() {
        return nbArgs;
    }

    public void setNbArgs(int n) {
        nbArgs = n;
    }

    /*
    Cette methode est apellee dans la methode valider,
    avec en argument les types des
    deux operandes pour un operateur binaire, et deux fois
    le type de la seule operande pour un operateur unaire.
    */
    public TypeList typeValide(TypeList t1, TypeList t2) {
        if(t1 != t2 || t1 == TypeList.ERREUR)
            return TypeList.ERREUR;
        switch(operateur) {
            case SUP:
            case INF:
            case SUPEG:
            case INFEG:
                if(t1 == TypeList.ENTIER)
                    return TypeList.BOOLEEN;
                else
                    return TypeList.ERREUR;
            case PLUS:
            case MOINS:
            case FOIS:
            case DIV:
            case NEG:
                if(t1 == TypeList.ENTIER)
                    return TypeList.ENTIER;
                else
                    return TypeList.ERREUR;
            case OU:
            case ET:
            case NON:
                if(t1 == TypeList.BOOLEEN)
                    return TypeList.BOOLEEN;
                else
                    return TypeList.ERREUR;
            case EG:
            case DIFF:
                //on est dans le cas (t1!=ERREUR) == (t2!=ERREUR), donc pas de probleme.
                return TypeList.BOOLEEN;
            default:
                return TypeList.ERREUR;
        }
    }

    /** TODO REFACTOR : valider doit être déplacée dans Expression */

    /*
    Cette methode depile les types des parametres de la pile
    associee a l'expression passee en parametre,
    et reempile le type resultat : ENTIER / BOOLEEN / ERREUR.
    En pratique, elle est appellee dans la classe Expression,
    du fait de l'utilisation d'une instance statique de la classe Expression.
     */
    public void valider(Expression e) throws EmptyStackException {
        //operateur unaire.
        if(nbArgs == 1) {
            TypeList a = e.retraitType();
            e.ajoutType(typeValide(a, a));
        }
        //operateur binaire.
        if(nbArgs == 2) {
            TypeList a = e.retraitType();
            TypeList b = e.retraitType();
            e.ajoutType(typeValide(a, b));
        }
    }

}
