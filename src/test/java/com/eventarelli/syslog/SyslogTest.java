package com.eventarelli.syslog;

import org.junit.jupiter.api.Test;


class SyslogTest {

  @Test
  void stderrOptionWorks() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.info("hello world!");
  }

  @Test
  void noOptionsDoesNotCrash() {
    Syslog.openlog("test_syslog", Facility.LOG_USER);
    Syslog.info("hello world!");
  }

  @Test
  void multipleOptionsDoesNotCrash() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR, Option.LOG_CONS,
        Option.LOG_NDELAY);
    Syslog.info("hello world!");
  }

  @Test
  void debugDoesNotCrash() {
    Syslog.debug("hello world!");
  }

  @Test
  void infoDoesNotCrash() {
    Syslog.info("hello world!");
  }

  @Test
  void formatAnchorsWork() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.debug("hello world: %s %s %s", "and", "another", "thing ...");
  }

  @Test
  void tooManyFormatAnchorsDoesNotCrash() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.debug("hello world: %s %s %s %s", "and", "another", "thing ...");
  }

  @Test
  void tooFewFormatAnchorsDoesNotCrash() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.debug("hello world: %s %s", "and", "another", "thing ...");
  }

  @Test
  void numericAnchorPrintsADifferentIntegerEachTime() {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.debug("hello world: %d %s", "42", "another");
  }

}
