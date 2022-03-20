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
public class HillTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    writeKey(2, new int[][]{{5, 8}, {17, 3}}, 1);
    plainFile = Utils.writeOnFile("HillPlainTextFileScenario1.txt", "chaptertwosearch");
    cipherFile = Utils.writeOnFile("HillCipherTextFileScenario1.txt",
        "ODQTXXDIOASGGZOD".toLowerCase());
    validate();
  }

  @Test
  public void testScenario2() {
    writeKey(3, new int[][]{{17, 17, 5}, {21, 18, 21}, {2, 2, 19}}, 2);
    plainFile = Utils.writeOnFile("HillPlainTextFileScenario2.txt", "propertiesofsecurity");
    cipherFile = Utils.writeOnFile("HillCipherTextFileScenario2.txt",
        "QFSSQXLDAXHDUYETKSOOD".toLowerCase());
    validate();
  }

  private void validate() {
    try {

      Hill hill = new Hill(keyFile);
      File cipherFileAnswer = hill.encrypt(plainFile);
      File plainFileAnswer = hill.decrypt(cipherFileAnswer);

      Scanner cipherAnswerReader = new Scanner(cipherFileAnswer);
      Scanner cipherExpectedReader = new Scanner(cipherFile);
      while (cipherExpectedReader.hasNext()) {
        Assertions.assertEquals(cipherExpectedReader.nextLine(), cipherAnswerReader.nextLine());
      }

//      Scanner plainAnswerReader = new Scanner(plainFileAnswer);
//      Scanner plainExpectedReader = new Scanner(plainFile);
//      while (plainExpectedReader.hasNext()) {
//        Assertions.assertEquals(plainExpectedReader.nextLine(), plainAnswerReader.nextLine());
//      }

    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      Assertions.fail();
    }
  }

  private void writeKey(int n, int[][] key, int testNumber) {
    keyFile = new File("files/HillKeyFileScenario" + testNumber + ".txt");
    try (PrintWriter pw = new PrintWriter(keyFile)) {
      pw.println(n);
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          pw.print(key[i][j] + " ");
        }
        pw.println();
      }
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }
}
