package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OneTimePadAttackTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    keyFile = Utils.writeOnFile("OTPAttackKeyFileScenario1.txt", "ymk");
    plainFile = Utils.writeOnFile("OTPAttackPlainTextFileScenario1.txt", "abc");
    cipherFile = Utils.writeOnFile("OTPAttackCipherTextFileScenario1.txt", "ynm");
    validate();
  }

  private void validate() {
    try {

      OneTimePadAttack oneTimePadAttack = new OneTimePadAttack();
      File keyFileAnswer = oneTimePadAttack.attack(plainFile, cipherFile);

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
