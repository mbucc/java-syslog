package eventarelli.syslog;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class Syslog {

  public interface Clib extends Library {

    // Per Posix, syslog should be thread-safe.
    // http://pubs.opengroup.org/onlinepubs/000095399/functions/xsh_chap02_09.html#tag_02_09_01
    Clib INSTANCE = Native.loadLibrary("c", Clib.class);

    // void
    // syslog(int priority, const char *message, ...);
    void syslog(int priority, String... args);

    // void
    // openlog(const char *ident, int logopt, int facility);
    void openlog(String ident, int logopt, int facility);

  }


  /**
   * Setup more specialized processing of the messages sent by syslog().
   *
   * @param ident This will be prepended to every message.
   * @param facility Default facility for messages that do specify one.
   * @param logopts Specifies logging options.
   */

  public static void openlog(String ident, Facility facility, Option... logopts) {
    int opts = 0;
    for (Option o: logopts) {
      opts |= o.asInt();
    }
    Clib.INSTANCE.openlog(ident, opts, facility.asInt());
  }

  /**
   * Log a message at LOG_DEBUG level.
   */

  public static void debug(String... args) {
    Clib.INSTANCE.syslog(Level.LOG_DEBUG.asInt(), args);
  }

  /**
   * Log a message at LOG_INFO level.
   */

  public static void info(String... args) {
    Clib.INSTANCE.syslog(Level.LOG_INFO.asInt(), args);
  }

  public static void main(String[] args) {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.info("hello world!");
  }

}

