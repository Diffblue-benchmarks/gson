package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ParseBenchmarkDiffblueTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ParseBenchmark.Content}
   *   <li>{@link ParseBenchmark.Content#toString()}
   * </ul>
   */
  @Test
  void testContentGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new ParseBenchmark.Content()).toString());
  }

  /** Method under test: default or parameterless constructor of {@link ParseBenchmark.Feed} */
  @Test
  void testFeedNewFeed() {
    // Arrange and Act
    ParseBenchmark.Feed actualFeed = new ParseBenchmark.Feed();

    // Assert
    assertNull(actualFeed.description);
    assertNull(actualFeed.id);
    assertNull(actualFeed.title);
    assertNull(actualFeed.items);
    assertNull(actualFeed.alternates);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualFeed.updated);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ParseBenchmark.Item}
   *   <li>{@link ParseBenchmark.Item#toString()}
   * </ul>
   */
  @Test
  void testItemGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "null\n"
            + "author: null\n"
            + "published: 0\n"
            + "updated: 0\n"
            + "null\n"
            + "liking users: null\n"
            + "alternates: null\n"
            + "categories: null",
        (new ParseBenchmark.Item()).toString());
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ParseBenchmark.Link}
   *   <li>{@link ParseBenchmark.Link#toString()}
   * </ul>
   */
  @Test
  void testLinkGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new ParseBenchmark.Link()).toString());
  }

  /** Method under test: default or parameterless constructor of {@link ParseBenchmark} */
  @Test
  void testNewParseBenchmark() {
    // Arrange and Act
    ParseBenchmark actualParseBenchmark = new ParseBenchmark();

    // Assert
    assertNull(actualParseBenchmark.api);
    assertNull(actualParseBenchmark.document);
  }

  /**
   * Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ParseBenchmark.ReaderUser}
   *   <li>{@link ParseBenchmark.ReaderUser#toString()}
   * </ul>
   */
  @Test
  void testReaderUserGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull((new ParseBenchmark.ReaderUser()).toString());
  }

  /** Method under test: default or parameterless constructor of {@link ParseBenchmark.Tweet} */
  @Test
  void testTweetNewTweet() {
    // Arrange and Act
    ParseBenchmark.Tweet actualTweet = new ParseBenchmark.Tweet();

    // Assert
    assertNull(actualTweet.retweeted_status);
    assertNull(actualTweet.user);
    assertNull(actualTweet.geo);
    assertNull(actualTweet.place);
    assertNull(actualTweet.contributors);
    assertNull(actualTweet.coordinates);
    assertNull(actualTweet.id_str);
    assertNull(actualTweet.in_reply_to_id_str);
    assertNull(actualTweet.in_reply_to_screen_name);
    assertNull(actualTweet.in_reply_to_status_id_str);
    assertNull(actualTweet.in_reply_to_user_id);
    assertNull(actualTweet.in_reply_to_user_id_str);
    assertNull(actualTweet.retweet_count);
    assertNull(actualTweet.source);
    assertNull(actualTweet.text);
    assertNull(actualTweet.created_at);
    assertFalse(actualTweet.favorited);
    assertFalse(actualTweet.retweeted);
    assertFalse(actualTweet.truncated);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualTweet.id);
  }

  /** Method under test: default or parameterless constructor of {@link ParseBenchmark.User} */
  @Test
  void testUserNewUser() {
    // Arrange and Act
    ParseBenchmark.User actualUser = new ParseBenchmark.User();

    // Assert
    assertNull(actualUser.description);
    assertNull(actualUser.id_str);
    assertNull(actualUser.lang);
    assertNull(actualUser.location);
    assertNull(actualUser.name);
    assertNull(actualUser.profile_background_color);
    assertNull(actualUser.profile_background_image_url);
    assertNull(actualUser.profile_image_url);
    assertNull(actualUser.profile_link_color);
    assertNull(actualUser.profile_sidebar_border_color);
    assertNull(actualUser.profile_sidebar_fill_color);
    assertNull(actualUser.profile_text_color);
    assertNull(actualUser.screen_name);
    assertNull(actualUser.time_zone);
    assertNull(actualUser.url);
    assertNull(actualUser.created_at);
    assertEquals(0, actualUser.favourites_count);
    assertEquals(0, actualUser.followers_count);
    assertEquals(0, actualUser.friends_count);
    assertEquals(0, actualUser.listed_count);
    assertEquals(0, actualUser.statuses_count);
    assertFalse(actualUser.contributors_enabled);
    assertFalse(actualUser.default_profile);
    assertFalse(actualUser.default_profile_image);
    assertFalse(actualUser.follow_request_sent);
    assertFalse(actualUser.following);
    assertFalse(actualUser.geo_enabled);
    assertFalse(actualUser.isProtected);
    assertFalse(actualUser.is_translator);
    assertFalse(actualUser.notifications);
    assertFalse(actualUser.profile_background_tile);
    assertFalse(actualUser.profile_use_background_image);
    assertFalse(actualUser.show_all_inline_media);
    assertFalse(actualUser.verified);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualUser.id);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualUser.utc_offset);
  }
}
