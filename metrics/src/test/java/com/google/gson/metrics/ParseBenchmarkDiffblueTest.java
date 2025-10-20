package com.google.gson.metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.metrics.ParseBenchmark.Content;
import com.google.gson.metrics.ParseBenchmark.Feed;
import com.google.gson.metrics.ParseBenchmark.Item;
import com.google.gson.metrics.ParseBenchmark.Link;
import com.google.gson.metrics.ParseBenchmark.ReaderUser;
import com.google.gson.metrics.ParseBenchmark.Tweet;
import com.google.gson.metrics.ParseBenchmark.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ParseBenchmarkDiffblueTest {
  /**
   * Test Content getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Content}
   *   <li>{@link Content#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test Content getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Content.<init>()", "java.lang.String Content.toString()"})
  void testContentGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new Content().toString());
  }

  /**
   * Test Feed new {@link Feed} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Feed}
   */
  @Test
  @DisplayName("Test Feed new Feed (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Feed.<init>()"})
  void testFeedNewFeed() {
    // Arrange and Act
    Feed actualFeed = new Feed();

    // Assert
    assertNull(actualFeed.description);
    assertNull(actualFeed.id);
    assertNull(actualFeed.title);
    assertNull(actualFeed.items);
    assertNull(actualFeed.alternates);
    assertEquals(BagOfPrimitives.DEFAULT_VALUE, actualFeed.updated);
  }

  /**
   * Test Item getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Item}
   *   <li>{@link Item#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test Item getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Item.<init>()", "java.lang.String Item.toString()"})
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
        new Item().toString());
  }

  /**
   * Test Link getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link Link}
   *   <li>{@link Link#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test Link getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Link.<init>()", "java.lang.String Link.toString()"})
  void testLinkGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new Link().toString());
  }

  /**
   * Test new {@link ParseBenchmark} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ParseBenchmark}
   */
  @Test
  @DisplayName("Test new ParseBenchmark (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParseBenchmark.<init>()"})
  void testNewParseBenchmark() {
    // Arrange and Act
    ParseBenchmark actualParseBenchmark = new ParseBenchmark();

    // Assert
    assertNull(actualParseBenchmark.api);
    assertNull(actualParseBenchmark.document);
  }

  /**
   * Test ReaderUser getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ReaderUser}
   *   <li>{@link ReaderUser#toString()}
   * </ul>
   */
  @Test
  @DisplayName("Test ReaderUser getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ReaderUser.<init>()", "java.lang.String ReaderUser.toString()"})
  void testReaderUserGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new ReaderUser().toString());
  }

  /**
   * Test Tweet new {@link Tweet} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Tweet}
   */
  @Test
  @DisplayName("Test Tweet new Tweet (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void Tweet.<init>()"})
  void testTweetNewTweet() {
    // Arrange and Act
    Tweet actualTweet = new Tweet();

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

  /**
   * Test User new {@link User} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link User}
   */
  @Test
  @DisplayName("Test User new User (default constructor)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void User.<init>()"})
  void testUserNewUser() {
    // Arrange and Act
    User actualUser = new User();

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
