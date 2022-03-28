package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RailFenceAttackTest {

  private File cipherFile, attackResultFile;

  @Test
  public void testScenario1() {
    cipherFile = Utils.writeOnFile("RFCipherAttackFileScenario1.txt",
        "abdelghany");
    attackResultFile = Utils.writeOnFile("RFResultFileScenario1.txt",
        "agbhdaenly\n"
            + "aenlbgyhda\n"
            + "adgnheblay\n"
            + "adgaynhebl\n"
            + "abegaynhld\n"
            + "abdegaynhl\n"
            + "abdelgaynh\n"
            + "abdelghayn\n"
            + "abdelghany");
    validate();
  }

  private void validate() {
    try {

      RailFenceAttack railFenceAttack = new RailFenceAttack();
      File possiblePlainTextFileAnswer = railFenceAttack.attack(cipherFile);

      Scanner possiblePlainTextFileAnswerReader = new Scanner(possiblePlainTextFileAnswer);

      Scanner resultExpectedReader = new Scanner(attackResultFile);
      while (resultExpectedReader.hasNext()) {
        Assertions.assertEquals(resultExpectedReader.nextLine(), possiblePlainTextFileAnswerReader.nextLine());
      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }
}
