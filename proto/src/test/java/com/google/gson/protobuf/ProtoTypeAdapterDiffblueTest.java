package com.google.gson.protobuf;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

import com.google.common.base.CaseFormat;
import com.google.protobuf.Extension;
import com.google.protobuf.GeneratedMessage;
import org.junit.Test;

public class ProtoTypeAdapterDiffblueTest {
  /**
   * Method under test: {@link ProtoTypeAdapter.Builder#addSerializedEnumValueExtension(Extension)}
   */
  @Test
  public void testBuilderAddSerializedEnumValueExtension() {
    // Arrange
    ProtoTypeAdapter.Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.addSerializedEnumValueExtension(
            mock(GeneratedMessage.GeneratedExtension.class)));
  }

  /** Method under test: {@link ProtoTypeAdapter.Builder#addSerializedNameExtension(Extension)} */
  @Test
  public void testBuilderAddSerializedNameExtension() {
    // Arrange
    ProtoTypeAdapter.Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.addSerializedNameExtension(
            mock(GeneratedMessage.GeneratedExtension.class)));
  }

  /**
   * Method under test: {@link ProtoTypeAdapter.Builder#setFieldNameSerializationFormat(CaseFormat,
   * CaseFormat)}
   */
  @Test
  public void testBuilderSetFieldNameSerializationFormat() {
    // Arrange
    ProtoTypeAdapter.Builder newBuilderResult = ProtoTypeAdapter.newBuilder();

    // Act and Assert
    assertSame(
        newBuilderResult,
        newBuilderResult.setFieldNameSerializationFormat(
            CaseFormat.LOWER_HYPHEN, CaseFormat.LOWER_HYPHEN));
  }
}
