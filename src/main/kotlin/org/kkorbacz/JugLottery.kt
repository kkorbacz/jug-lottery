package org.kkorbacz

import mu.KotlinLogging
import java.text.SimpleDateFormat

private const val GENERATOR_DELAY_IN_MILLIS = 10000L
private const val DEFAULT_NO_OF_LUCKY_NUMBERS = 4

private val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
private val MEETUP_START_DATE_TIME = DATE_FORMAT.parse("2018-12-20 18:00:00")

private typealias HandleAndTweetText = Pair<String, String>

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    JugLottery().run()
}

class JugLottery {
    fun run() {
        val idxToHandle = TwitterServiceWrapper.getTweetsWith("#kielcejava")
                .distinctBy{ it.user.screenName }
                .filter{ !it.isRetweet }
                .filter{ it.createdAt.after(MEETUP_START_DATE_TIME) }
                .filter{ it.user.screenName != "KielceJava" }
                .sortedBy { it.createdAt }
                .mapIndexed{ idx, tweet -> Pair(idx + 1, HandleAndTweetText(tweet.user.screenName, tweet.text))}
                .toMap()

        val numberOfDistinctTweets = idxToHandle.size

        log.info {"Got: [$numberOfDistinctTweets] distinct tweets"}

        log.info {"Generating lucky numbers ..."}
        Thread.sleep(GENERATOR_DELAY_IN_MILLIS)
        val luckyNumbers = pickLuckyNumbers(numberOfDistinctTweets, DEFAULT_NO_OF_LUCKY_NUMBERS)
        log.info {"And the lucky numbers are: $luckyNumbers"}

        log.info {"Which maps to the following JUG participants: "}
        luckyNumbers
                .mapNotNull { idxToHandle[it] }
                .forEach{ log.info("${it.first} with tweet: [${it.second.take(50)} ...]") }
    }
}

private fun pickLuckyNumbers(inclusiveUpperBound: Int, noOfLuckyNumbers: Int) =
        (1..inclusiveUpperBound)
                .shuffled()
                .subList(0, noOfLuckyNumbers)
