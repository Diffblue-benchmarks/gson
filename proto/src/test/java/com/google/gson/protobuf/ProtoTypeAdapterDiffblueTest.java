package com.google.gson.protobuf;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.base.CaseFormat;
import com.google.gson.protobuf.ProtoTypeAdapter.Builder;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
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
    assertSame(newBuilderResult, actualSetFieldNameSerializationFormatResult);
  }
}
