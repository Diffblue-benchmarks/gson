package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumberTypeAdapterDiffblueTest {
  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then return create {@link Gson#Gson()} and {@link TypeToken} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenReturnCreateGsonAndTypeTokenIsNull() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    org.mockito.Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult = actualFactory.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertNull(actualCreateResult);
  }

  /**
   * Test {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}.
   *
   * <ul>
   *   <li>Then throw {@link JsonSyntaxException}.
   * </ul>
   *
   * <p>Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"TypeAdapterFactory NumberTypeAdapter.getFactory(ToNumberStrategy)"})
  public void testGetFactory_thenThrowJsonSyntaxException() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    org.mockito.Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new JsonSyntaxException("Msg"));

    // Assert
    assertThrows(JsonSyntaxException.class, () -> actualFactory.create(gson, typeToken));
    verify(typeToken).getRawType();
  }
}
