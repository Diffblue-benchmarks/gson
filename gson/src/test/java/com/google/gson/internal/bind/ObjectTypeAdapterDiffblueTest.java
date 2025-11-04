package com.google.gson.internal.bind;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ObjectTypeAdapterDiffblueTest {
  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then create {@link Gson#Gson()} and {@link TypeToken} return {@link ObjectTypeAdapter}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenCreateGsonAndTypeTokenReturnObjectTypeAdapter() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    org.mockito.Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult = actualFactory.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertTrue(actualCreateResult instanceof ObjectTypeAdapter);
  }

  /**
   * Test {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapterFactory ObjectTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenThrowIllegalStateException() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = ObjectTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    org.mockito.Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new IllegalStateException("foo"));

    // Assert
    assertThrows(IllegalStateException.class, () -> actualFactory.create(gson, typeToken));
    verify(typeToken).getRawType();
  }
}
