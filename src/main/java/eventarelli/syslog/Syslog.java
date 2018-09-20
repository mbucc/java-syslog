package eventarelli.syslog;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Wrapper around openlib and syslog methods from the operating system's library that controls the system log.
 */
public class Syslog {

  /**
   * Define the libc methods we want and create a static instance of these methods mapped to the
   * native libc.so library.
   */
  public interface Clib extends Library {

    /**
     * A shared static instance of the two methods.
     *
     * <p>Per Posix, syslog should be thread-safe.
     * See <a href="http://pubs.opengroup.org/onlinepubs/000095399/functions/xsh_chap02_09.html#tag_02_09_01">http://pubs.opengroup.org/onlinepubs/000095399/functions/xsh_chap02_09.html#tag_02_09_01</a></p>
     */
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
   *
   * <p>Takes a printf style list of strings; for example, Syslog.debug("%s is the %s", "42", "answer");</p>
   * <p>Note that the %s is the only formatting anchor that makes sense, as all arguments
   * are strings.</p>
   */

  public static void info(String... args) {
    Clib.INSTANCE.syslog(Level.LOG_INFO.asInt(), args);
  }


  /**
   * Print the message "hello world!" with ident "test_syslog" to the LOG_USER facility and also
   * print the message to stderr.
   * @param args Ignored
   */

  public static void main(String[] args) {
    Syslog.openlog("test_syslog", Facility.LOG_USER, Option.LOG_PERROR);
    Syslog.info("hello world!");
  }

}

