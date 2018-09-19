package eventarelli.syslog;

public enum Level {
  LOG_EMERG   (0), /* system is unusable */
  LOG_ALERT   (1), /* action must be taken immediately */
  LOG_CRIT    (2), /* critical conditions */
  LOG_ERR     (3), /* error conditions */
  LOG_WARNING (4), /* warning conditions */
  LOG_NOTICE  (5), /* normal but significant condition */
  LOG_INFO    (6), /* informational */
  LOG_DEBUG   (7), /* debug-level messages */
  LOG_ALL     (8); /* '*' in config, all levels */

  private final int flag;

  Level(int flag) {
    this.flag = flag;
  }

  int asInt() {
    return flag;
  }
  
}
