# java-syslog

Use [Java Native Access](https://github.com/java-native-access/jna) 
to log to syslog from Java.

Sample usage:

```bash
$ ./gradlew build
$ curl -L https://github.com/java-native-access/jna/raw/master/dist/jna.jar > jna.jar
$ java -classpath ./build/libs/java-syslog-1.0.0.jar:jna.jar eventarelli.syslog.Syslog
Sep 18 20:48:57  test_syslog[56407] <Info>: hello world!
$ 
```


