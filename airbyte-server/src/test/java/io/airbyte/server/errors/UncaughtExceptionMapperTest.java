package io.airbyte.server.errors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class UncaughtExceptionMapperTest {
  UncaughtExceptionMapper uncaughtExceptionMapper = new UncaughtExceptionMapper();
  IOException ioException = new IOException();

  @Test
  void testExceptionMapper() {
    Response response = uncaughtExceptionMapper.toResponse(ioException);

    assertNotNull(response);
    assertEquals(response.getMediaType(), MediaType.APPLICATION_JSON_TYPE);
  }
}
