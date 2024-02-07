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
package com.ancevt.commons.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class Cyclic<T> {
    private final Collection<T> elements;
    private Iterator<T> iterator;


    @SafeVarargs
    private Cyclic(T... elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        this.elements = Arrays.asList(elements);
        this.iterator = this.elements.iterator();
    }

    private Cyclic(Collection<T> elements) {
        if (elements == null || elements.isEmpty()) {
            throw new IllegalArgumentException("Collection must not be null or empty");
        }

        this.elements = elements;
        this.iterator = elements.iterator();
    }

    public T next() {
        if (!iterator.hasNext()) {
            iterator = elements.iterator();
        }
        return iterator.next();
    }

    public T prev() {
        if (!iterator.hasNext()) {
            iterator = elements.iterator();
        }

        T current = iterator.next();
        T previous = null;

        while (iterator.hasNext()) {
            previous = current;
            current = iterator.next();
        }

        iterator = elements.iterator();
        while (iterator.hasNext() && !iterator.next().equals(previous)) {}

        return previous;
    }

    public static <T> Cyclic<T> of(T... elements) {
        return new Cyclic<>(elements);
    }

    public static <T> Cyclic<T> of(Collection<T> elements) {
        return new Cyclic<>(elements);
    }

}
