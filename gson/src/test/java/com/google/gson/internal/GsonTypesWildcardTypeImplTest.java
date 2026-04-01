/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import java.lang.reflect.WildcardType;
import org.junit.Test;

public final class GsonTypesWildcardTypeImplTest {

  @Test
  public void testSubtypeOfGetUpperBounds() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);

    assertThat(wildcard.getUpperBounds()).asList().containsExactly(String.class);
  }

  @Test
  public void testSubtypeOfGetLowerBoundsEmpty() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);

    assertThat(wildcard.getLowerBounds()).asList().isEmpty();
  }

  @Test
  public void testSubtypeOfObjectToString() {
    WildcardType wildcard = GsonTypes.subtypeOf(Object.class);

    assertThat(wildcard.toString()).isEqualTo("?");
  }

  @Test
  public void testSubtypeOfStringToString() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);

    assertThat(wildcard.toString()).isEqualTo("? extends java.lang.String");
  }

  @Test
  public void testSubtypeOfEqualsReflexive() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);

    assertThat(wildcard).isEqualTo(wildcard);
  }

  @Test
  public void testSubtypeOfEqualsSameType() {
    WildcardType a = GsonTypes.subtypeOf(String.class);
    WildcardType b = GsonTypes.subtypeOf(String.class);

    assertThat(a).isEqualTo(b);
  }

  @Test
  public void testSubtypeOfNotEqualsNull() {
    WildcardType wildcard = GsonTypes.subtypeOf(String.class);

    assertThat(wildcard.equals(null)).isFalse();
  }

  @Test
  public void testSubtypeOfNotEqualsDifferentType() {
    WildcardType a = GsonTypes.subtypeOf(String.class);
    WildcardType b = GsonTypes.subtypeOf(Integer.class);

    assertThat(a).isNotEqualTo(b);
  }

  @Test
  public void testSubtypeOfHashCodeConsistent() {
    WildcardType a = GsonTypes.subtypeOf(String.class);
    WildcardType b = GsonTypes.subtypeOf(String.class);

    assertThat(a.hashCode()).isEqualTo(b.hashCode());
  }

  @Test
  public void testSupertypeOfGetLowerBounds() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);

    assertThat(wildcard.getLowerBounds()).asList().containsExactly(String.class);
  }

  @Test
  public void testSupertypeOfGetUpperBoundsIsObject() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);

    assertThat(wildcard.getUpperBounds()).asList().containsExactly(Object.class);
  }

  @Test
  public void testSupertypeOfToString() {
    WildcardType wildcard = GsonTypes.supertypeOf(String.class);

    assertThat(wildcard.toString()).isEqualTo("? super java.lang.String");
  }

  @Test
  public void testSupertypeOfEqualsSameType() {
    WildcardType a = GsonTypes.supertypeOf(String.class);
    WildcardType b = GsonTypes.supertypeOf(String.class);

    assertThat(a).isEqualTo(b);
  }

  @Test
  public void testSupertypeOfHashCodeConsistent() {
    WildcardType a = GsonTypes.supertypeOf(String.class);
    WildcardType b = GsonTypes.supertypeOf(String.class);

    assertThat(a.hashCode()).isEqualTo(b.hashCode());
  }

  @Test
  public void testSupertypeOfNotEqualsSubtypeOf() {
    WildcardType supertype = GsonTypes.supertypeOf(String.class);
    WildcardType subtype = GsonTypes.subtypeOf(String.class);

    assertThat(supertype).isNotEqualTo(subtype);
  }
}
