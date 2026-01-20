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
import com.google.gson.protobuf.ProtoTypeAdapter.Builder;
import com.google.gson.protobuf.ProtoTypeAdapter.EnumSerialization;
import com.google.protobuf.DescriptorProtos.EnumValueOptions;
import com.google.protobuf.DescriptorProtos.FieldOptions;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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

    // Act
    Builder actualAddSerializedEnumValueExtensionResult =
        newBuilderResult.addSerializedEnumValueExtension(mock(GeneratedExtension.class));

    // Assert
    assertSame(newBuilderResult, actualAddSerializedEnumValueExtensionResult);
  }

  /**
   * Test Builder {@link Builder#addSerializedNameExtension(Extension)}.
   *
   * <ul>
   *   <li>Then return newBuilder.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addSerializedNameExtension(Extension)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addSerializedNameExtension(Extension)"})
  public void testBuilderAddSerializedNameExtension_thenReturnNewBuilder() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act
    Builder actualAddSerializedNameExtensionResult =
        newBuilderResult.addSerializedNameExtension(mock(GeneratedExtension.class));

    // Assert
    assertSame(newBuilderResult, actualAddSerializedNameExtensionResult);
  }

  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#setEnumSerialization(EnumSerialization)}
   *   <li>{@link Builder#setShouldUseJsonNameFieldOption(boolean)}
   *   <li>{@link Builder#getJsonFormat()}
   *   <li>{@link Builder#getProtoFormat()}
   *   <li>{@link Builder#getEnumSerialization()}
   *   <li>{@link Builder#isShouldUseJsonNameFieldOption()}
   *   <li>{@link Builder#getSerializedEnumValueExtensions()}
   *   <li>{@link Builder#getSerializedNameExtensions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ProtoTypeAdapter Builder.build()",
    "EnumSerialization Builder.getEnumSerialization()",
    "CaseFormat Builder.getJsonFormat()",
    "CaseFormat Builder.getProtoFormat()",
    "Set Builder.getSerializedEnumValueExtensions()",
    "Set Builder.getSerializedNameExtensions()",
    "boolean Builder.isShouldUseJsonNameFieldOption()",
    "Builder Builder.setEnumSerialization(EnumSerialization)",
    "Builder Builder.setShouldUseJsonNameFieldOption(boolean)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    Builder actualNewBuilderResult = ProtoTypeAdapter.newBuilder();
    Builder actualSetEnumSerializationResult =
        actualNewBuilderResult.setEnumSerialization(EnumSerialization.NUMBER);
    Builder actualSetShouldUseJsonNameFieldOptionResult =
        actualSetEnumSerializationResult.setShouldUseJsonNameFieldOption(true);
    ProtoTypeAdapter actualProtoTypeAdapter = actualSetShouldUseJsonNameFieldOptionResult.build();

    // Assert
    assertEquals(CaseFormat.LOWER_CAMEL, actualProtoTypeAdapter.getJsonFormat());
    assertEquals(CaseFormat.LOWER_CAMEL, actualSetEnumSerializationResult.getJsonFormat());
    assertEquals(
        CaseFormat.LOWER_CAMEL, actualSetShouldUseJsonNameFieldOptionResult.getJsonFormat());
    assertEquals(CaseFormat.LOWER_CAMEL, actualNewBuilderResult.getJsonFormat());
    assertEquals(CaseFormat.LOWER_UNDERSCORE, actualProtoTypeAdapter.getProtoFormat());
    assertEquals(CaseFormat.LOWER_UNDERSCORE, actualSetEnumSerializationResult.getProtoFormat());
    assertEquals(
        CaseFormat.LOWER_UNDERSCORE, actualSetShouldUseJsonNameFieldOptionResult.getProtoFormat());
    assertEquals(CaseFormat.LOWER_UNDERSCORE, actualNewBuilderResult.getProtoFormat());
    assertEquals(EnumSerialization.NUMBER, actualProtoTypeAdapter.getEnumSerialization());
    assertEquals(EnumSerialization.NUMBER, actualSetEnumSerializationResult.getEnumSerialization());
    assertEquals(
        EnumSerialization.NUMBER,
        actualSetShouldUseJsonNameFieldOptionResult.getEnumSerialization());
    assertEquals(EnumSerialization.NUMBER, actualNewBuilderResult.getEnumSerialization());
    assertTrue(actualProtoTypeAdapter.isShouldUseJsonNameFieldOption());
    assertTrue(actualSetEnumSerializationResult.isShouldUseJsonNameFieldOption());
    assertTrue(actualSetShouldUseJsonNameFieldOptionResult.isShouldUseJsonNameFieldOption());
    assertTrue(actualNewBuilderResult.isShouldUseJsonNameFieldOption());
    Set<Extension<EnumValueOptions, String>> serializedEnumValueExtensions =
        actualNewBuilderResult.getSerializedEnumValueExtensions();
    assertTrue(serializedEnumValueExtensions.isEmpty());
    Set<Extension<FieldOptions, String>> serializedNameExtensions =
        actualNewBuilderResult.getSerializedNameExtensions();
    assertTrue(serializedNameExtensions.isEmpty());
    assertSame(
        serializedEnumValueExtensions, actualProtoTypeAdapter.getSerializedEnumValueExtensions());
    assertSame(
        serializedEnumValueExtensions,
        actualSetEnumSerializationResult.getSerializedEnumValueExtensions());
    assertSame(
        serializedEnumValueExtensions,
        actualSetShouldUseJsonNameFieldOptionResult.getSerializedEnumValueExtensions());
    assertSame(serializedNameExtensions, actualProtoTypeAdapter.getSerializedNameExtensions());
    assertSame(
        serializedNameExtensions, actualSetEnumSerializationResult.getSerializedNameExtensions());
    assertSame(
        serializedNameExtensions,
        actualSetShouldUseJsonNameFieldOptionResult.getSerializedNameExtensions());
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

    // Act
    Builder actualSetFieldNameSerializationFormatResult =
        newBuilderResult.setFieldNameSerializationFormat(
            CaseFormat.LOWER_HYPHEN, CaseFormat.LOWER_HYPHEN);

    // Assert
    assertEquals(CaseFormat.LOWER_HYPHEN, newBuilderResult.getJsonFormat());
    assertEquals(CaseFormat.LOWER_HYPHEN, newBuilderResult.getProtoFormat());
    assertSame(newBuilderResult, actualSetFieldNameSerializationFormatResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ProtoTypeAdapter#newBuilder()}
   *   <li>{@link ProtoTypeAdapter#getEnumSerialization()}
   *   <li>{@link ProtoTypeAdapter#getJsonFormat()}
   *   <li>{@link ProtoTypeAdapter#getProtoFormat()}
   *   <li>{@link ProtoTypeAdapter#getSerializedEnumValueExtensions()}
   *   <li>{@link ProtoTypeAdapter#getSerializedNameExtensions()}
   *   <li>{@link ProtoTypeAdapter#isShouldUseJsonNameFieldOption()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "EnumSerialization ProtoTypeAdapter.getEnumSerialization()",
    "CaseFormat ProtoTypeAdapter.getJsonFormat()",
    "CaseFormat ProtoTypeAdapter.getProtoFormat()",
    "Set ProtoTypeAdapter.getSerializedEnumValueExtensions()",
    "Set ProtoTypeAdapter.getSerializedNameExtensions()",
    "boolean ProtoTypeAdapter.isShouldUseJsonNameFieldOption()",
    "Builder ProtoTypeAdapter.newBuilder()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ProtoTypeAdapter protoTypeAdapter =
        ProtoTypeAdapter.newBuilder()
            .setEnumSerialization(EnumSerialization.NUMBER)
            .setShouldUseJsonNameFieldOption(true)
            .build();

    // Act
    Builder actualNewBuilderResult = protoTypeAdapter.newBuilder();
    EnumSerialization actualEnumSerialization = protoTypeAdapter.getEnumSerialization();
    CaseFormat actualJsonFormat = protoTypeAdapter.getJsonFormat();
    CaseFormat actualProtoFormat = protoTypeAdapter.getProtoFormat();
    Set<Extension<EnumValueOptions, String>> actualSerializedEnumValueExtensions =
        protoTypeAdapter.getSerializedEnumValueExtensions();
    Set<Extension<FieldOptions, String>> actualSerializedNameExtensions =
        protoTypeAdapter.getSerializedNameExtensions();
    boolean actualIsShouldUseJsonNameFieldOptionResult =
        protoTypeAdapter.isShouldUseJsonNameFieldOption();

    // Assert
    assertEquals(CaseFormat.LOWER_CAMEL, actualJsonFormat);
    assertEquals(CaseFormat.LOWER_CAMEL, actualNewBuilderResult.getJsonFormat());
    assertEquals(CaseFormat.LOWER_UNDERSCORE, actualProtoFormat);
    assertEquals(CaseFormat.LOWER_UNDERSCORE, actualNewBuilderResult.getProtoFormat());
    assertEquals(EnumSerialization.NAME, actualNewBuilderResult.getEnumSerialization());
    assertEquals(EnumSerialization.NUMBER, actualEnumSerialization);
    assertFalse(actualNewBuilderResult.isShouldUseJsonNameFieldOption());
    assertTrue(actualIsShouldUseJsonNameFieldOptionResult);
    assertTrue(actualSerializedEnumValueExtensions.isEmpty());
    assertTrue(actualSerializedNameExtensions.isEmpty());
    assertTrue(actualNewBuilderResult.getSerializedEnumValueExtensions().isEmpty());
    assertTrue(actualNewBuilderResult.getSerializedNameExtensions().isEmpty());
  }
}
