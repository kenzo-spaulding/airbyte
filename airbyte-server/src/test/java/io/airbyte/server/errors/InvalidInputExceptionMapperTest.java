package io.airbyte.server.errors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

import java.util.Set;

import static org.mockito.Mockito.*;

public class InvalidInputExceptionMapperTest {
  ConstraintViolationException constraintViolationException;
  ConstraintViolation constraintViolation;

  InvalidInputExceptionMapper exceptionMapper = new InvalidInputExceptionMapper();

  @BeforeEach
  void setup() {
    constraintViolationException = mock(ConstraintViolationException.class);
    constraintViolation = mock(ConstraintViolation.class);

    when(constraintViolation.getPropertyPath()).thenReturn(null);
    when(constraintViolation.getMessage()).thenReturn("test");
    when(constraintViolation.getInvalidValue()).thenReturn("testObj");

    when(constraintViolationException.getConstraintViolations()).thenReturn(Set.of(constraintViolation));
  }

  @Test
  void testExceptionMapper() {
    Response resp = exceptionMapper.toResponse(constraintViolationException);

    verify(constraintViolation).getMessage();
    verify(constraintViolation).getPropertyPath();
    verify(constraintViolation).getInvalidValue();
    verify(constraintViolationException).getConstraintViolations();
  }
}
