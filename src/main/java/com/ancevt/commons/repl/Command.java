/**
 * Copyright (C) 2022 the original author or authors.
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
package com.ancevt.commons.repl;



import com.ancevt.util.args.Args;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Consumer;
import java.util.function.Function;

public class Command {

    private final String commandWord;
    private final String description;

    @Getter
    private final Consumer<Args> function;

    public Command(String commandWord, Consumer<Args> func) {
        this.commandWord = commandWord;
        this.description = "";
        this.function = func;
    }

    public Command(String commandWord, String description, Consumer<Args> func) {
        this.commandWord = commandWord;
        this.description = description;
        this.function = func;
    }

    public void execute(String commandLine) {
        Args args = Args.of(commandLine);
        args.skip();
        function.accept(args);
    }

    public String getDescription() {
        return description;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public static Command of(String commandWord, Consumer<Args> func) {
        return new Command(commandWord, func);
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandWord='" + commandWord + '\'' +
                ", description='" + description + '\'' +
                ", function=" + function +
                '}';
    }
}
