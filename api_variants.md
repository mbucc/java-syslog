# kotlin-syslog

Log to syslog daemon from Kotlin or Java.

# API

## BSD

[logger](http://man.openbsd.org/logger)
```
logger	[-is] [-f file] [-p pri] [-t tag] [message ...]

     -i       Log the process id of the logger process with each line.

     -s       Log the message to standard error, as well as the system log.

     -f file  Log the specified file.

     -p pri   Enter the message with the specified priority.  The priority may be specified numerically or as a ``facility.level'' pair.  For example,
              ``-p local3.info'' logs the message(s) as informational level in the local3 facility.  The default is ``user.notice.''

     -t tag   Mark every line in the log with the specified tag.

     message  Write the message to log; if not specified, and the -f flag is not provided, standard input is logged.

     The logger utility exits 0 on success, and >0 if an error occurs.

```


[openlog](http://man.openbsd.org/syslog)
```C
// The openlog() function provides for more specialized processing of the 
// messages sent by syslog() and vsyslog(). The parameter ident is a string 
// that will be prepended to every message. The logopt argument is a bit 
// field specifying logging options (for example, LOG_PERROR, which writes
// to stderr as well as syslog).  The facility parameter encodes a default
// facility to be assigned to all messages that do not have an explicit
// facility encoded
void 
openlog(const char *ident, int logopt, int facility);
```

[syslog](http://man.openbsd.org/syslog)
```C
// Priorities are encoded as a facility and a level. The facility describes
// the part of the system generating the message (for example, the mail system
// or a user process, which is the default, if no facility specified).
void syslog(int priority, const char *msg, ...);
```

Alpine 3.8 on Raspberry Pi---syslog is in libc.

```bash
tallis:/usr# readelf -a ./lib/libc.so | grep syslog
   443: 000333a0   140 FUNC    GLOBAL DEFAULT    6 __vsyslog
   943: 0003342c    40 FUNC    GLOBAL DEFAULT    6 syslog
  1516: 000333a0   140 FUNC    WEAK   DEFAULT    6 vsyslog
tallis:/usr# uname -a
Linux tallis 4.14.52-0-rpi2 #1-Alpine SMP Tue Jun 26 08:55:40 UTC 2018 armv7l Linux
tallis:/usr# 
```



## golang
[Dial](https://golang.org/src/log/syslog/syslog.go?s=2740:2820#L108)
```golang
// Dial establishes a connection to a log daemon by connecting to
// address raddr on the specified network. Each write to the returned
// writer sends a log message with the facility and severity
// (from priority) and tag. If tag is empty, the os.Args[0] is used.
// If network is empty, Dial will connect to the local syslog server.
// Otherwise, see the documentation for net.Dial for valid values
// of network and raddr.
func Dial(network, raddr string, priority Priority, tag string) (*Writer, error) {...}
```

[Writer.Info](https://golang.org/src/log/syslog/syslog.go?s=5357:5394#L225)
```golang
// Info logs a message with severity LOG_INFO, ignoring the severity
// passed to New.
func (w *Writer) Info(m string) error {...}
```

[Writer.Close](https://golang.org/src/log/syslog/syslog.go?s=3996:4026#L169)
```golang
// Close closes a connection to the syslog daemon.
func (w *Writer) Close() error {...}
```


## Plan9

[syslog.c](https://github.com/brho/plan9/blob/master/sys/src/libc/9sys/syslog.c)

## Rust

Main page: [syslog](https://docs.rs/syslog/4.0.1/syslog/)

[init](https://docs.rs/syslog/4.0.1/syslog/fn.init.html)
```Rust
pub fn init(
    facility: Facility, 
    log_level: LevelFilter, 
    application_name: Option<&str>
) -> Result<()>

// This tries to connect to syslog by following ways:
// 
//   1. Unix sockets /dev/log and /var/run/syslog (in this order)
//   2. Tcp connection to 127.0.0.1:601
//   3. Udp connection to 127.0.0.1:514
//
// Note the last option usually (almost) never fails in this method. So 
// this method doesn't return error even if there is no syslog.
// 
// If application_name is None name is derived from executable name
```

[init_unix](https://docs.rs/syslog/4.0.1/syslog/fn.init_unix.html)
```Rust
pub fn init_unix(facility: Facility, log_level: LevelFilter) -> Result<()>

// Unix socket Logger init function compatible with log crate
```

All init methods return a (error, Logger) tuplish thing called a [Result](https://docs.rs/syslog/4.0.1/syslog/type.Result.html).
[Logger](https://docs.rs/syslog/4.0.1/syslog/struct.Logger.html) has methods
emerg, alert, crit, err, warning, notice and info.

## Ruby

[new](https://docs.ruby-lang.org/en/2.1.0/Syslog/Logger.html#method-c-new)
```Ruby
log = Syslog::Logger.new 'my_program', Syslog::LOG_LOCAL1
```

[info](https://docs.ruby-lang.org/en/2.1.0/Syslog/Logger.html#method-i-info)
```Ruby
log.info 'this will be logged via syslog(3)'
```

## Haskell


[syslog](http://hackage.haskell.org/package/hsyslog-5.0.1/docs/System-Posix-Syslog.html#v:syslog)
```Haskell
-- Maybe Facility  default facility to User, if none specified
-- Priority        log with this priority
-- CStringLen      the log message
syslog :: Maybe Facility -> Priority -> CStringLen -> IO ()
```


[openlog](http://hackage.haskell.org/package/hsyslog-5.0.1/docs/System-Posix-Syslog.html#v:openlog)
```Haskell
-- CString   identifier prepended to all log messsages
-- [Option]  options to configure syslog
-- Facility  facility to use
openlog :: CString -> [Option] -> Facility -> IO()

--
-- This function configures the process-wide hidden state of the system's 
-- syslog engine. It's probably a bad idea to call this function anywhere 
-- except at the very top of your program's main function. And even then 
-- you should probably prefer withSyslog instead, which guarantees that 
-- syslog is properly initialized within its scope.
--
```

Simple example that logs "Hello World" message at INFO level and User facility:
```Haskell
syslog (Just User) Info (withCStringLen "Hello, world.")
```


