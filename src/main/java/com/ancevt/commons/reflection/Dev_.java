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
package com.ancevt.commons.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Dev_ {

    public static void main(String[] args) {
        Test test = new Test(148, "hello", Color_.of(0));

        ObjectPropertyTool objectPropertyTool = new ObjectPropertyTool();

        objectPropertyTool.setProperties(test, new HashMap<String, Object>() {
            {
                put("id", 1);
                put("name", "Andre");
                put("color", Color_.of(0xFADED));
            }
        });

        System.out.println(test);

        objectPropertyTool.addTypeFilter(Color_.class, new ObjectPropertyTool.TypeFilter<Color_>() {
            @Override
            public String encode(Color_ o) {
                return o.toHexString();
            }

            @Override
            public Color_ decode(String src) {
                return Color_.of(src);
            }
        });

        Map<String, Object> properties = objectPropertyTool.getProperties(test);
        System.out.println(properties);



    }




    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public interface ITest {

        void setId(int id);

        int getId();

        void setName(String name);

        String getName();

        void setColor(Color_ color);

        Color_ getColor();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test implements ITest {

        private int id;

        private String name;

        private Color_ color;
    }

    public static class Color_ {

        public static final Color_ ALICE_BLUE  = new Color_( 0xF0F8FF, true);
        public static final Color_ ANTIQUE_WHITE  = new Color_( 0xFAEBD7, true);
        public static final Color_ AQUA  = new Color_( 0x00FFFF, true);
        public static final Color_ AQUAMARINE  = new Color_( 0x7FFFD4, true);
        public static final Color_ AZURE  = new Color_( 0xF0FFFF, true);
        public static final Color_ BEIGE  = new Color_( 0xF5F5DC, true);
        public static final Color_ BISQUE  = new Color_( 0xFFE4C4, true);
        public static final Color_ BLACK  = new Color_( 0x000000, true);
        public static final Color_ BLANCHED_ALMOND  = new Color_( 0xFFEBCD, true);
        public static final Color_ BLUE  = new Color_( 0x0000FF, true);
        public static final Color_ BLUE_VIOLET  = new Color_( 0x8A2BE2, true);
        public static final Color_ BROWN  = new Color_( 0xA52A2A, true);
        public static final Color_ BURLY_WOOD  = new Color_( 0xDEB887, true);
        public static final Color_ CADET_BLUE  = new Color_( 0x5F9EA0, true);
        public static final Color_ CHARTREUSE  = new Color_( 0x7FFF00, true);
        public static final Color_ CHOCOLATE  = new Color_( 0xD2691E, true);
        public static final Color_ CORAL  = new Color_( 0xFF7F50, true);
        public static final Color_ CORNFLOWER_BLUE  = new Color_( 0x6495ED, true);
        public static final Color_ CORNSILK  = new Color_( 0xFFF8DC, true);
        public static final Color_ CRIMSON  = new Color_( 0xDC143C, true);
        public static final Color_ CYAN  = new Color_( 0x00FFFF, true);
        public static final Color_ DARK_BLUE  = new Color_( 0x00008B, true);
        public static final Color_ DARK_CYAN  = new Color_( 0x008B8B, true);
        public static final Color_ DARKGOLDEN_ROD  = new Color_( 0xB8860B, true);
        public static final Color_ DARK_GRAY  = new Color_( 0xA9A9A9, true);
        public static final Color_ DARK_GREY  = new Color_( 0xA9A9A9, true);
        public static final Color_ DARK_GREEN  = new Color_( 0x006400, true);
        public static final Color_ DARK_KHAKI  = new Color_( 0xBDB76B, true);
        public static final Color_ DARK_MAGENTA  = new Color_( 0x8B008B, true);
        public static final Color_ DARK_OLIVEGREEN  = new Color_( 0x556B2F, true);
        public static final Color_ DARK_ORANGE  = new Color_( 0xFF8C00, true);
        public static final Color_ DARK_ORCHID  = new Color_( 0x9932CC, true);
        public static final Color_ DARK_RED  = new Color_( 0x8B0000, true);
        public static final Color_ DARK_SALMON  = new Color_( 0xE9967A, true);
        public static final Color_ DARK_SEAGREEN  = new Color_( 0x8FBC8F, true);
        public static final Color_ DARK_SLATEBLUE  = new Color_( 0x483D8B, true);
        public static final Color_ DARK_SLATEGRAY  = new Color_( 0x2F4F4F, true);
        public static final Color_ DARK_SLATEGREY  = new Color_( 0x2F4F4F, true);
        public static final Color_ DARK_TURQUOISE  = new Color_( 0x00CED1, true);
        public static final Color_ DARK_VIOLET  = new Color_( 0x9400D3, true);
        public static final Color_ DEEP_PINK  = new Color_( 0xFF1493, true);
        public static final Color_ DEEP_SKY_BLUE  = new Color_( 0x00BFFF, true);
        public static final Color_ DIM_GRAY  = new Color_( 0x696969, true);
        public static final Color_ DIM_GREY  = new Color_( 0x696969, true);
        public static final Color_ DODGER_BLUE  = new Color_( 0x1E90FF, true);
        public static final Color_ FIRE_BRICK  = new Color_( 0xB22222, true);
        public static final Color_ FLORAL_WHITE  = new Color_( 0xFFFAF0, true);
        public static final Color_ FOREST_GREEN  = new Color_( 0x228B22, true);
        public static final Color_ FUCHSIA  = new Color_( 0xFF00FF, true);
        public static final Color_ GAINSBORO  = new Color_( 0xDCDCDC, true);
        public static final Color_ GHOST_WHITE  = new Color_( 0xF8F8FF, true);
        public static final Color_ GOLD  = new Color_( 0xFFD700, true);
        public static final Color_ GOLDEN_ROD  = new Color_( 0xDAA520, true);
        public static final Color_ GRAY  = new Color_( 0x808080, true);
        public static final Color_ GREY  = new Color_( 0x808080, true);
        public static final Color_ GREEN  = new Color_( 0x008000, true);
        public static final Color_ GREEN_YELLOW  = new Color_( 0xADFF2F, true);
        public static final Color_ HONEY_DEW  = new Color_( 0xF0FFF0, true);
        public static final Color_ HOT_PINK  = new Color_( 0xFF69B4, true);
        public static final Color_ INDIAN_RED  = new Color_( 0xCD5C5C, true);
        public static final Color_ INDIGO  = new Color_( 0x4B0082, true);
        public static final Color_ IVORY  = new Color_( 0xFFFFF0, true);
        public static final Color_ KHAKI  = new Color_( 0xF0E68C, true);
        public static final Color_ LAVENDER  = new Color_( 0xE6E6FA, true);
        public static final Color_ LAVENDERBLUSH  = new Color_( 0xFFF0F5, true);
        public static final Color_ LAWNGREEN  = new Color_( 0x7CFC00, true);
        public static final Color_ LEMON_CHIFFON  = new Color_( 0xFFFACD, true);
        public static final Color_ LIGHT_BLUE  = new Color_( 0xADD8E6, true);
        public static final Color_ LIGHT_CORAL  = new Color_( 0xF08080, true);
        public static final Color_ LIGHT_CYAN  = new Color_( 0xE0FFFF, true);
        public static final Color_ LIGHT_GOLDENRODYELLOW  = new Color_( 0xFAFAD2, true);
        public static final Color_ LIGHT_GRAY  = new Color_( 0xD3D3D3, true);
        public static final Color_ LIGHT_GREY  = new Color_( 0xD3D3D3, true);
        public static final Color_ LIGHT_GREEN  = new Color_( 0x90EE90, true);
        public static final Color_ LIGHT_PINK  = new Color_( 0xFFB6C1, true);
        public static final Color_ LIGHT_SALMON  = new Color_( 0xFFA07A, true);
        public static final Color_ LIGHT_SEAGREEN  = new Color_( 0x20B2AA, true);
        public static final Color_ LIGHT_SKYBLUE  = new Color_( 0x87CEFA, true);
        public static final Color_ LIGHT_SLATEGRAY  = new Color_( 0x778899, true);
        public static final Color_ LIGHT_SLATEGREY  = new Color_( 0x778899, true);
        public static final Color_ LIGHT_STEELBLUE  = new Color_( 0xB0C4DE, true);
        public static final Color_ LIGHT_YELLOW  = new Color_( 0xFFFFE0, true);
        public static final Color_ LIME  = new Color_( 0x00FF00, true);
        public static final Color_ LIMEGREEN  = new Color_( 0x32CD32, true);
        public static final Color_ LINEN  = new Color_( 0xFAF0E6, true);
        public static final Color_ MAGENTA  = new Color_( 0xFF00FF, true);
        public static final Color_ MAROON  = new Color_( 0x800000, true);
        public static final Color_ MEDIUM_AQUAMARINE  = new Color_( 0x66CDAA, true);
        public static final Color_ MEDIUM_BLUE  = new Color_( 0x0000CD, true);
        public static final Color_ MEDIUM_ORCHID  = new Color_( 0xBA55D3, true);
        public static final Color_ MEDIUM_PURPLE  = new Color_( 0x9370DB, true);
        public static final Color_ MEDIUM_SEAGREEN  = new Color_( 0x3CB371, true);
        public static final Color_ MEDIUM_SLATEBLUE  = new Color_( 0x7B68EE, true);
        public static final Color_ MEDIUM_SPRINGGREEN  = new Color_( 0x00FA9A, true);
        public static final Color_ MEDIUM_TURQUOISE  = new Color_( 0x48D1CC, true);
        public static final Color_ MEDIUM_VIOLETRED  = new Color_( 0xC71585, true);
        public static final Color_ MIDNIGHT_BLUE  = new Color_( 0x191970, true);
        public static final Color_ MINT_CREAM  = new Color_( 0xF5FFFA, true);
        public static final Color_ MISTY_ROSE  = new Color_( 0xFFE4E1, true);
        public static final Color_ MOCCASIN  = new Color_( 0xFFE4B5, true);
        public static final Color_ NAVAJO_WHITE  = new Color_( 0xFFDEAD, true);
        public static final Color_ NAVY  = new Color_( 0x000080, true);
        public static final Color_ OLD_LACE  = new Color_( 0xFDF5E6, true);
        public static final Color_ OLIVE  = new Color_( 0x808000, true);
        public static final Color_ OLIVE_DRAB  = new Color_( 0x6B8E23, true);
        public static final Color_ ORANGE  = new Color_( 0xFFA500, true);
        public static final Color_ ORANGE_RED  = new Color_( 0xFF4500, true);
        public static final Color_ ORCHID  = new Color_( 0xDA70D6, true);
        public static final Color_ PALE_GOLDEN_ROD  = new Color_( 0xEEE8AA, true);
        public static final Color_ PALE_GREEN  = new Color_( 0x98FB98, true);
        public static final Color_ PALE_TURQUOISE  = new Color_( 0xAFEEEE, true);
        public static final Color_ PALE_VIOLET_RED  = new Color_( 0xDB7093, true);
        public static final Color_ PAPAYA_WHIP  = new Color_( 0xFFEFD5, true);
        public static final Color_ PEACH_PUFF  = new Color_( 0xFFDAB9, true);
        public static final Color_ PERU  = new Color_( 0xCD853F, true);
        public static final Color_ PINK  = new Color_( 0xFFC0CB, true);
        public static final Color_ PLUM  = new Color_( 0xDDA0DD, true);
        public static final Color_ POWDER_BLUE  = new Color_( 0xB0E0E6, true);
        public static final Color_ PURPLE  = new Color_( 0x800080, true);
        public static final Color_ REBECCA_PURPLE  = new Color_( 0x663399, true);
        public static final Color_ RED  = new Color_( 0xFF0000, true);
        public static final Color_ ROSY_BROWN  = new Color_( 0xBC8F8F, true);
        public static final Color_ ROYAL_BLUE  = new Color_( 0x4169E1, true);
        public static final Color_ SADDLE_BROWN  = new Color_( 0x8B4513, true);
        public static final Color_ SALMON  = new Color_( 0xFA8072, true);
        public static final Color_ SANDY_BROWN  = new Color_( 0xF4A460, true);
        public static final Color_ SEA_GREEN  = new Color_( 0x2E8B57, true);
        public static final Color_ SEA_SHELL  = new Color_( 0xFFF5EE, true);
        public static final Color_ SIENNA  = new Color_( 0xA0522D, true);
        public static final Color_ SILVER  = new Color_( 0xC0C0C0, true);
        public static final Color_ SKY_BLUE  = new Color_( 0x87CEEB, true);
        public static final Color_ SLATE_BLUE  = new Color_( 0x6A5ACD, true);
        public static final Color_ SLATE_GRAY  = new Color_( 0x708090, true);
        public static final Color_ SLATE_GREY  = new Color_( 0x708090, true);
        public static final Color_ SNOW  = new Color_( 0xFFFAFA, true);
        public static final Color_ SPRING_GREEN  = new Color_( 0x00FF7F, true);
        public static final Color_ STEEL_BLUE  = new Color_( 0x4682B4, true);
        public static final Color_ TAN  = new Color_( 0xD2B48C, true);
        public static final Color_ TEAL  = new Color_( 0x008080, true);
        public static final Color_ THISTLE  = new Color_( 0xD8BFD8, true);
        public static final Color_ TOMATO  = new Color_( 0xFF6347, true);
        public static final Color_ TURQUOISE  = new Color_( 0x40E0D0, true);
        public static final Color_ VIOLET  = new Color_( 0xEE82EE, true);
        public static final Color_ WHEAT  = new Color_( 0xF5DEB3, true);
        public static final Color_ WHITE  = new Color_( 0xFFFFFF, true);
        public static final Color_ WHITE_SMOKE  = new Color_( 0xF5F5F5, true);
        public static final Color_ YELLOW  = new Color_( 0xFFFF00, true);
        public static final Color_ YELLOW_GREEN  = new Color_( 0x9ACD32, true);

        private int r;
        private int g;
        private int b;

        private final boolean immutable;

        public Color_(int r, int g, int b, boolean immutable) {
            setRGB(r, g, b);
            this.immutable = immutable;
        }

        public Color_(int r, int g, int b) {
            this(r, g, b, false);
        }

        public Color_(int rgb, boolean immutable) {
            setValue(rgb);
            this.immutable = immutable;
        }

        public Color_(int rgb) {
            this(rgb, false);
        }

        public Color_(String hex, boolean immutable) {
            setValue(Integer.parseInt(hex, 16));
            this.immutable = immutable;
        }

        public Color_(String hex) {
            this(hex, false);
        }

        public void setRGB(int r, int g, int b) {
            setR(r);
            setG(g);
            setB(b);
        }

        public void setValue(int rgb) {
            setR((rgb >> 16) & 0xFF);
            setG((rgb >> 8) & 0xFF);
            setB(rgb & 255);
        }

        public int getValue() {
            return r << 16 | g << 8 | b;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            constantCheck();
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            constantCheck();
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            constantCheck();
            this.b = b;
        }

        public boolean isImmutable() {
            return immutable;
        }

        private void constantCheck() {
            if (immutable)
                throw new IllegalStateException("The Color_ object is immutable");
        }

        public String toHexString() {
            return Integer.toHexString(getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Color_ color = (Color_) o;
            return r == color.r && g == color.g && b == color.b && immutable == color.immutable;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, g, b, immutable);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Color[0x");

            final String hex = Integer.toHexString(getValue());

            if (hex.length() < 6) sb.append('0');

            sb.append(hex);

            if (immutable)
                sb.append(", const");

            sb.append(']');

            return sb.toString();
        }

        public Color_ cloneColor_( ) {
            return new Color_( getValue(), isImmutable());
        }

        public static Color_ createRandomColor_( ) {
            return new Color_( (int) (Math.random() * 0xFFFFFF));
        }

        public static Color_ createVisibleRandomColor_( ) {
            Random random = new Random();

            final int[] values = new int[]{0x00, 0x40, 0x80, 0xC0, 0xFF};

            int r, g, b;

            do {
                r = values[random.nextInt(values.length)];
                g = values[random.nextInt(values.length)];
                b = values[random.nextInt(values.length)];
            } while (r <= 0x40 && g <= 0x40 && b <= 0x00);

            return new Color_( r, g, b);
        }

        public static Color_ of(int rgb) {
            return new Color_( rgb, true);
        }

        public static Color_ of(String hex) {
            return new Color_( hex, true);
        }
    }
}
