package org.kkorbacz

import mu.KotlinLogging
import twitter4j.Query
import twitter4j.Status
import twitter4j.TwitterFactory

private const val GENERATOR_DELAY_IN_MILLIS = 10000L
private const val DEFAULT_NO_OF_LUCKY_NUMBERS = 4

private typealias HandleAndTweetText = Pair<String, String>

private val log = KotlinLogging.logger {}

// todo add unit tests
// todo adjust build.gradle
// todo add proper since (meaning date not hours)
// todo filter out tweets created before the meetup
// todo handle corner cases (0 tweets, 1 tweet, mismatch due to incorrect input params)
// todo migrate to kotlin 1.3
// todo verify order of tweets
// todo warn when full page returned
// todo nullability of list of tweets
// todo no retweets in search result
// todo place debug logs into separate file (second appender)
// todo add info about data fidelity of standard search api into README.md
// todo divide code into neat classes
fun main(args: Array<String>) {

    val idxToHandle = getTweetsWith("#kielcejava")
            .also { log.debug {"Got the following tweets: [$it]"} }
            .distinctBy{ it.user.screenName }
            .mapIndexed{ idx, tweet -> Pair(idx + 1, HandleAndTweetText(tweet.user.screenName, tweet.text))}
            .toMap()

    val numberOfDistinctTweets = idxToHandle.size

    log.info {"Got: [$numberOfDistinctTweets] distinct tweets"}

    log.info {"Generating lucky numbers ..."}
    Thread.sleep(GENERATOR_DELAY_IN_MILLIS)
    val luckyNumbers = pickLuckyNumbers(numberOfDistinctTweets, 2)
    log.info {"And the lucky numbers are: $luckyNumbers"}

    log.info {"Which maps to the following JUG participants: "}
    luckyNumbers
            .mapNotNull { idxToHandle[it] }
            .forEach{ log.info("${it.first} with tweet: [${it.second}]") }
}

private fun getTweetsWith(hashTag: String): List<Status> {
    val twitter = TwitterFactory.getSingleton()
    val query = Query(hashTag).count(100)

    return twitter.search(query).tweets
}

private fun pickLuckyNumbers(inclusiveUpperBound: Int, noOfLuckyNumbers: Int) =
        (1..inclusiveUpperBound)
                .shuffled()
                .subList(0, noOfLuckyNumbers)