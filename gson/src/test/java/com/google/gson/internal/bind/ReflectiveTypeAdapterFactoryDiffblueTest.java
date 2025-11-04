package com.google.gson.internal.bind;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ReflectiveTypeAdapterFactoryDiffblueTest {
  /**
   * Test {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}.
   *
   * <ul>
   *   <li>Given {@link JsonIOException#JsonIOException(String)} with {@code Msg}.
   *   <li>Then throw {@link JsonIOException}.
   * </ul>
   *
   * <p>Method under test: {@link ReflectiveTypeAdapterFactory#create(Gson, TypeToken)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({
    "com.google.gson.TypeAdapter ReflectiveTypeAdapterFactory.create(Gson, TypeToken)"
  })
  public void testCreate_givenJsonIOExceptionWithMsg_thenThrowJsonIOException() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    ConstructorConstructor constructorConstructor =
        new ConstructorConstructor(instanceCreators, true, new ArrayList<>());

    FieldNamingStrategy fieldNamingPolicy = mock(FieldNamingStrategy.class);
    HashMap<Type, InstanceCreator<?>> instanceCreators2 = new HashMap<>();
    JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory =
        new JsonAdapterAnnotationTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators2, true, new ArrayList<>()));
    ReflectiveTypeAdapterFactory reflectiveTypeAdapterFactory =
        new ReflectiveTypeAdapterFactory(
            constructorConstructor,
            fieldNamingPolicy,
            Excluder.DEFAULT,
            jsonAdapterFactory,
            new ArrayList<>());
    Gson gson = new Gson();
    TypeToken<Object> type = mock(TypeToken.class);
    when(type.getType()).thenThrow(new JsonIOException("Msg"));
    Class<Object> forNameResult = Object.class;
    org.mockito.Mockito.<Class<? super Object>>when(type.getRawType()).thenReturn(forNameResult);

    // Act and Assert
    assertThrows(JsonIOException.class, () -> reflectiveTypeAdapterFactory.create(gson, type));
    verify(type).getRawType();
    verify(type).getType();
  }
}
