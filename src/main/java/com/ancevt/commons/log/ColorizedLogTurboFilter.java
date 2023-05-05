package com.ancevt.commons.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import com.ancevt.commons.debug.TraceUtils;
import org.slf4j.Marker;

public class ColorizedLogTurboFilter extends TurboFilter {

    private static boolean enabled;

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        ColorizedLogTurboFilter.enabled = enabled;
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        if (!enabled) return FilterReply.NEUTRAL;

        boolean logChanged = false;

        if (s != null && s.contains("<>")) {
            s = TraceUtils.colorize(s);
            logChanged = true;
        }

        if (logChanged) {
            switch (level.levelInt) {
                case Level.TRACE_INT:
                    logger.trace(s, objects);
                    break;
                case Level.INFO_INT:
                    logger.info(s, objects);
                    break;
                case Level.WARN_INT:
                    logger.warn(s, objects);
                    break;
                case Level.DEBUG_INT:
                    logger.debug(s, objects);
                    break;
                case Level.ERROR_INT:
                    logger.error(s, objects);
                    break;
            }


            return FilterReply.DENY;
        }

        return FilterReply.NEUTRAL;
    }
}


