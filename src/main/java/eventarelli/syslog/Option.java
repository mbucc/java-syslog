package eventarelli.syslog;

/**
 * An option to pass to openlog; for example, log message to standard error as well as syslog.
 */
public enum Option {
  LOG_PID(0x01),      /* log the pid with each message             */
  LOG_CONS(0x02),     /* log on the console if errors in sending   */
  LOG_ODELAY(0x04),   /* delay open until first syslog() (default) */
  LOG_NDELAY(0x08),   /* don't delay open                          */
  LOG_NOWAIT(0x10),   /* don't wait for console forks: DEPRECATED  */
  LOG_PERROR(0x20);   /* log to stderr as well                     */


  private final int flag;

  Option(int flag) {
    this.flag = flag;
  }

  int asInt() {
    return flag;
  }

}
