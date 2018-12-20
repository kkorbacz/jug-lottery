package org.kkorbacz

import mu.KotlinLogging
import twitter4j.Query
import twitter4j.Status
import twitter4j.TwitterFactory
import java.time.LocalDateTime

private const val MEETUP_START_DATE = "2018-12-20"

private val log = KotlinLogging.logger {}

@Suppress("Unused")
data class Tweet(
        val username: String,
        val tweetText: String,
        val createdAt: LocalDateTime,
        val isRetweet: String)

object TwitterServiceWrapper {

    fun getTweetsWith(hashTag: String): List<Status> {
        val twitter = TwitterFactory.getSingleton()
        val query = Query(hashTag)
                .count(100)
                .since(MEETUP_START_DATE)

        val tweets = twitter.search(query).tweets
        log.debug { "Got the following tweets: [$tweets]" }
        return tweets
    }
}
