// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020 yoyosource

package yapion.parser;

import yapion.exceptions.utils.YAPIONArrayIndexOutOfBoundsException;
import yapion.exceptions.parser.YAPIONParserException;
import yapion.hierarchy.Type;

public class TypeStack {

    private int pointer = -1;
    private static final int depth = 65536;
    private Type[] stack = new Type[depth];
    private long[] timeStack = new long[depth];

    public void push(Type type) {
        pointer++;
        stack[pointer] = type;
        timeStack[pointer] = System.nanoTime();
    }

    public Type pop(Type type) {
        if (empty()) {
            throw new YAPIONArrayIndexOutOfBoundsException();
        }
        timeStack[pointer] = 0;
        Type current = stack[pointer];
        if (current != type) {
            throw new YAPIONParserException();
        }
        pointer--;
        return current;
    }

    public Type peek() {
        if (empty()) {
            throw new YAPIONArrayIndexOutOfBoundsException();
        }
        return stack[pointer];
    }

    public long peekTime() {
        if (empty()) {
            throw new YAPIONArrayIndexOutOfBoundsException();
        }
        return System.nanoTime() - timeStack[pointer];
    }

    private boolean empty() {
        return pointer == -1;
    }

    public boolean isEmpty() {
        return pointer == -1;
    }

    public boolean isNotEmpty() {
        return pointer != -1;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i <= pointer; i++) {
            if (i != 0) {
                st.append(", ");
            }
            st.append(stack[i].getName());
        }
        return "TypeStack[" + st.toString() + "]";
    }

}