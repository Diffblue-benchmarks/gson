package com.google.gson.protobuf;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.MaintainedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.common.base.CaseFormat;
import com.google.gson.protobuf.ProtoTypeAdapter.Builder;
import com.google.protobuf.Extension;
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Builder Builder.addSerializedEnumValueExtension(Extension)"})
  public void testBuilderAddSerializedEnumValueExtension_thenReturnNewBuilder() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.addSerializedEnumValueExtension(mock(GeneratedExtension.class)));
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
  @Category(MaintainedByDiffblue.class)
  @MethodsUnderTest({"Builder Builder.addSerializedNameExtension(Extension)"})
  public void testBuilderAddSerializedNameExtension_thenReturnNewBuilder() {
    // Arrange
    Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.addSerializedNameExtension(mock(GeneratedExtension.class)));
  }

  /**
   * Test Builder {@link Builder#setFieldNameSerializationFormat(CaseFormat, CaseFormat)}.
   *
   * <p>Method under test: {@link Builder#setFieldNameSerializationFormat(CaseFormat, CaseFormat)}
   */
  @Test
  @Category(MaintainedByDiffblue.class)
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
}
