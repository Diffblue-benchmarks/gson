package com.google.gson.internal;

import java.util.Set;

/**
 * Factory class to help Diffblue Cover construct instances of LinkedTreeMap.KeySet. This factory
 * provides a package-visible method to create KeySet instances for testing.
 */
public class LinkedTreeMapKeySetDiffblueTestFactory {

  /**
   * Creates a KeySet instance from a LinkedTreeMap. This method allows Diffblue Cover to construct
   * KeySet instances for testing.
   *
   * @return a Set view of the keys in the LinkedTreeMap
   */
  public static Set<String> createKeySet() {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    return map.keySet();
  }

  /**
   * Creates a KeySet instance with pre-populated data.
   *
   * @param key the key to add to the map
   * @param value the value to add to the map
   * @return a Set view of the keys in the LinkedTreeMap
   */
  public static Set<String> createKeySetWithData(String key, Object value) {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    map.put(key, value);
    return map.keySet();
  }

  /**
   * Creates a KeySet instance from an empty LinkedTreeMap.
   *
   * @return an empty Set view of the keys
   */
  public static Set<String> createEmptyKeySet() {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    return map.keySet();
  }

  /**
   * Creates a LinkedTreeMap instance that can be used to access its KeySet.
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

  /**
   * Creates a KeySet instance with multiple entries.
   *
   * @return a Set view of the keys in a LinkedTreeMap with multiple entries
   */
  public static Set<String> createKeySetWithMultipleEntries() {
    LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    return map.keySet();
  }
}
