package com.sci.cs402.week05;

import com.sci.cs402.utilities.Utils;
import java.io.File;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RowTranspositionTest {

  private File keyFile, plainFile, cipherFile;

  @Test
  public void testScenario1() {
    keyFile = Utils.writeOnFile("RTKeyFileScenario1.txt", "3\n 2 1 0");
    plainFile = Utils.writeOnFile("RTPlainTextFileScenario1.txt",
        "don't waste your time with explanations, people only hear what they want to hear");
    cipherFile = Utils.writeOnFile("RTCipherTextFileScenario1.txt",
        "n s utei pnispp lhrh ewtoeotaeo mwhxatn oen awth nthrd'wtyri telao,eloye atya  a");
    validate();
  }

  @Test
  public void testScenario2() {
    keyFile = Utils.writeOnFile("RTKeyFileScenario2.txt", "5\n 0 4 1 2 3");
    plainFile = Utils.writeOnFile("RTPlainTextFileScenario2.txt",
        "don't waste your time with explanations, people only hear what they want to hear");
    cipherFile = Utils.writeOnFile("RTCipherTextFileScenario2.txt",
        "d erehli llaaen naytwenne      e'soiixasoohwtwtattumtpt,pnehhaorow    aopeyrtyth");
    validate();
  }

  @Test
  public void testScenario3() {
    keyFile = Utils.writeOnFile("RTKeyFileScenario3.txt", "7\n 5 1 3 2 0 6 4");
    plainFile = Utils.writeOnFile("RTPlainTextFileScenario3.txt",
        "don't waste your time with explanations, people only hear what they want to hear");
    cipherFile = Utils.writeOnFile("RTCipherTextFileScenario3.txt",
        "t ihn,eha oosrwpool hta'ettasl hytnt ilnpywe rwoeetpoa ahdau xienrtne ym a  etw");
    validate();
  }

  private void validate() {
    try {

      RowTransposition rowTransposition = new RowTransposition(keyFile);
      File cipherFileAnswer = rowTransposition.encrypt(plainFile);
      File plainFileAnswer = rowTransposition.decrypt(cipherFileAnswer);

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
