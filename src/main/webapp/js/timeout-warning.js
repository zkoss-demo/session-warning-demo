zk.afterLoad(function() {

    var warningDelay;
    var intervalId;
    var origResetTimeout;

    var show = function() {
       zk.$("$sessionsExpirationWarning").setVisible(true);
    };

    var hide = function() {
       zk.$("$sessionsExpirationWarning").setVisible(false);
    };

    var check = function () {
        var timeoutWarningTime = window.localStorage.getItem('timeout-warning-time');
        if(timeoutWarningTime < Date.now()) {
            show();
        } else {
            hide();
        }
    };

    var reset = function () {
        var timeoutWarningTime = Date.now() + warningDelay;
        window.localStorage.setItem('timeout-warning-time', timeoutWarningTime);
        console.log("reset timeout warning time to:", timeoutWarningTime);
        hide();
    };

    var init = function (delay, automaticExtend) {
        if(intervalId) {
            console.warn('timeoutWarning already initialized');
            return;
        }

        warningDelay = delay;
        if(automaticExtend) {
            origResetTimeout = zAu._resetTimeout;
            zAu._resetTimeout = function() {
                origResetTimeout.apply(this, arguments);
                reset();
            }
        }
        reset();
        
        intervalId = window.setInterval(check, 1000);
    };

    var stop = function() {
        if(intervalId) {
            window.clearInterval(intervalId);
            intervalId = null;
        }
        if(origResetTimeout) {
            zAu._resetTimeout = origResetTimeout;
            origResetTimeout = null;
        }
    }

    window.timeoutWarning = {
        init: init,
        stop: stop,
        reset: reset,
        check: check,
        show: show,
        hide: hide
    };
});
