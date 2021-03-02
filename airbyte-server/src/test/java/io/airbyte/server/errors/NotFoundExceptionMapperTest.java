package io.airbyte.server.errors;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.ws.rs.NotFoundException;

public class NotFoundExceptionMapperTest {
  NotFoundExceptionMapper notFoundExceptionMapper = new NotFoundExceptionMapper();
  NotFoundException notFoundException;

  @BeforeEach
  void setup() {
    notFoundException = mock(NotFoundException.class);
    when(notFoundException.getMessage()).thenReturn("test");
  }

  @Test
  void testExceptionMapper() {
    notFoundExceptionMapper.toResponse(notFoundException);

    verify(notFoundException, times(2)).getMessage();
  }
}
