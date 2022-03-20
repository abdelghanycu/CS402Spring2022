package com.sci.cs402.week04;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VigenereAttackTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    plainFile = Utils.writeOnFile("VigenerePlainTextAttackFileScenario1.txt",
        "wearediscoveredsaveyourself");
    cipherFile = Utils.writeOnFile("VigenereCipherTextAttackFileScenario1.txt",
        "zicvtwqngrzgvtwavzhcqyglmgj");
    keyFile = Utils.writeOnFile("VigenereKeyFileScenario1.txt", "deceptive");
    validate();
  }

  private void validate() {
    try {

      VigenereAttack vigenereAttack = new VigenereAttack();
      File keyFileAnswer = vigenereAttack.attack(plainFile, cipherFile);

      Scanner keyAnswerReader = new Scanner(keyFileAnswer);
      Scanner keyExpectedReader = new Scanner(keyFile);
      while (keyExpectedReader.hasNext()) {
        Assertions.assertEquals(keyExpectedReader.nextLine(), keyAnswerReader.nextLine());
      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }
}
