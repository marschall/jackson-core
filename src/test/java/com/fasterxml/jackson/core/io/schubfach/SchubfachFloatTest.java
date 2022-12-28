package com.fasterxml.jackson.core.io.schubfach;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SchubfachFloatTest extends FloatToStringTest {
  @Override
  String f(float f) {
  char[] buffer = new char[AbstractFloatToDecimal.MAX_CHARS];
  int count = CharacterFloatToDecimal.appendTo(f, buffer, 0);
  return new String(buffer, 0, count);
  }
}
