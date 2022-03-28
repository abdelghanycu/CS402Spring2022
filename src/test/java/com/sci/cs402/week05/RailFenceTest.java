package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RailFenceTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    keyFile = Utils.writeOnFile("RFKeyFileScenario1.txt", "5");
    plainFile = Utils.writeOnFile("RFPlainTextFileScenario1.txt",
        "Be confident in yourself. Nobody can make you feel inferior without your permission.");
    cipherFile = Utils.writeOnFile("RFCipherTextFileScenario1.txt",
        "bdy. eeio ieie of yck elrohurpso fnnulndaayf erttuesncntireoonmo if i ori.o sb unwym");
    validate();
  }

  @Test
  public void testScenario2() {
    keyFile = Utils.writeOnFile("RFKeyFileScenario2.txt", "2");
    plainFile = Utils.writeOnFile("RFPlainTextFileScenario2.txt",
        "Be confident in yourself. Nobody can make you feel inferior without your permission.");
    cipherFile = Utils.writeOnFile("RFCipherTextFileScenario2.txt",
        "b ofdn nyusl.nbd a aeyufe neirwtotyu emsinecnieti oref ooycnmk o elifro ihu orpriso.");
    validate();
  }

  @Test
  public void testScenario3() {
    keyFile = Utils.writeOnFile("RFKeyFileScenario3.txt", "10");
    plainFile = Utils.writeOnFile("RFPlainTextFileScenario3.txt",
        "Be confident in yourself. Nobody can make you feel inferior without your permission.");
    cipherFile = Utils.writeOnFile("RFCipherTextFileScenario3.txt",
        "bu e eornmfrrp ysaaniuec eckiooronl e rymnify l  if .dyewtsit ooeius.dnnbuftoineo ho");
    validate();
  }

  private void validate() {
    try {

      RailFence railFence = new RailFence(keyFile);
      File cipherFileAnswer = railFence.encrypt(plainFile);
      File plainFileAnswer = railFence.decrypt(cipherFileAnswer);

      Scanner cipherAnswerReader = new Scanner(cipherFileAnswer);
      Scanner cipherExpectedReader = new Scanner(cipherFile);
      while (cipherExpectedReader.hasNext()) {
        Assertions.assertEquals(cipherExpectedReader.nextLine(), cipherAnswerReader.nextLine());
      }

      Scanner plainAnswerReader = new Scanner(plainFileAnswer);
      Scanner plainExpectedReader = new Scanner(plainFile);
      while (plainExpectedReader.hasNext()) {
        Assertions.assertEquals(plainExpectedReader.nextLine(), plainAnswerReader.nextLine());
      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }
}
