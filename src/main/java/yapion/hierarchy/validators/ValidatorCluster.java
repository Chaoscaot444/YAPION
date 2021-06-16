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

package yapion.hierarchy.validators;

import yapion.annotations.api.DeprecationInfo;
import yapion.hierarchy.types.YAPIONObject;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@DeprecationInfo(since = "0.25.3")
public final class ValidatorCluster {

    private final List<Validator> validatorList = new ArrayList<>();

    /**
     * Add a new {@link Validator} to this {@link ValidatorCluster}.
     *
     * @param validator the {@link Validator} to add
     */
    public ValidatorCluster add(Validator validator) {
        if (validator == null) return this;
        validatorList.add(validator);
        return this;
    }

    public enum ClusterMode {
        /**
         * If one or more are valid.
         */
        ANY_VALID,
        /**
         * If all are valid.
         */
        ALL_VALID,
        /**
         * If at least the inputted number are valid.
         */
        LEAST_VALID,
        /**
         * If at most the inputted number are valid.
         */
        MOST_VALID,
        /**
         * If n are valid.
         */
        SPECIFIC_VALID,
        /**
         * If none are valid.
         */
        NONE_VALID,
    }

    /**
     * Checks if the inputted {@link YAPIONObject} matches one
     * {@link Validator}. It uses the {@link ClusterMode#ANY_VALID}.
     *
     * @param yapionObject the {@link YAPIONObject} to validate
     * @return {@code true} if any {@link Validator} returns {@code true}, {@code false} otherwise
     */
    public boolean validate(YAPIONObject yapionObject) {
        return validate(ClusterMode.ANY_VALID, yapionObject);
    }

    /**
     * Checks if the inputted {@link YAPIONObject} matches one
     * {@link Validator}. It uses the {@link ClusterMode#LEAST_VALID}.
     *
     * @param yapionObject the {@link YAPIONObject} to validate
     * @param validNumber the {@link Validator} threshold
     * @return {@code true} if at least n {@link Validator}'s returned {@code true}, {@code false} otherwise
     */
    public boolean validate(YAPIONObject yapionObject, int validNumber) {
        return validate(ClusterMode.LEAST_VALID, yapionObject, validNumber);
    }

    /**
     * Checks if the inputted {@link YAPIONObject} matches the {@link Validator} with the specified {@link ClusterMode}.
     *
     * @param clusterMode the {@link ClusterMode} to use
     * @param yapionObject the {@link YAPIONObject} to validate
     * @return {@code true} if the specification of the {@code ClusterMode} are met
     * {@link ClusterMode#ANY_VALID} and {@link ClusterMode#LEAST_VALID} will behave the same
     * {@link ClusterMode#SPECIFIC_VALID} will behave as one and only one is valid
     * {@link ClusterMode#MOST_VALID} will behave as one valid or none valid
     */
    public boolean validate(ClusterMode clusterMode, YAPIONObject yapionObject) {
        return validate(clusterMode, yapionObject, 1);
    }

    /**
     * Checks if the inputted {@link YAPIONObject} matches the {@link Validator} with the specified {@link ClusterMode}.
     *
     * @param clusterMode the {@link ClusterMode} to use
     * @param yapionObject the {@link YAPIONObject} to validate
     * @param validNumber the {@link Validator} threshold
     * @return {@code true} if the specification of the {@link ClusterMode} are met, {@code false} otherwise
     */
    public boolean validate(ClusterMode clusterMode, YAPIONObject yapionObject, int validNumber) {
        int valid = 0;
        for (Validator validator : validatorList) {
            if (validator.validate(yapionObject)) valid++;
            // Optimization for some "clusterMode"'s
            switch (clusterMode) {
                case ANY_VALID:
                    if (valid > 0) return true;
                    break;
                case LEAST_VALID:
                    if (valid >= validNumber) return true;
                    break;
                case NONE_VALID:
                    if (valid != 0) return false;
                    break;
                case MOST_VALID:
                    if (valid > validNumber) return false;
                    break;
                default:
                    break;
            }
        }
        switch (clusterMode) {
            case ANY_VALID:
                return false; // valid > 0
            case LEAST_VALID:
                return valid >= validNumber;
            case ALL_VALID:
                return valid == validatorList.size();
            case NONE_VALID:
                return true; // valid == 0
            case MOST_VALID:
                return valid <= validNumber;
            case SPECIFIC_VALID:
                return valid == validNumber;
            default:
                return false;
        }
    }

}
