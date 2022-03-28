package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OneTimePadTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    keyFile = Utils.writeOnFile("OTPKeyFileScenario1.txt",
        "lmbidzxwoxzitxrymatcjvnphareclueizufoldscujdigxp");
    plainFile = Utils.writeOnFile("OTPPlainTextFileScenario1.txt",
        "itissaidthattheonetimepadisthebestcipheranywhere");
    cipherFile = Utils.writeOnFile("OTPCipherTextFileScenario1.txt",
        "tfjavzfzhezbmevmzemkvzcpkijxjpviaswndshjchhzpkot");
    validate();
  }

  @Test
  public void testScenario2() {
    keyFile = Utils.writeOnFile("OTPKeyFileScenario2.txt",
        "cgcfvydltsnhlcloxqkwqhxvgobdfjkfhdsvplmvbirnsows");
    plainFile = Utils.writeOnFile("OTPPlainTextFileScenario2.txt",
        "itissaidthattheonetimepadisthebestcipheranywhere");
    cipherFile = Utils.writeOnFile("OTPCipherTextFileScenario2.txt",
        "kzkxnylomznaejpckudeclmvjwtwmnljzwudesqmbvpjzsnw");
    validate();
  }

  @Test
  public void testScenario3() {
    keyFile = Utils.writeOnFile("OTPKeyFileScenario3.txt",
        "oyxzfejrvsgzinlapgpuvebnezfkuoaivixtcdroifilirml");
    plainFile = Utils.writeOnFile("OTPPlainTextFileScenario3.txt", "onetimepad");
    cipherFile = Utils.writeOnFile("OTPCipherTextFileScenario3.txt", "clbsnqngvv");
    validate();
  }

  private void validate() {
    try {

      OneTimePad oneTimePad = new OneTimePad(keyFile);
      File cipherFileAnswer = oneTimePad.encrypt(plainFile);
      File plainFileAnswer = oneTimePad.decrypt(cipherFileAnswer);

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
