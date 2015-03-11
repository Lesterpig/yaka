package compiler;

import generated.*;
import java.util.EmptyStackException;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class DeclarationTest {

  private Declaration d;

  @Before
  public void init() {
    d = new Declaration();
    Yaka.tabIdent = new TabIdent();
  }

  @Test
  public void setEntierTest() {
    d.setEntier(5);
    assertEquals(d.getType(), TypeList.ENTIER);
  }
  
  @Test
  public void setBooleenTest() {
    d.setBooleen(true);
    assertEquals(d.getType(), TypeList.BOOLEEN);
  }

  @Test
  public void identValideTest() {
    d.setIdent("test2");
    assertTrue(d.identValide());
    Yaka.tabIdent.addIdent("test2", new IdConst(5));
    assertFalse(d.identValide());
  }

  @Test 
  public void setConstanteExistanteTest() { 
    //Pas de constante existante nommée test
    d.setConstanteExistante("test3");
    assertEquals(TypeList.ERREUR, d.getType());
    
    //Une variable et non une constante existe et possède ce nom
    Yaka.tabIdent.addIdent("test3", new IdVar(TypeList.ENTIER, -2));
    d.setConstanteExistante("test3");
    assertEquals(TypeList.ERREUR, d.getType());

    //Tout se passe bien pour un entier
    Yaka.tabIdent.addIdent("test3", new IdConst(2));
    d.setConstanteExistante("test3");
    assertEquals(TypeList.ENTIER, d.getType());
    
    //Tout se passe bien pour un booleen
    Yaka.tabIdent.addIdent("test4", new IdConst(true));
    d.setConstanteExistante("test4");
    assertEquals(TypeList.BOOLEEN, d.getType());
  }

  @Test
  public void ajoutVarTest() {

    d.setIdent("test5");
    d.ajoutVar();
    assertEquals(d.getErrorLog(),"");
    assertTrue(Yaka.tabIdent.existIdent("test5"));

    //Test ajout d'une variable existant déjà
    d.setIdent("test5");
    d.ajoutVar();
    assertNotEquals(d.getErrorLog(),"");

  }

  @Test
  public void ajoutConstTest() {
    //Test ajout d'une constante entier
    d.setIdent("test6");
    d.setEntier(5);
    d.ajoutConst();
    assertEquals(d.getErrorLog(),"");
    assertTrue(Yaka.tabIdent.existIdent("test6"));

    //Test ajout d'une constante à partir d'une autre constante
    d.setIdent("test7");
    d.setConstanteExistante("test6");
    d.ajoutConst();
    assertEquals(d.getErrorLog(),"");
    assertTrue(Yaka.tabIdent.existIdent("test7"));

    //Test d'ajout d'une constante existant déjà
    d.setIdent("test6");
    d.setEntier(6);
    d.ajoutConst();
    assertNotEquals(d.getErrorLog(),"");
  }
}
