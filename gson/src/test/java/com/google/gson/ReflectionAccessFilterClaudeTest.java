/*
 * Copyright (C) 2009 Google Inc.
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

package com.google.gson;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

import com.google.gson.ReflectionAccessFilter.FilterResult;
import org.junit.Test;

/**
 * Unit tests for {@link ReflectionAccessFilter.FilterResult}.
 *
 * @author Claude
 */
public class ReflectionAccessFilterClaudeTest {

  // ========== values() Tests ==========

  @Test
  public void testValuesReturnsAllEnumConstants() {
    FilterResult[] values = FilterResult.values();
    assertThat(values).hasLength(4);
  }

  @Test
  public void testValuesContainsAllow() {
    FilterResult[] values = FilterResult.values();
    assertThat(values).asList().contains(FilterResult.ALLOW);
  }

  @Test
  public void testValuesContainsIndecisive() {
    FilterResult[] values = FilterResult.values();
    assertThat(values).asList().contains(FilterResult.INDECISIVE);
  }

  @Test
  public void testValuesContainsBlockInaccessible() {
    FilterResult[] values = FilterResult.values();
    assertThat(values).asList().contains(FilterResult.BLOCK_INACCESSIBLE);
  }

  @Test
  public void testValuesContainsBlockAll() {
    FilterResult[] values = FilterResult.values();
    assertThat(values).asList().contains(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testValuesOrderIsCorrect() {
    FilterResult[] values = FilterResult.values();
    assertThat(values[0]).isEqualTo(FilterResult.ALLOW);
    assertThat(values[1]).isEqualTo(FilterResult.INDECISIVE);
    assertThat(values[2]).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(values[3]).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testValuesReturnsNewArrayEachTime() {
    FilterResult[] values1 = FilterResult.values();
    FilterResult[] values2 = FilterResult.values();
    assertThat(values1).isNotSameInstanceAs(values2);
  }

  @Test
  public void testValuesArrayContentsAreEqual() {
    FilterResult[] values1 = FilterResult.values();
    FilterResult[] values2 = FilterResult.values();
    assertThat(values1).isEqualTo(values2);
  }

  // ========== valueOf() Tests ==========

  @Test
  public void testValueOfAllow() {
    FilterResult result = FilterResult.valueOf("ALLOW");
    assertThat(result).isEqualTo(FilterResult.ALLOW);
  }

  @Test
  public void testValueOfIndecisive() {
    FilterResult result = FilterResult.valueOf("INDECISIVE");
    assertThat(result).isEqualTo(FilterResult.INDECISIVE);
  }

  @Test
  public void testValueOfBlockInaccessible() {
    FilterResult result = FilterResult.valueOf("BLOCK_INACCESSIBLE");
    assertThat(result).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
  }

  @Test
  public void testValueOfBlockAll() {
    FilterResult result = FilterResult.valueOf("BLOCK_ALL");
    assertThat(result).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testValueOfWithInvalidNameThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("INVALID");
    });
  }

  @Test
  public void testValueOfWithNullThrowsException() {
    assertThrows(NullPointerException.class, () -> {
      FilterResult.valueOf(null);
    });
  }

  @Test
  public void testValueOfIsCaseSensitiveLowercase() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("allow");
    });
  }

  @Test
  public void testValueOfIsCaseSensitiveMixedCase() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("Allow");
    });
  }

  @Test
  public void testValueOfWithEmptyStringThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("");
    });
  }

  @Test
  public void testValueOfWithLeadingWhitespaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf(" ALLOW");
    });
  }

  @Test
  public void testValueOfWithTrailingWhitespaceThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("ALLOW ");
    });
  }

  @Test
  public void testValueOfWithUnderscoreVariationThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      FilterResult.valueOf("BLOCK-INACCESSIBLE");
    });
  }

  // ========== Enum Properties Tests ==========

  @Test
  public void testAllowOrdinal() {
    assertThat(FilterResult.ALLOW.ordinal()).isEqualTo(0);
  }

  @Test
  public void testIndecisiveOrdinal() {
    assertThat(FilterResult.INDECISIVE.ordinal()).isEqualTo(1);
  }

  @Test
  public void testBlockInaccessibleOrdinal() {
    assertThat(FilterResult.BLOCK_INACCESSIBLE.ordinal()).isEqualTo(2);
  }

  @Test
  public void testBlockAllOrdinal() {
    assertThat(FilterResult.BLOCK_ALL.ordinal()).isEqualTo(3);
  }

  @Test
  public void testAllowName() {
    assertThat(FilterResult.ALLOW.name()).isEqualTo("ALLOW");
  }

  @Test
  public void testIndecisiveName() {
    assertThat(FilterResult.INDECISIVE.name()).isEqualTo("INDECISIVE");
  }

  @Test
  public void testBlockInaccessibleName() {
    assertThat(FilterResult.BLOCK_INACCESSIBLE.name()).isEqualTo("BLOCK_INACCESSIBLE");
  }

  @Test
  public void testBlockAllName() {
    assertThat(FilterResult.BLOCK_ALL.name()).isEqualTo("BLOCK_ALL");
  }

  // ========== Comparison Tests ==========

  @Test
  public void testSameEnumConstantIsEqual() {
    assertThat(FilterResult.ALLOW).isEqualTo(FilterResult.ALLOW);
    assertThat(FilterResult.INDECISIVE).isEqualTo(FilterResult.INDECISIVE);
    assertThat(FilterResult.BLOCK_INACCESSIBLE).isEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(FilterResult.BLOCK_ALL).isEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testAllowNotEqualToOthers() {
    assertThat(FilterResult.ALLOW).isNotEqualTo(FilterResult.INDECISIVE);
    assertThat(FilterResult.ALLOW).isNotEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(FilterResult.ALLOW).isNotEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testIndecisiveNotEqualToOthers() {
    assertThat(FilterResult.INDECISIVE).isNotEqualTo(FilterResult.ALLOW);
    assertThat(FilterResult.INDECISIVE).isNotEqualTo(FilterResult.BLOCK_INACCESSIBLE);
    assertThat(FilterResult.INDECISIVE).isNotEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testBlockInaccessibleNotEqualToOthers() {
    assertThat(FilterResult.BLOCK_INACCESSIBLE).isNotEqualTo(FilterResult.ALLOW);
    assertThat(FilterResult.BLOCK_INACCESSIBLE).isNotEqualTo(FilterResult.INDECISIVE);
    assertThat(FilterResult.BLOCK_INACCESSIBLE).isNotEqualTo(FilterResult.BLOCK_ALL);
  }

  @Test
  public void testBlockAllNotEqualToOthers() {
    assertThat(FilterResult.BLOCK_ALL).isNotEqualTo(FilterResult.ALLOW);
    assertThat(FilterResult.BLOCK_ALL).isNotEqualTo(FilterResult.INDECISIVE);
    assertThat(FilterResult.BLOCK_ALL).isNotEqualTo(FilterResult.BLOCK_INACCESSIBLE);
  }

  // ========== toString() Tests ==========

  @Test
  public void testAllowToString() {
    assertThat(FilterResult.ALLOW.toString()).isEqualTo("ALLOW");
  }

  @Test
  public void testIndecisiveToString() {
    assertThat(FilterResult.INDECISIVE.toString()).isEqualTo("INDECISIVE");
  }

  @Test
  public void testBlockInaccessibleToString() {
    assertThat(FilterResult.BLOCK_INACCESSIBLE.toString()).isEqualTo("BLOCK_INACCESSIBLE");
  }

  @Test
  public void testBlockAllToString() {
    assertThat(FilterResult.BLOCK_ALL.toString()).isEqualTo("BLOCK_ALL");
  }

  // ========== valueOf and name() Round Trip Tests ==========

  @Test
  public void testValueOfNameRoundTrip() {
    for (FilterResult result : FilterResult.values()) {
      assertThat(FilterResult.valueOf(result.name())).isEqualTo(result);
    }
  }
}
