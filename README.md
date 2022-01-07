# session-warning-demo

**run with maven:**
> mvn clean jetty:run

**Open localhost website:** 
> http://localhost:8080/session-warning-demo/

The default configuration has a session timeout set to 65 seconds, and the warning message set to be displayed 60 seconds before session timeout.
After opening the page, you will see the warning after 5 seconds of inactivity.

Configuration
The session timeout can be configured in [WEB-INF/zk.xml](https://github.com/zkoss-demo/session-warning-demo/blob/main/src/main/webapp/WEB-INF/zk.xml#L14)

The timeout warning message delay can be configured in the [TimeoutWarningInitiator.java](https://github.com/zkoss-demo/session-warning-demo/blob/main/src/main/java/org/zkoss/session_warning_demo/TimeoutWarningInitiator.java#L15) class.
