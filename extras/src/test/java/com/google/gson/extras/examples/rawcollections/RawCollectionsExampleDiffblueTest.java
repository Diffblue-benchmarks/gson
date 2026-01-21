package com.google.gson.extras.examples.rawcollections;

import static org.junit.Assert.assertEquals;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.extras.examples.rawcollections.RawCollectionsExample.Event;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RawCollectionsExampleDiffblueTest {
  /**
   * Test Event getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Event#Event(String, String)}
   *   <li>{@link Event#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Event.<init>(String, String)", "String Event.toString()"})
  public void testEventGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("(name=Name, source=Source)", new Event("Name", "Source").toString());
  }
}
