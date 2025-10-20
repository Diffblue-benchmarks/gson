package com.google.gson.internal;

import java.util.Map;
import java.util.Set;

/**
 * Factory class to help Diffblue Cover construct instances of LinkedTreeMap.EntrySet. This factory
 * provides a package-visible method to create EntrySet instances for testing.
 */
public class LinkedTreeMapEntrySetDiffblueTestFactory {

  /**
   * Creates an EntrySet instance from a LinkedTreeMap. This method allows Diffblue Cover to
   * construct EntrySet instances for testing.
   *
   * @return a Set view of the entries in the LinkedTreeMap
   */
  public static Set<Map.Entry<String, Object>> createEntrySet() {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    return map.entrySet();
  }

  /**
   * Creates an EntrySet instance with pre-populated data.
   *
   * @param key the key to add to the map
   * @param value the value to add to the map
   * @return a Set view of the entries in the LinkedTreeMap
   */
  public static Set<Map.Entry<String, Object>> createEntrySetWithData(String key, Object value) {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    map.put(key, value);
    return map.entrySet();
  }

  /**
   * Creates an EntrySet instance from an empty LinkedTreeMap.
   *
   * @return an empty Set view of the entries
   */
  public static Set<Map.Entry<String, Object>> createEmptyEntrySet() {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    return map.entrySet();
  }

  /**
   * Creates a LinkedTreeMap instance that can be used to access its EntrySet.
   *
   * @return a new LinkedTreeMap instance
   */
  public static LinkedTreeMap<String, Object> createLinkedTreeMap() {
    return new LinkedTreeMap<>();
  }

  /**
   * Creates a LinkedTreeMap instance with allowNullValues setting.
   *
   * @param allowNullValues whether to allow null values
   * @return a new LinkedTreeMap instance
   */
  public static LinkedTreeMap<String, Object> createLinkedTreeMapWithNullValuesSetting(
      boolean allowNullValues) {
    return new LinkedTreeMap<>(allowNullValues);
  }
}
