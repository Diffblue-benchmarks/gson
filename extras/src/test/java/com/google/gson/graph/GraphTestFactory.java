/*
 * Copyright (C) 2024 Google Inc.
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
package com.google.gson.graph;

import com.diffblue.cover.annotations.InterestingTestFactory;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/** Test factory for creating Graph instances for Cover test generation. */
public class GraphTestFactory {

  /**
   * Factory method to create a Graph instance with a valid map. This prevents NullPointerException
   * when calling nextName().
   */
  @InterestingTestFactory
  public static GraphAdapterBuilder.Graph createGraph() {
    try {
      // Get the Graph constructor that takes a Map parameter
      Constructor<GraphAdapterBuilder.Graph> constructor =
          GraphAdapterBuilder.Graph.class.getDeclaredConstructor(Map.class);
      constructor.setAccessible(true);

      // Create a valid empty map for the Graph
      Map<Object, GraphAdapterBuilder.Element<?>> map = new HashMap<>();

      return constructor.newInstance(map);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create Graph instance", e);
    }
  }
}
