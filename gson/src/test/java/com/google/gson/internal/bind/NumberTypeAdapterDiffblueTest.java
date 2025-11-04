package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.mockito.Mockito;

public class NumberTypeAdapterDiffblueTest {
  /** Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)} */
  @Test
  public void testGetFactory() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    TypeAdapter<Object> actualCreateResult = actualFactory.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    assertNull(actualCreateResult);
  }

  /** Method under test: {@link NumberTypeAdapter#getFactory(ToNumberStrategy)} */
  @Test
  public void testGetFactory2() {
    // Arrange and Act
    TypeAdapterFactory actualFactory = NumberTypeAdapter.getFactory(mock(ToNumberStrategy.class));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Mockito.<Class<? super Object>>when(typeToken.getRawType())
        .thenThrow(new JsonSyntaxException("Msg"));

    // Assert
    assertThrows(JsonSyntaxException.class, () -> actualFactory.create(gson, typeToken));
    verify(typeToken).getRawType();
  }
}
