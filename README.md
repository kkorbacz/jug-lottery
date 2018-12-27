# jug-lottery

Before using application one has to provide twitter credentials in: twitter4j.properties file.

More info in:
* http://twitter4j.org/en/configuration.html
* https://developer.twitter.com/en/docs/basics/authentication/guides/access-tokens.html

## Running application

`./gradlew run`

## todos
1. remove whitespaces from tweet text before printout
1. document which application version (or commit) was used when running lottery
1. todo add unit tests
1. todo adjust / review build.gradle
1. todo handle corner cases (0 tweets, 1 tweet, mismatch due to incorrect input params)
1. todo warn when full page returned (more than 100 tweets)
1. todo nullability considerations of list of tweets
1. todo add info about data fidelity of standard search api into README.md ; not all tweets can be returned
1. todo divide code into neat classes
1. todo use java.time instead of old api
1. code cleanup unhardcode stuff, use input params, better naming
1. consider writing frontend for application or integrate it with Alexa