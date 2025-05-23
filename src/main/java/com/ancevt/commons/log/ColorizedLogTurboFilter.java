/**
 * Copyright (C) 2024 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        boolean logChanged = false;

        if (s != null && s.contains("<>")) {
            if(enabled) {
                s = TraceUtils.colorize(s);
            } else {
                boolean tmp = TraceUtils.isEnabled();
                TraceUtils.setEnabled(false);
                s = TraceUtils.colorize(s);
                TraceUtils.setEnabled(tmp);
            }

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


