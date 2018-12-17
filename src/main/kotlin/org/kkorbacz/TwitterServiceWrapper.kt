package org.kkorbacz

import java.time.LocalDateTime

data class Tweet(
        val username: String,
        val tweetText: String,
        val createdAt: LocalDateTime,
        val isRetweet: String)

@Suppress("Unused")
object TwitterServiceWrapper {

    fun getTweetsWith(hashTag: String): List<Tweet> = emptyList()

}