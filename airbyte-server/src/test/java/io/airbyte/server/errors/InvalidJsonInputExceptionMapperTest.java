package io.airbyte.server.errors;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;

public class InvalidJsonInputExceptionMapperTest {
  JsonMappingException jsonParseException;

  InvalidJsonInputExceptionMapper invalidJsonInputExceptionMapper = new InvalidJsonInputExceptionMapper();

  @BeforeEach
  void setup() {
    jsonParseException = mock(JsonMappingException.class);
    when(jsonParseException.getOriginalMessage()).thenReturn("testMessage");
  }

  @Test
  void testExceptionMapper() {
    Response resp = invalidJsonInputExceptionMapper.toResponse(jsonParseException);

    verify(jsonParseException).getOriginalMessage();
  }
}
