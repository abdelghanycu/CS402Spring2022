package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RowTranspositionAttackTest {

  private File cipherFile, attackResultFile;

  @Test
  public void testScenario1() {
    cipherFile = Utils.writeOnFile("RTCipherAttackFileScenario1.txt",
        "abc");
    attackResultFile = Utils.writeOnFile("RTResultFileScenario1.txt",
        "acb\n"
            + "bac\n"
            + "abc\n"
            + "bac\n"
            + "cab\n"
            + "acb\n"
            + "bca\n"
            + "cba");
    validate();
  }

  private void validate() {
    try {

      RowTranspositionAttack rowTranspositionAttack = new RowTranspositionAttack();
      File possiblePlainTextFileAnswer = rowTranspositionAttack.attack(cipherFile);

      Scanner possiblePlainTextFileAnswerReader = new Scanner(possiblePlainTextFileAnswer);

      Scanner resultExpectedReader = new Scanner(attackResultFile);
      while (resultExpectedReader.hasNext()) {
        Assertions.assertEquals(resultExpectedReader.nextLine(),
            possiblePlainTextFileAnswerReader.nextLine());
      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }
}
