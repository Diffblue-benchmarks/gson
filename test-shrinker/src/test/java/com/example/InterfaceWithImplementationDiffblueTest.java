package com.example;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class InterfaceWithImplementationDiffblueTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link
   *       InterfaceWithImplementation.Implementation}
   *   <li>{@link InterfaceWithImplementation.Implementation#getValue()}
   * </ul>
   */
  @Test
  public void testImplementationGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new InterfaceWithImplementation.Implementation()).getValue());
  }
}
