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
    
    //Une variable et non une constante
    Yaka.tabIdent.addIdent("test3", new IdVar(TypeList.ENTIER, -2));
    d.setConstanteExistante("test3");
    assertEquals(TypeList.ERREUR, d.getType());

    Yaka.tabIdent.addIdent("test3", new IdConst(2));
    d.setConstanteExistante("test3");
    assertEquals(TypeList.ENTIER, d.getType());
  }

/*  @Test*/
  //public void ajoutVarTest() {
    ////Test ajout d'une variable existant déjà
    //assertTrue(true);
  //}

  //@Test
  //public void ajoutConstTest() {

    //assertTrue(true);
    ////Test ajout d'une constante référençant une constante n'existant pas
    ////Test d'ajout d'une constante existant déjà
  /*}*/
}
