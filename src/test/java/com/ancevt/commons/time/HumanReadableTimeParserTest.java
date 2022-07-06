package com.ancevt.commons.time;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class HumanReadableTimeParserTest {

    @Test
    void humanReadableTimeParserTest() {
        final HumanReadableTimeParser time = new HumanReadableTimeParser("5d10h32m5s12ms");

        final long millis = time.getMillis();
        final long seconds = time.getSeconds();

        assertThat(millis, is(469925012L));
        assertThat(seconds, is(469925L));
    }
}
