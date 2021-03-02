/*
 * MIT License
 *
 * Copyright (c) 2020 Airbyte
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.airbyte.server.converters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.airbyte.commons.enums.Enums;
import io.airbyte.config.DataType;
import io.airbyte.protocol.models.AirbyteStream;
import io.airbyte.server.helpers.ConnectionHelpers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CatalogConverterTest {

  @Test
  void testConvertToProtocol() {
    assertEquals(ConnectionHelpers.generateBasicConfiguredAirbyteCatalog(), CatalogConverter.toProtocol(ConnectionHelpers.generateBasicApiCatalog()));
  }

  @Test
  void testConvertToAPI() {
    assertEquals(ConnectionHelpers.generateBasicApiCatalog(), CatalogConverter.toApi(ConnectionHelpers.generateBasicConfiguredAirbyteCatalog()));
  }

  @Test
  void testEnumConversion() {
    assertTrue(Enums.isCompatible(io.airbyte.api.model.DataType.class, DataType.class));
    assertTrue(Enums.isCompatible(io.airbyte.config.SyncMode.class, io.airbyte.api.model.SyncMode.class));
  }

  @Test
  void testConvertToAPIWithMocks() throws JsonProcessingException {
    final AirbyteStream stream = mock(AirbyteStream.class);
    JsonMapper mapper = JsonMapper.builder().build();

    when(stream.getName()).thenReturn("default");
    when(stream.getJsonSchema()).thenReturn(mapper.readTree("{}"));
    when(stream.getSupportedSyncModes()).thenReturn(new ArrayList<>());
    when(stream.getSourceDefinedCursor()).thenReturn(null);
    when(stream.getDefaultCursorField()).thenReturn(null);

    io.airbyte.api.model.AirbyteStream stream1 = CatalogConverter.toApi(stream);
    assertNotNull(stream1);
  }

}
