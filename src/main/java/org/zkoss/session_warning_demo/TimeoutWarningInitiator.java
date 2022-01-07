package org.zkoss.session_warning_demo;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WebApps;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.InitiatorExt;

public class TimeoutWarningInitiator implements Initiator, InitiatorExt {

	public static final int WARNING_BEFORE_TIMEOUT_IN_SECONDS = 60;

	@Override
	public void doAfterCompose(Page page, Component[] comps) throws Exception {
		int sessionMaxInactiveIntervalInSeconds = WebApps.getCurrent().getConfiguration().getSessionMaxInactiveInterval();
		int secondsToMillisecondsMultiplier = 1000;
		int warningTimerInMilliseconds = (sessionMaxInactiveIntervalInSeconds - WARNING_BEFORE_TIMEOUT_IN_SECONDS)*secondsToMillisecondsMultiplier;
		String timeoutCount = Integer.toString(warningTimerInMilliseconds);
		Clients.evalJavaScript("timeoutWarning.init("+ timeoutCount +", true);");
		Component warningWindow = page.getFirstRoot().getFellowIfAny("sessionsExpirationWarning", false);
		if(warningWindow == null) {
			page.getFirstRoot().appendChild(Executions.createComponents("~./zul/timeoutwarning.zul",null,null));
		}
	}

	@Override
	public boolean doCatch(Throwable ex) throws Exception {
		return false;
	}

	@Override
	public void doFinally() throws Exception {
		
	}

	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
	}

}
