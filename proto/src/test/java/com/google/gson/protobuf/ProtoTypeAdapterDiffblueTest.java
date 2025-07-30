package com.google.gson.protobuf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.base.CaseFormat;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.protobuf.ProtoTypeAdapter.Builder;
import com.google.gson.protobuf.ProtoTypeAdapter.EnumSerialization;
import com.google.gson.protobuf.generated.Bag.OuterMessage;
import com.google.protobuf.DescriptorProtos.EnumValueOptions;
import com.google.protobuf.DescriptorProtos.FieldOptions;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.google.protobuf.Message;
import java.lang.reflect.Type;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.internal.util.reflection.GenericMetadataSupport.TypeVarBoundedType;

public class ProtoTypeAdapterDiffblueTest {
  /**
   * Test Builder {@link Builder#addSerializedEnumValueExtension(Extension)}.
   *
   * <ul>
   *   <li>Then return newBuilder.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addSerializedEnumValueExtension(Extension)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addSerializedEnumValueExtension(Extension)"})
  public void testBuilderAddSerializedEnumValueExtension_thenReturnNewBuilder() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();
    Class<Object> singularType = Object.class;
    GeneratedExtension<EnumValueOptions, String> serializedEnumValueExtension =
        GeneratedMessage.newFileScopedGeneratedExtension(
            singularType, OuterMessage.getDefaultInstance());

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.addSerializedEnumValueExtension(serializedEnumValueExtension));
  }

  /**
   * Test Builder {@link Builder#addSerializedNameExtension(Extension)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return newBuilder.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addSerializedNameExtension(Extension)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addSerializedNameExtension(Extension)"})
  public void testBuilderAddSerializedNameExtension_whenJavaLangObject_thenReturnNewBuilder() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();
    Class<Object> singularType = Object.class;
    GeneratedExtension<FieldOptions, String> serializedNameExtension =
        GeneratedMessage.newFileScopedGeneratedExtension(
            singularType, OuterMessage.getDefaultInstance());

    // Act and Assert
    assertSame(
        newBuilderResult, newBuilderResult.addSerializedNameExtension(serializedNameExtension));
  }

  /**
   * Test Builder {@link Builder#setFieldNameSerializationFormat(CaseFormat, CaseFormat)}.
   *
   * <p>Method under test: {@link Builder#setFieldNameSerializationFormat(CaseFormat, CaseFormat)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.setFieldNameSerializationFormat(CaseFormat, CaseFormat)"})
  public void testBuilderSetFieldNameSerializationFormat() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.setFieldNameSerializationFormat(
            CaseFormat.LOWER_HYPHEN, CaseFormat.LOWER_HYPHEN));
  }

  /**
   * Test {@link ProtoTypeAdapter#serialize(Message, Type, JsonSerializationContext)} with {@code
   * Message}, {@code Type}, {@code JsonSerializationContext}.
   *
   * <p>Method under test: {@link ProtoTypeAdapter#serialize(Message, Type,
   * JsonSerializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonElement ProtoTypeAdapter.serialize(Message, Type, JsonSerializationContext)"
  })
  public void testSerializeWithMessageTypeJsonSerializationContext() {
    // Arrange
    ProtoTypeAdapter buildResult =
        ProtoTypeAdapter.newBuilder()
            .setEnumSerialization(EnumSerialization.NUMBER)
            .setShouldUseJsonNameFieldOption(true)
            .build();
    OuterMessage src = OuterMessage.getDefaultInstance();

    // Act
    JsonElement actualSerializeResult =
        buildResult.serialize(
            src, new TypeVarBoundedType(null), mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertTrue(actualSerializeResult.isJsonObject());
    assertTrue(((JsonObject) actualSerializeResult).isEmpty());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }

  /**
   * Test {@link ProtoTypeAdapter#serialize(Message, Type, JsonSerializationContext)} with {@code
   * Message}, {@code Type}, {@code JsonSerializationContext}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link ProtoTypeAdapter#serialize(Message, Type,
   * JsonSerializationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JsonElement ProtoTypeAdapter.serialize(Message, Type, JsonSerializationContext)"
  })
  public void testSerializeWithMessageTypeJsonSerializationContext_givenJavaLangObject() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();
    Class<Object> singularType = Object.class;
    GeneratedExtension<FieldOptions, String> serializedNameExtension =
        GeneratedMessage.newFileScopedGeneratedExtension(
            singularType, OuterMessage.getDefaultInstance());
    newBuilderResult.addSerializedNameExtension(serializedNameExtension);
    ProtoTypeAdapter buildResult =
        newBuilderResult
            .setEnumSerialization(EnumSerialization.NUMBER)
            .setShouldUseJsonNameFieldOption(true)
            .build();
    OuterMessage src = OuterMessage.getDefaultInstance();

    // Act
    JsonElement actualSerializeResult =
        buildResult.serialize(
            src, new TypeVarBoundedType(null), mock(JsonSerializationContext.class));

    // Assert
    assertTrue(actualSerializeResult instanceof JsonObject);
    assertEquals(0, ((JsonObject) actualSerializeResult).size());
    assertFalse(actualSerializeResult.isJsonArray());
    assertFalse(actualSerializeResult.isJsonNull());
    assertFalse(actualSerializeResult.isJsonPrimitive());
    assertTrue(actualSerializeResult.isJsonObject());
    assertTrue(((JsonObject) actualSerializeResult).isEmpty());
    assertSame(actualSerializeResult, actualSerializeResult.getAsJsonObject());
  }
}
