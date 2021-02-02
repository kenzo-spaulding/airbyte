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

package io.airbyte.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestAirbyteVersion {

  @Test
  public void testParseVersion() {
    final AirbyteVersion version = new AirbyteVersion("6.7.8");
    assertEquals("6", version.getMajorVersion());
    assertEquals("7", version.getMinorVersion());
    assertEquals("8", version.getPatchVersion());
  }

  @Test
  public void testParseVersionWithLabel() {
    final AirbyteVersion version = new AirbyteVersion("6.7.8-omega");
    assertEquals("6", version.getMajorVersion());
    assertEquals("7", version.getMinorVersion());
    assertEquals("8", version.getPatchVersion());
  }

  @Test
  public void testParseVersionInvalidFormat() {
    final AirbyteVersion version = new AirbyteVersion("6-b-8");
    assertEquals("6", version.getMajorVersion());
    assertEquals("", version.getMinorVersion());
    assertEquals("", version.getPatchVersion());
  }

  @Test
  public void testParseVersionNonNumeric() {
    final AirbyteVersion version = new AirbyteVersion("a.b.8");
    assertEquals("a", version.getMajorVersion());
    assertEquals("b", version.getMinorVersion());
    assertEquals("8", version.getPatchVersion());
  }

  @Test
  public void testCompareSameVersionsDifferentLabels() {
    assertEquals(0, new AirbyteVersion("6.7.8-omega").compareTo(new AirbyteVersion("6.7.8-gamma")));
  }

  @Test
  public void testCompareDifferentPatchVersions() {
    assertEquals(0, new AirbyteVersion("6.7.8-alpha").compareTo(new AirbyteVersion("6.7.9-alpha")));
  }

  @Test
  public void testCompareDifferentMinorVersions() {
    assertEquals(1, new AirbyteVersion("6.8.0-alpha").compareTo(new AirbyteVersion("6.7.8-alpha")));
  }

  @Test
  public void testCompareDifferentMinorVersionsNeg() {
    assertEquals(1, new AirbyteVersion("6.8.0-alpha").compareTo(new AirbyteVersion("6.-7.8-alpha")));
  }

  @Test
  public void testCompareDifferentMinorVersionsMajorNeg() {
    assertEquals(0, new AirbyteVersion("-6.8.0-alpha").compareTo(new AirbyteVersion("-6.7.8-alpha")));
  }

  @Test
  public void testCompareDifferentMinorVersionsBothNeg() {
    assertEquals(0, new AirbyteVersion("-6.-8.0-alpha").compareTo(new AirbyteVersion("-6.-7.8-alpha")));
  }

  @Test
  public void testCompareDifferentVersions() {
    assertEquals(-6, new AirbyteVersion("0.8.0-alpha").compareTo(new AirbyteVersion("6.7.8-alpha")));
  }

  @Test
  public void testCompareDifferentVersionsMinorNeg() {
    assertEquals(-6, new AirbyteVersion("0.8.0-alpha").compareTo(new AirbyteVersion("6.-8.8-alpha")));
  }

  @Test
  public void testCompareDifferentVersionsNeg() {
    assertEquals(1, new AirbyteVersion("0.8.0-alpha").compareTo(new AirbyteVersion("-6.8.8-alpha")));
  }

  @Test
  public void testCompareDifferentVersionsBothNeg() {
    assertEquals(0, new AirbyteVersion("-2.-8.0-alpha").compareTo(new AirbyteVersion("-6.-8.8-alpha")));
  }

  @Test
  public void testCompareDifferentMajorMinorVersionsMinorNeg() {
    assertEquals(-6, new AirbyteVersion("0.-8.0-alpha").compareTo(new AirbyteVersion("6.-7.8-alpha")));
  }

  @Test
  public void testCompareDifferentMajorMinorVersionsMajorNeg() {
    assertEquals(-1, new AirbyteVersion("-3.8.0-alpha").compareTo(new AirbyteVersion("6.7.8-alpha")));
  }

  @Test
  public void testCompareDifferentMajorMinorVersionsBothNeg() {
    assertEquals(0, new AirbyteVersion("-3.-8.0-alpha").compareTo(new AirbyteVersion("-6.-7.8-alpha")));
  }

  @Test
  public void testCompareEqualVersions() {
    assertEquals(0, new AirbyteVersion("6.8.0-alpha").compareTo(new AirbyteVersion("6.8.0-alpha")));
  }

  @Test
  public void testCompareEqualVersionsMajorNeg() {
    assertEquals(0, new AirbyteVersion("-6.8.0-alpha").compareTo(new AirbyteVersion("-6.8.0-alpha")));
  }

  @Test
  public void testCompareEqualVersionsMinorNeg() {
    assertEquals(0, new AirbyteVersion("6.-8.0-alpha").compareTo(new AirbyteVersion("6.-8.0-alpha")));
  }

  @Test
  public void testCompareEqualVersionsBothNeg() {
    assertEquals(0, new AirbyteVersion("-6.-8.0-alpha").compareTo(new AirbyteVersion("-6.-8.0-alpha")));
  }

  @Test
  public void testCompareDifferentVersionPrecisions() {
    assertEquals(1, new AirbyteVersion("6.8").compareTo(new AirbyteVersion("6.7.8-alpha")));
  }

  @Test
  public void testCompareSameVersionsDifferentPrecisions() {
    assertEquals(0, new AirbyteVersion("6.8").compareTo(new AirbyteVersion("6.8.3-alpha")));
  }

  @Test
  public void testCompareDevVersion() {
    assertEquals(0, new AirbyteVersion("1.2.3-prod").compareTo(new AirbyteVersion("dev")));
    assertEquals(0, new AirbyteVersion("dev").compareTo(new AirbyteVersion("1.2.3-prod")));
  }

  @Test
  public void testCheckVersion() {
    assertThrows(IllegalStateException.class, () -> AirbyteVersion.check("1.2.3", "3.2.1"));
  }

}
