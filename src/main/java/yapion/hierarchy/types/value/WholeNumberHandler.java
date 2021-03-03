/*
 * Copyright 2019,2020,2021 yoyosource
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yapion.hierarchy.types.value;

import yapion.utils.ReferenceFunction;

import java.math.BigInteger;
import java.util.Optional;

public class WholeNumberHandler {

    private WholeNumberHandler() {
        throw new IllegalStateException("Utility Class");
    }

    public static class ByteHandler implements ValueHandler<Byte> {

        @Override
        public String output(Byte aByte) {
            return aByte + NumberSuffix.BYTE.getSuffix();
        }

        @Override
        public Optional<Byte> preParse(String s) {
            return NumberSuffix.tryValueParse(s, NumberSuffix.BYTE);
        }

        @Override
        public Optional<Byte> parse(String s) {
            return Optional.empty();
        }

        @Override
        public long referenceValue(ReferenceFunction referenceFunction) {
            return referenceFunction.stringToReferenceValue("java.lang.Byte");
        }

    }

    public static class ShortHandler implements ValueHandler<Short> {

        @Override
        public String output(Short aShort) {
            return aShort + NumberSuffix.SHORT.getSuffix();
        }

        @Override
        public Optional<Short> preParse(String s) {
            return NumberSuffix.tryValueParse(s, NumberSuffix.SHORT);
        }

        @Override
        public Optional<Short> parse(String s) {
            return Optional.empty();
        }

        @Override
        public long referenceValue(ReferenceFunction referenceFunction) {
            return referenceFunction.stringToReferenceValue("java.lang.Short");
        }

    }

    public static class IntegerHandler implements ValueHandler<Integer> {

        @Override
        public String output(Integer integer) {
            return integer + "";
        }

        @Override
        public Optional<Integer> preParse(String s) {
            return NumberSuffix.tryValueParse(s, NumberSuffix.INTEGER);
        }

        @Override
        public Optional<Integer> parse(String s) {
            return NumberSuffix.trySuffixLessValueParse(s, NumberSuffix.INTEGER);
        }

        @Override
        public long referenceValue(ReferenceFunction referenceFunction) {
            return referenceFunction.stringToReferenceValue("java.lang.Integer");
        }

    }

    public static class LongHandler implements ValueHandler<Long> {

        @Override
        public String output(Long aLong) {
            return aLong + NumberSuffix.LONG.getSuffix();
        }

        @Override
        public Optional<Long> preParse(String s) {
            return NumberSuffix.tryValueParse(s, NumberSuffix.LONG);
        }

        @Override
        public Optional<Long> parse(String s) {
            return NumberSuffix.trySuffixLessValueParse(s, NumberSuffix.LONG);
        }

        @Override
        public long referenceValue(ReferenceFunction referenceFunction) {
            return referenceFunction.stringToReferenceValue("java.lang.Long");
        }

    }

    public static class BigIntegerHandler implements ValueHandler<BigInteger> {

        @Override
        public String output(BigInteger bigInteger) {
            return bigInteger + NumberSuffix.BIG_INTEGER.getSuffix();
        }

        @Override
        public Optional<BigInteger> preParse(String s) {
            return NumberSuffix.tryValueParse(s, NumberSuffix.BIG_INTEGER);
        }

        @Override
        public Optional<BigInteger> parse(String s) {
            return NumberSuffix.trySuffixLessValueParse(s, NumberSuffix.BIG_INTEGER);
        }

        @Override
        public long referenceValue(ReferenceFunction referenceFunction) {
            return referenceFunction.stringToReferenceValue("java.math.BigInteger");
        }

    }

}
