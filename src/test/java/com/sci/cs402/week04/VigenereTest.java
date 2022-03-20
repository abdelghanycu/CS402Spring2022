package com.sci.cs402.week04;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VigenereTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    keyFile = Utils.writeOnFile("VigenereKeyFileScenario1.txt", "deceptive");
    plainFile = Utils.writeOnFile("VigenerePlainTextFileScenario1.txt",
        "wearediscoveredsaveyourself");
    cipherFile = Utils.writeOnFile("VigenereCipherTextFileScenario1.txt",
        "ZICVTWQNGRZGVTWAVZHCQYGLMGJ".toLowerCase());
    validate();
  }

  @Test
  public void testScenario2() {
    keyFile = Utils.writeOnFile("VigenereKeyFileScenario2.txt", "leg");
    plainFile = Utils.writeOnFile("VigenerePlainTextFileScenario2.txt", "abdelhamid");
    cipherFile = Utils.writeOnFile("VigenereCipherTextFileScenario2.txt",
        "LFJPPNLQOO".toLowerCase());
    validate();
  }

  private void validate() {
    try {

      Vigenere vigenere = new Vigenere(keyFile);
      File cipherFileAnswer = vigenere.encrypt(plainFile);
      File plainFileAnswer = vigenere.decrypt(cipherFileAnswer);

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
