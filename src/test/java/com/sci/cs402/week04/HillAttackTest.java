package com.sci.cs402.week04;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HillAttackTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    writeMatrix(2, new int[][]{{7, 8}, {11, 11}}, "files/HillPlainFileAttackTestScenario1.txt");
    writeMatrix(2, new int[][]{{7, 2}, {17, 25}}, "files/HillCipherFileAttackTestScenario1.txt");
    writeMatrix(2, new int[][]{{3, 2}, {8, 5}}, "files/HillKeyFileAttackTestScenario1.txt");
    validate();
  }

  private void validate() {
    try {

      HillAttack vigenereAttack = new HillAttack();
      File keyFileAnswer = vigenereAttack.attack(plainFile, cipherFile);

      Scanner keyAnswerReader = new Scanner(keyFileAnswer);
      Scanner keyExpectedReader = new Scanner(keyFile);
      while (keyExpectedReader.hasNext()) {
        Assertions.assertEquals(keyExpectedReader.nextLine().trim(),
            keyAnswerReader.nextLine().trim());
      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }

  private File writeMatrix(int n, int[][] key, String fileName) {
    File file = new File(fileName);
    try (PrintWriter pw = new PrintWriter(file)) {
      pw.println(n);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          pw.print(key[i][j] + " ");
        }
        pw.println();
      }
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      return null;
    }
    return file;
  }
}
