package compiler;

import static org.junit.Assert.*;
import org.junit.Test;

public class OperateurTest {

    private Operateur a;
    private Operateur b;

    @Test
    public void testEquals() {
        a = new Operateur(OpList.PLUS);
        b = new Operateur(OpList.PLUS);
        assertTrue(a.equals(b));
        b = new Operateur(OpList.NEG);
        assertFalse(a.equals(b));
    }

    @Test
    public void testNbArgs() {
        a = new Operateur(OpList.SUP);
        assertEquals(2, a.getNbArgs());
        b = new Operateur(OpList.NEG);
        assertEquals(1, b.getNbArgs());
    }

    @Test
    public void testErreur() {
        a = new Operateur(OpList.PLUS);
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.ENTIER, TypeList.BOOLEEN));
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.BOOLEEN, TypeList.ENTIER));
        assertEquals(TypeList.ERREUR, a.typeValide(null, null));
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.ERREUR, TypeList.ENTIER));
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.ENTIER, TypeList.ERREUR));
    }

    @Test
    public void testPlus() {
        a = new Operateur(OpList.PLUS);
        assertEquals(TypeList.ENTIER, a.typeValide(TypeList.ENTIER, TypeList.ENTIER));
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.BOOLEEN, TypeList.BOOLEEN));
    }

    @Test
    public void testInf() {
        a = new Operateur(OpList.INF);
        assertEquals(TypeList.BOOLEEN, a.typeValide(TypeList.ENTIER, TypeList.ENTIER));
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.BOOLEEN, TypeList.BOOLEEN));
    }

    @Test
    public void testEgal() {
        a = new Operateur(OpList.EG);
        assertEquals(TypeList.BOOLEEN, a.typeValide(TypeList.ENTIER, TypeList.ENTIER));
        assertEquals(TypeList.BOOLEEN, a.typeValide(TypeList.BOOLEEN, TypeList.BOOLEEN));
    }

    @Test
    public void testEt() {
        a = new Operateur(OpList.ET);
        assertEquals(TypeList.ERREUR, a.typeValide(TypeList.ENTIER, TypeList.ENTIER));
        assertEquals(TypeList.BOOLEEN, a.typeValide(TypeList.BOOLEEN, TypeList.BOOLEEN));
    }

}
