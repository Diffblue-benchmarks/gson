package com.example;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.example.InterfaceWithImplementation.Implementation;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class InterfaceWithImplementationDiffblueTest {
  /**
   * Test Implementation getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Implementation}
   *   <li>{@link Implementation#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Implementation.<init>()", "java.lang.String Implementation.getValue()"})
  public void testImplementationGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new Implementation().getValue());
  }
}
