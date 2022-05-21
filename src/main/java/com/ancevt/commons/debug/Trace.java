/*
 *   Ancevt Commons Library
 *   Copyright (C) 2022 Ancevt (me@ancevt.com)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ancevt.commons.debug;

public class Trace {

    public static void trace(String varNames, Object... o) {
        StringBuilder s = new StringBuilder();

        varNames = varNames.replaceAll("\\s+", "");

        String[] split = varNames.split(",");
        for (int i = 0; i < o.length; i++) {
            s.append(split[i]).append(": ").append(o[i]).append(", ");
        }

        System.out.println(s.substring(0, s.length() - 2));
    }

    public static void main(String[] args) {
        trace("a, b, c", 1, 2, 3);
    }
}
