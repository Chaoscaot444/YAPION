// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020 yoyosource

package test;

import yapion.annotations.YAPIONDeserializeType;
import yapion.annotations.YAPIONLoad;
import yapion.annotations.YAPIONOptimize;
import yapion.annotations.YAPIONSave;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@YAPIONSave
@YAPIONLoad
public class Test {

    public transient volatile double i = 10.0;

    @YAPIONDeserializeType(type = ArrayList.class)
    private final List<String> strings = new ArrayList<>();
    private final String[] strings2 = new String[4];
    private String[][] strings3 = new String[3][3];
    private Hugo[][] hugos = new Hugo[4][4];

    private static final int d = 0;

    @YAPIONDeserializeType(type = HashMap.class)
    private final Map<String, String> stringStringMap = new HashMap<>();
    @YAPIONDeserializeType(type = HashMap.class)
    private final Map<Hugo, String> hugoStringMap = new HashMap<>();
    private final HashMap<String, String> hashMap = new HashMap<>();
    private final LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();

    {
        strings.add("Hello WOrld");
        strings.add("Hello WOrld!");
        strings.add("Hello WOrld!!");
        strings.add("Hello WOrld!!!");
        strings.add("Hello WOrld!!!!");

        strings2[0] = "TEST";
        strings3[0][0] = "TEST";

        hugos[0][0] = new Hugo();
        hugos[1][1] = new Hugo(this);
        hugos[2][2] = new Hugo();
        hugos[3][3] = new Hugo(this);


        stringStringMap.put("Hello", "Hello2");
        stringStringMap.put("Hello2", "Hello2");
        stringStringMap.put("Hello3", "Hello2");
        stringStringMap.put("Hello4", "Hello2");
        stringStringMap.put("Hello5", "Hello2");
    }

    public Hugo hugo = new Hugo(this);

    private FileInputStream fileInputStream;

    private final HashMap<Hugo, String> hugoStringHashMap = new HashMap<>();

    {
        hugoStringHashMap.put(hugo, "");
        hugoStringHashMap.put(new Hugo(), "EMPTY");

        try {
            fileInputStream = new FileInputStream(new File("~Chess/.server/.6c545e8a37d74f3e82fec4523e95228c-B33DFB13.ac"));
        } catch (IOException e) {

        }
    }

    public String tru = "true";

    public Hugo hugo2 = hugo;

    private final Double test = 0.0;

    @YAPIONSave
    @YAPIONLoad
    private class Hugo {

        public int i = 0;

        @YAPIONOptimize
        public Test t;

        public Hugo2 hugo2 = new Hugo2();

        private Hugo() {

        }

        public Hugo(Test t) {
            this.t = t;
        }

        public int getI() {
            return i;
        }

        @YAPIONSave
        @YAPIONLoad
        private class Hugo2 {

            private final int i = 0;

        }

    }
}