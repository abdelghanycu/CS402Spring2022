package com.sci.cs402.utils;

public class Mod26 {

  public int inv(int a) {
    for (int i = 1; i < 26; i++) {
      if ((a * i) % 26 == 1) {
        return i;
      }
    }
    return -1;
  }

  public int firstPositiveNum(int a) {
    while (a < 0) {
      a += 26;
    }
    a %= 26;
    return a;
  }
}
