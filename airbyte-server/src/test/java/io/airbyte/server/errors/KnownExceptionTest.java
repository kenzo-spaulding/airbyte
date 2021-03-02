package io.airbyte.server.errors;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.io.IOException;

public class KnownExceptionTest {
  private final IOException ioException = new IOException("test");

  @Test
  void testExceptionMapper() {
    KnownException knownException = new KnownException(400, "test message", ioException);
    int code = knownException.getHttpCode();

    assertNotNull(knownException);
    assertEquals(code, 400);
  }
}
