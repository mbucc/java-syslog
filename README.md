# java-syslog

Use [Java Native Access](https://github.com/java-native-access/jna) 
to log to syslog from Java.

Sample usage:

```bash
$ ./gradlew jar
$ curl -L https://github.com/java-native-access/jna/raw/master/dist/jna.jar > jna.jar
$ java -classpath ./build/libs/java-syslog-2.0.0.jar:jna.jar com.eventarelli.syslog.Syslog
Oct  2 18:46:59  test_syslog[72914] <Info>: hello world!
$ 
```

Following Dave Cheney's [suggested approach](https://dave.cheney.net/2015/11/05/lets-talk-about-logging), 
only provides info and debug.





