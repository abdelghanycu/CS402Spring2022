package com.sci.cs402.utilities;

import java.io.File;
import java.io.PrintWriter;

public class Utils {

  public static String generateRandomPlainText() {
    int sz = 15 + (int) (Math.random() * 7);
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < sz; i++) {
      res.append((char) ('a' + (int) (Math.random() * 26)));
    }
    return res.toString();
  }

  public static File writeOnFile(String FileName, String text) {
    File file = new File("files/" + FileName);
    try (PrintWriter pw = new PrintWriter(file)) {
      pw.println(text);
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
      return null;
    }
    return file;
  }
}
