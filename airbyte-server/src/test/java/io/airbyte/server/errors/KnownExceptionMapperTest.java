package io.airbyte.server.errors;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KnownExceptionMapperTest {
  KnownExceptionMapper knownExceptionMapper = new KnownExceptionMapper();
  KnownException knownException;

  @BeforeEach
  void setup() {
    knownException = mock(KnownException.class);
    when(knownException.getMessage()).thenReturn("test");
  }

  @Test
  void testExceptionMapper() {
    knownExceptionMapper.toResponse(knownException);

    verify(knownException, times(2)).getMessage();
  }
}
