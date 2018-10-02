package com.eventarelli.syslog;

/**
 * What part of the system is logging the message; for example, the mail subsystem.
 */
public enum Facility {
  
  LOG_KERN        (0<<3),    /* kernel messages */
  LOG_USER        (1<<3),    /* random user-level messages */
  LOG_MAIL        (2<<3),    /* mail system */
  LOG_DAEMON      (3<<3),    /* system daemons */
  LOG_AUTH        (4<<3),    /* security/authorization messages */
  LOG_SYSLOG      (5<<3),    /* messages generated internally by syslogd */
  LOG_LPR         (6<<3),    /* line printer subsystem */
  LOG_NEWS        (7<<3),    /* network news subsystem */
  LOG_UUCP        (8<<3),    /* UUCP subsystem */
  LOG_CRON        (9<<3),    /* clock daemon */
  LOG_AUTHPRIV    (10<<3),   /* security/authorization messages (private), */
  LOG_FTP         (11<<3),   /* ftp daemon */
  LOG_NETINFO     (12<<3),   /* NetInfo */
  LOG_REMOTEAUTH  (13<<3),   /* remote authentication/authorization */
  LOG_INSTALL     (14<<3),   /* installer subsystem */
  LOG_RAS         (15<<3),   /* Remote Access Service (VPN / PPP), */
  LOG_LOCAL0      (16<<3),   /* reserved for local use */
  LOG_LOCAL1      (17<<3),   /* reserved for local use */
  LOG_LOCAL2      (18<<3),   /* reserved for local use */
  LOG_LOCAL3      (19<<3),   /* reserved for local use */
  LOG_LOCAL4      (20<<3),   /* reserved for local use */
  LOG_LOCAL5      (21<<3),   /* reserved for local use */
  LOG_LOCAL6      (22<<3),   /* reserved for local use */
  LOG_LOCAL7      (23<<3),   /* reserved for local use */
  LOG_LAUNCHD     (24<<3);   /* launchd - general bootstrap daemon */


  private final int flag;

  Facility(int flag) {
    this.flag = flag;
  }

  int asInt() {
    return flag;
  }

}
