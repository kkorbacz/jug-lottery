# jug-lottery

Before using application one has to provide twitter credentials in: twitter4j.properties file.

More info in:
http://twitter4j.org/en/configuration.html
https://developer.twitter.com/en/docs/basics/authentication/guides/access-tokens.html

## todos
0. remove whitespaces from tweet text before printout
0. document which application version (or commit) was used when running lottery
0. todo add unit tests
0. todo adjust build.gradle
0. todo handle corner cases (0 tweets, 1 tweet, mismatch due to incorrect input params)
0. todo warn when full page returned
0. todo nullability of list of tweets
0. todo add info about data fidelity of standard search api into README.md
0. todo divide code into neat classes
0. todo use java.time instead of old api