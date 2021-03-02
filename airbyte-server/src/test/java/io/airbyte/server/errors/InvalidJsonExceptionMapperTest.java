package io.airbyte.server.errors;

import com.fasterxml.jackson.core.JsonParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class InvalidJsonExceptionMapperTest {
  JsonParseException jsonParseException;

  InvalidJsonExceptionMapper invalidJsonExceptionMapper = new InvalidJsonExceptionMapper();

  @BeforeEach
  void setup() {
    jsonParseException = mock(JsonParseException.class);
    when(jsonParseException.getOriginalMessage()).thenReturn("testMessage");
  }

  @Test
  void testExceptionMapper() {
    Response resp = invalidJsonExceptionMapper.toResponse(jsonParseException);

    verify(jsonParseException).getOriginalMessage();
  }
}
