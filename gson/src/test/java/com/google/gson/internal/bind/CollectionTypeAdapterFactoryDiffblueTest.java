package com.google.gson.internal.bind;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.GenericMetadataSupport;

public class CollectionTypeAdapterFactoryDiffblueTest {
  /** Method under test: {@link CollectionTypeAdapterFactory#create(Gson, TypeToken)} */
  @Test
  public void testCreate() {
    // Arrange
    HashMap<Type, InstanceCreator<?>> instanceCreators = new HashMap<>();
    CollectionTypeAdapterFactory collectionTypeAdapterFactory =
        new CollectionTypeAdapterFactory(
            new ConstructorConstructor(instanceCreators, true, new ArrayList<>()));
    Gson gson = new Gson();
    TypeToken<Object> typeToken = mock(TypeToken.class);
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<? super Object>>when(typeToken.getRawType()).thenReturn(forNameResult);
    when(typeToken.getType()).thenReturn(new GenericMetadataSupport.TypeVarBoundedType(null));

    // Act
    TypeAdapter<Object> actualCreateResult = collectionTypeAdapterFactory.create(gson, typeToken);

    // Assert
    verify(typeToken).getRawType();
    verify(typeToken).getType();
    assertNull(actualCreateResult);
  }
}
