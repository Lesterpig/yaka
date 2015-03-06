package compiler;

import java.util.EmptyStackException;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ExpressionTest {

    private Expression e;

    private Operateur plus;
    private Operateur inf;
    private Operateur egal;
    private Operateur non;

    @Before
    public void init() {
        plus = new Operateur(OpList.PLUS);
        inf  = new Operateur(OpList.INF);
        egal = new Operateur(OpList.EG);
        non  = new Operateur(OpList.NON);
    }

    @Test
    public void ajoutRetraitOperateurTest() {
        e = new Expression();

        e.ajoutOperateur(plus);
        e.ajoutOperateur(inf);

        assertEquals(inf, e.retraitOperateur());
        assertEquals(plus, e.retraitOperateur());

        try {
            e.retraitOperateur();
            fail("Should throws an exception");
        } catch(EmptyStackException e) {}
    }

    @Test
    public void ajoutRetraitTypeTest() {
        e = new Expression();

        e.ajoutType(TypeList.ENTIER);
        e.ajoutType(TypeList.BOOLEEN);

        assertEquals(TypeList.BOOLEEN, e.retraitType());
        assertEquals(TypeList.ENTIER, e.retraitType());

        try {
            e.retraitType();
            fail("Should throws an exception");
        } catch(EmptyStackException e) {}
    }

    @Test
    public void traiterOperationTest() {
        e = new Expression();

        e.ajoutType(TypeList.ENTIER);
        e.ajoutType(TypeList.ENTIER);
        e.ajoutOperateur(plus);

        e.traiterOperation();
        assertEquals(TypeList.ENTIER, e.retraitType());
        e.ajoutType(TypeList.ENTIER);

        e.ajoutType(TypeList.BOOLEEN);
        e.ajoutOperateur(non);

        e.traiterOperation();
        assertEquals(TypeList.BOOLEEN, e.retraitType());
        e.ajoutType(TypeList.BOOLEEN);
        e.ajoutOperateur(inf);

        e.traiterOperation();
        assertEquals(TypeList.ERREUR, e.retraitType());
    }
}
