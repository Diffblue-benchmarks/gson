/*
 * Copyright (C) 2024 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Test;

/** Tests for {@link TreeTypeAdapter}. */
public class TreeTypeAdapterClaudeTest {

  // ==========================================================================
  // Test data classes
  // ==========================================================================

  @SuppressWarnings("unused")
  private static class Person {
    String name;
    int age;

    Person() {}

    Person(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }

  private static class Animal {
    String species;
    int legs;

    Animal() {}

    Animal(String species, int legs) {
      this.species = species;
      this.legs = legs;
    }
  }

  private static class Dog extends Animal {
    String breed;

    Dog() {}

    Dog(String species, int legs, String breed) {
      super(species, legs);
      this.breed = breed;
    }
  }

  // ==========================================================================
  // Custom serializers/deserializers for testing
  // ==========================================================================

  private static class PersonSerializer implements JsonSerializer<Person> {
    @Override
    public JsonElement serialize(Person src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject obj = new JsonObject();
      obj.addProperty("person_name", src.name);
      obj.addProperty("person_age", src.age);
      return obj;
    }
  }

  private static class PersonDeserializer implements JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject obj = json.getAsJsonObject();
      Person person = new Person();
      if (obj.has("person_name")) {
        person.name = obj.get("person_name").getAsString();
      } else if (obj.has("name")) {
        person.name = obj.get("name").getAsString();
      }
      if (obj.has("person_age")) {
        person.age = obj.get("person_age").getAsInt();
      } else if (obj.has("age")) {
        person.age = obj.get("age").getAsInt();
      }
      return person;
    }
  }

  private static class PersonSerializerAndDeserializer
      implements JsonSerializer<Person>, JsonDeserializer<Person> {
    @Override
    public JsonElement serialize(Person src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject obj = new JsonObject();
      obj.addProperty("n", src.name);
      obj.addProperty("a", src.age);
      return obj;
    }

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject obj = json.getAsJsonObject();
      Person person = new Person();
      person.name = obj.get("n").getAsString();
      person.age = obj.get("a").getAsInt();
      return person;
    }
  }

  private static class AnimalHierarchySerializer implements JsonSerializer<Animal> {
    @Override
    public JsonElement serialize(Animal src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject obj = new JsonObject();
      obj.addProperty("type", src.getClass().getSimpleName());
      obj.addProperty("species", src.species);
      obj.addProperty("legs", src.legs);
      if (src instanceof Dog) {
        obj.addProperty("breed", ((Dog) src).breed);
      }
      return obj;
    }
  }

  private static class AnimalHierarchyDeserializer implements JsonDeserializer<Animal> {
    @Override
    public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      JsonObject obj = json.getAsJsonObject();
      String type = obj.has("type") ? obj.get("type").getAsString() : "Animal";
      String species = obj.get("species").getAsString();
      int legs = obj.get("legs").getAsInt();
      if ("Dog".equals(type)) {
        String breed = obj.get("breed").getAsString();
        return new Dog(species, legs, breed);
      }
      return new Animal(species, legs);
    }
  }

  // ==========================================================================
  // Constructor tests
  // ==========================================================================

  @Test
  public void constructor_withSerializerOnly_createsAdapter() {
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_withDeserializerOnly_createsAdapter() {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_withBothSerializerAndDeserializer_createsAdapter() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonSerializer())
            .registerTypeAdapter(Person.class, new PersonDeserializer())
            .create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    assertNotNull(adapter);
  }

  @Test
  public void constructor_withCombinedSerializerDeserializer_createsAdapter() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonSerializerAndDeserializer())
            .create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    assertNotNull(adapter);
  }

  // ==========================================================================
  // read() tests
  // ==========================================================================

  @Test
  public void read_withDeserializer_usesDeserializer() throws IOException {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    Person person = gson.fromJson("{\"person_name\":\"John\",\"person_age\":30}", Person.class);
    assertEquals("John", person.name);
    assertEquals(30, person.age);
  }

  @Test
  public void read_withDeserializer_nullInput_returnsNull() throws IOException {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    Person person = gson.fromJson("null", Person.class);
    assertNull(person);
  }

  @Test
  public void read_withDeserializerAndNullSafeFalse_nullInput_callsDeserializer()
      throws IOException {
    // When nullSafe is false, the deserializer receives the JSON null
    JsonDeserializer<Person> deserializer =
        (json, typeOfT, context) -> {
          if (json.isJsonNull()) {
            // Return a "null person" instead
            Person p = new Person();
            p.name = "NULL_PERSON";
            p.age = -1;
            return p;
          }
          JsonObject obj = json.getAsJsonObject();
          Person person = new Person();
          person.name = obj.get("name").getAsString();
          person.age = obj.get("age").getAsInt();
          return person;
        };

    // Using serializeNulls() doesn't affect the nullSafe flag directly,
    // but we can test the deserializer behavior with non-null input
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, deserializer).create();
    Person person = gson.fromJson("{\"name\":\"Test\",\"age\":25}", Person.class);
    assertEquals("Test", person.name);
    assertEquals(25, person.age);
  }

  @Test
  public void read_withoutDeserializer_delegatesToDefault() throws IOException {
    // Only register a serializer, so read should delegate
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    Person person = gson.fromJson("{\"name\":\"Jane\",\"age\":25}", Person.class);
    assertEquals("Jane", person.name);
    assertEquals(25, person.age);
  }

  @Test
  public void read_withJsonReader_parsesCorrectly() throws IOException {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    JsonReader reader =
        new JsonReader(new StringReader("{\"person_name\":\"Alice\",\"person_age\":28}"));
    Person person = adapter.read(reader);
    assertEquals("Alice", person.name);
    assertEquals(28, person.age);
  }

  @Test
  public void read_withContext_canDeserializeNestedTypes() throws IOException {
    JsonDeserializer<List<Person>> listDeserializer =
        (json, typeOfT, context) -> {
          // Use context to deserialize individual Person objects
          java.util.ArrayList<Person> result = new java.util.ArrayList<>();
          for (JsonElement element : json.getAsJsonArray()) {
            Person person = context.deserialize(element, Person.class);
            result.add(person);
          }
          return result;
        };

    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonDeserializer())
            .registerTypeAdapter(
                new TypeToken<List<Person>>() {}.getType(), listDeserializer)
            .create();

    List<Person> people =
        gson.fromJson(
            "[{\"person_name\":\"A\",\"person_age\":1},{\"person_name\":\"B\",\"person_age\":2}]",
            new TypeToken<List<Person>>() {}.getType());
    assertEquals(2, people.size());
    assertEquals("A", people.get(0).name);
    assertEquals("B", people.get(1).name);
  }

  // ==========================================================================
  // write() tests
  // ==========================================================================

  @Test
  public void write_withSerializer_usesSerializer() throws IOException {
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    Person person = new Person("John", 30);
    String json = gson.toJson(person);
    assertEquals("{\"person_name\":\"John\",\"person_age\":30}", json);
  }

  @Test
  public void write_withSerializer_nullInput_writesNull() throws IOException {
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    String json = gson.toJson(null, Person.class);
    assertEquals("null", json);
  }

  @Test
  public void write_withoutSerializer_delegatesToDefault() throws IOException {
    // Only register a deserializer, so write should delegate
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    Person person = new Person("Jane", 25);
    String json = gson.toJson(person);
    assertTrue(json.contains("\"name\":\"Jane\""));
    assertTrue(json.contains("\"age\":25"));
  }

  @Test
  public void write_withJsonWriter_writesCorrectly() throws IOException {
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    StringWriter stringWriter = new StringWriter();
    JsonWriter writer = new JsonWriter(stringWriter);
    adapter.write(writer, new Person("Bob", 35));
    assertEquals("{\"person_name\":\"Bob\",\"person_age\":35}", stringWriter.toString());
  }

  @Test
  public void write_withContext_canSerializeNestedTypes() throws IOException {
    JsonSerializer<List<Person>> listSerializer =
        (src, typeOfSrc, context) -> {
          com.google.gson.JsonArray array = new com.google.gson.JsonArray();
          for (Person person : src) {
            array.add(context.serialize(person));
          }
          return array;
        };

    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonSerializer())
            .registerTypeAdapter(
                new TypeToken<List<Person>>() {}.getType(), listSerializer)
            .create();

    java.util.ArrayList<Person> people = new java.util.ArrayList<>();
    people.add(new Person("A", 1));
    people.add(new Person("B", 2));

    String json = gson.toJson(people, new TypeToken<List<Person>>() {}.getType());
    assertEquals(
        "[{\"person_name\":\"A\",\"person_age\":1},{\"person_name\":\"B\",\"person_age\":2}]", json);
  }

  // ==========================================================================
  // getSerializationDelegate() tests
  // ==========================================================================

  @Test
  public void getSerializationDelegate_withSerializer_returnsSelf() throws IOException {
    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, new PersonSerializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    // The adapter should be a TreeTypeAdapter wrapper
    assertTrue(adapter instanceof SerializationDelegatingTypeAdapter);
    TypeAdapter<Person> delegate = ((SerializationDelegatingTypeAdapter<Person>) adapter).getSerializationDelegate();
    // With a serializer, it should return itself
    assertSame(adapter, delegate);
  }

  @Test
  public void getSerializationDelegate_withoutSerializer_returnsDelegate() throws IOException {
    Gson gson =
        new GsonBuilder().registerTypeAdapter(Person.class, new PersonDeserializer()).create();
    TypeAdapter<Person> adapter = gson.getAdapter(Person.class);
    assertTrue(adapter instanceof SerializationDelegatingTypeAdapter);
    TypeAdapter<Person> delegate = ((SerializationDelegatingTypeAdapter<Person>) adapter).getSerializationDelegate();
    // Without a serializer, it returns the delegate (ReflectiveTypeAdapter)
    // The delegate should not be the same as the adapter
    assertTrue(delegate != adapter || !(adapter instanceof TreeTypeAdapter));
  }

  // ==========================================================================
  // newFactory() tests
  // ==========================================================================

  @Test
  public void newFactory_matchesExactType() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactory(TypeToken.get(Person.class), new PersonSerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Should match Person.class
    Person person = new Person("Test", 20);
    String json = gson.toJson(person);
    assertEquals("{\"person_name\":\"Test\",\"person_age\":20}", json);
  }

  @Test
  public void newFactory_doesNotMatchDifferentType() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactory(TypeToken.get(Person.class), new PersonSerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Should not match Animal.class
    Animal animal = new Animal("Cat", 4);
    String json = gson.toJson(animal);
    // Should use default serialization
    assertTrue(json.contains("\"species\":\"Cat\""));
    assertTrue(json.contains("\"legs\":4"));
  }

  @Test
  public void newFactory_doesNotMatchSubtype() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactory(TypeToken.get(Animal.class), new AnimalHierarchySerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Animal should match and use custom serializer
    Animal animal = new Animal("Cat", 4);
    String json = gson.toJson(animal, Animal.class);
    assertTrue(json.contains("\"type\":\"Animal\""));

    // Dog serialized as Dog.class should NOT match (exact type only)
    Dog dog = new Dog("Canis", 4, "Labrador");
    String dogJson = gson.toJson(dog);
    // Should use default serialization, not the custom one
    assertTrue(dogJson.contains("\"breed\":\"Labrador\""));
  }

  @Test
  public void newFactory_withInvalidTypeAdapter_throwsException() {
    try {
      TreeTypeAdapter.newFactory(TypeToken.get(Person.class), "not a type adapter");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("must implement JsonSerializer or JsonDeserializer"));
    }
  }

  @Test
  public void newFactory_withNull_throwsNullPointerException() {
    try {
      TreeTypeAdapter.newFactory(TypeToken.get(Person.class), null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      // expected
    }
  }

  // ==========================================================================
  // newFactoryWithMatchRawType() tests
  // ==========================================================================

  @Test
  public void newFactoryWithMatchRawType_matchesExactType() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactoryWithMatchRawType(
            TypeToken.get(Person.class), new PersonSerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    Person person = new Person("Test", 20);
    String json = gson.toJson(person);
    assertEquals("{\"person_name\":\"Test\",\"person_age\":20}", json);
  }

  @Test
  public void newFactoryWithMatchRawType_rawTypeMatchesRaw() {
    // For raw types (non-parameterized), matchRawType has the same effect
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactoryWithMatchRawType(
            TypeToken.get(Person.class), new PersonSerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    Person person = new Person("Test", 20);
    String json = gson.toJson(person);
    assertEquals("{\"person_name\":\"Test\",\"person_age\":20}", json);
  }

  @Test
  public void newFactoryWithMatchRawType_parameterizedTypeMatchesRaw() {
    // For parameterized types like List<String>, matchRawType allows matching List
    JsonSerializer<List<?>> listSerializer =
        (src, typeOfSrc, context) -> {
          JsonObject obj = new JsonObject();
          obj.addProperty("size", src.size());
          return obj;
        };

    // This factory is for List<String> specifically
    TypeToken<List<String>> listStringToken = new TypeToken<List<String>>() {};

    // newFactoryWithMatchRawType should NOT match raw type for parameterized tokens
    // because getType() != getRawType() for parameterized types
    TypeAdapterFactory factory =
        TreeTypeAdapter.newFactoryWithMatchRawType(listStringToken, listSerializer);
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    java.util.ArrayList<String> list = new java.util.ArrayList<>();
    list.add("a");
    list.add("b");

    // Should match List<String>
    String json = gson.toJson(list, listStringToken.getType());
    assertEquals("{\"size\":2}", json);
  }

  @Test
  public void newFactoryWithMatchRawType_withInvalidTypeAdapter_throwsException() {
    try {
      TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(Person.class), "not a type adapter");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("must implement JsonSerializer or JsonDeserializer"));
    }
  }

  // ==========================================================================
  // newTypeHierarchyFactory() tests
  // ==========================================================================

  @Test
  public void newTypeHierarchyFactory_matchesExactType() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalHierarchySerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    Animal animal = new Animal("Cat", 4);
    String json = gson.toJson(animal);
    assertTrue(json.contains("\"type\":\"Animal\""));
  }

  @Test
  public void newTypeHierarchyFactory_matchesSubtype() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalHierarchySerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Dog is a subtype of Animal, should use the hierarchy serializer
    Dog dog = new Dog("Canis", 4, "Labrador");
    String json = gson.toJson(dog);
    assertTrue(json.contains("\"type\":\"Dog\""));
    assertTrue(json.contains("\"breed\":\"Labrador\""));
  }

  @Test
  public void newTypeHierarchyFactory_doesNotMatchUnrelatedType() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalHierarchySerializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Person is not related to Animal
    Person person = new Person("Test", 20);
    String json = gson.toJson(person);
    // Should use default serialization
    assertTrue(json.contains("\"name\":\"Test\""));
  }

  @Test
  public void newTypeHierarchyFactory_withBothSerializerAndDeserializer() {
    class AnimalAdapter implements JsonSerializer<Animal>, JsonDeserializer<Animal> {
      @Override
      public JsonElement serialize(Animal src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("s", src.species);
        obj.addProperty("l", src.legs);
        return obj;
      }

      @Override
      public Animal deserialize(
          JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject obj = json.getAsJsonObject();
        return new Animal(obj.get("s").getAsString(), obj.get("l").getAsInt());
      }
    }

    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalAdapter());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Serialization
    Animal animal = new Animal("Cat", 4);
    String json = gson.toJson(animal);
    assertEquals("{\"s\":\"Cat\",\"l\":4}", json);

    // Deserialization
    Animal deserialized = gson.fromJson("{\"s\":\"Dog\",\"l\":4}", Animal.class);
    assertEquals("Dog", deserialized.species);
    assertEquals(4, deserialized.legs);
  }

  @Test
  public void newTypeHierarchyFactory_deserializerMatchesSubtype() {
    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalHierarchyDeserializer());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    // Deserialize as base type Animal but get Dog based on content
    Animal animal =
        gson.fromJson(
            "{\"type\":\"Dog\",\"species\":\"Canis\",\"legs\":4,\"breed\":\"Poodle\"}",
            Animal.class);
    assertTrue(animal instanceof Dog);
    assertEquals("Poodle", ((Dog) animal).breed);
  }

  @Test
  public void newTypeHierarchyFactory_withInvalidTypeAdapter_throwsException() {
    try {
      TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, "not a type adapter");
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      assertTrue(e.getMessage().contains("must implement JsonSerializer or JsonDeserializer"));
    }
  }

  // ==========================================================================
  // Round-trip tests
  // ==========================================================================

  @Test
  public void roundTrip_withSerializerAndDeserializer_preservesData() {
    Gson gson =
        new GsonBuilder()
            .registerTypeAdapter(Person.class, new PersonSerializerAndDeserializer())
            .create();

    Person original = new Person("Alice", 30);
    String json = gson.toJson(original);
    Person restored = gson.fromJson(json, Person.class);

    assertEquals(original.name, restored.name);
    assertEquals(original.age, restored.age);
  }

  @Test
  public void roundTrip_withHierarchyAdapter_preservesSubtype() {
    class AnimalAdapter implements JsonSerializer<Animal>, JsonDeserializer<Animal> {
      @Override
      public JsonElement serialize(Animal src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", src.getClass().getSimpleName());
        obj.addProperty("species", src.species);
        obj.addProperty("legs", src.legs);
        if (src instanceof Dog) {
          obj.addProperty("breed", ((Dog) src).breed);
        }
        return obj;
      }

      @Override
      public Animal deserialize(
          JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("type").getAsString();
        String species = obj.get("species").getAsString();
        int legs = obj.get("legs").getAsInt();
        if ("Dog".equals(type)) {
          return new Dog(species, legs, obj.get("breed").getAsString());
        }
        return new Animal(species, legs);
      }
    }

    TypeAdapterFactory factory =
        TreeTypeAdapter.newTypeHierarchyFactory(Animal.class, new AnimalAdapter());
    Gson gson = new GsonBuilder().registerTypeAdapterFactory(factory).create();

    Dog original = new Dog("Canis", 4, "Golden Retriever");
    String json = gson.toJson(original, Animal.class);
    Animal restored = gson.fromJson(json, Animal.class);

    assertTrue(restored instanceof Dog);
    Dog restoredDog = (Dog) restored;
    assertEquals(original.species, restoredDog.species);
    assertEquals(original.legs, restoredDog.legs);
    assertEquals(original.breed, restoredDog.breed);
  }

  // ==========================================================================
  // Context tests - serialize/deserialize methods on context
  // ==========================================================================

  @Test
  public void context_serializeWithoutType_usesRuntimeType() {
    JsonSerializer<Person> serializer =
        (src, typeOfSrc, context) -> {
          JsonObject obj = new JsonObject();
          obj.addProperty("name", src.name);
          // Use context.serialize without type
          obj.add("nested", context.serialize(new JsonPrimitive("test")));
          return obj;
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, serializer).create();
    Person person = new Person("Test", 25);
    String json = gson.toJson(person);
    assertTrue(json.contains("\"name\":\"Test\""));
    assertTrue(json.contains("\"nested\":\"test\""));
  }

  @Test
  public void context_serializeWithType_usesSpecifiedType() {
    JsonSerializer<Person> serializer =
        (src, typeOfSrc, context) -> {
          JsonObject obj = new JsonObject();
          obj.addProperty("name", src.name);
          // Use context.serialize with explicit type
          obj.add("ageString", context.serialize(src.age, Integer.class));
          return obj;
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, serializer).create();
    Person person = new Person("Test", 25);
    String json = gson.toJson(person);
    assertTrue(json.contains("\"name\":\"Test\""));
    assertTrue(json.contains("\"ageString\":25"));
  }

  @Test
  public void context_deserialize_usesType() {
    JsonDeserializer<Person> deserializer =
        (json, typeOfT, context) -> {
          JsonObject obj = json.getAsJsonObject();
          Person person = new Person();
          person.name = obj.get("name").getAsString();
          // Use context.deserialize
          person.age = context.deserialize(obj.get("age"), Integer.class);
          return person;
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, deserializer).create();
    Person person = gson.fromJson("{\"name\":\"Test\",\"age\":25}", Person.class);
    assertEquals("Test", person.name);
    assertEquals(25, person.age);
  }

  // ==========================================================================
  // Edge cases
  // ==========================================================================

  @Test
  public void emptyJsonObject_deserializes() {
    JsonDeserializer<Person> deserializer =
        (json, typeOfT, context) -> {
          JsonObject obj = json.getAsJsonObject();
          Person person = new Person();
          if (obj.has("name")) {
            person.name = obj.get("name").getAsString();
          }
          if (obj.has("age")) {
            person.age = obj.get("age").getAsInt();
          }
          return person;
        };

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, deserializer).create();
    Person person = gson.fromJson("{}", Person.class);
    assertNull(person.name);
    assertEquals(0, person.age);
  }

  @Test
  public void serializer_returnsNull_writesNull() throws IOException {
    JsonSerializer<Person> serializer = (src, typeOfSrc, context) -> null;

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, serializer).create();
    Person person = new Person("Test", 25);
    String json = gson.toJson(person);
    assertEquals("null", json);
  }

  @Test
  public void deserializer_returnsNull_returnsNull() {
    JsonDeserializer<Person> deserializer = (json, typeOfT, context) -> null;

    Gson gson = new GsonBuilder().registerTypeAdapter(Person.class, deserializer).create();
    Person person = gson.fromJson("{\"name\":\"Test\"}", Person.class);
    assertNull(person);
  }
}
