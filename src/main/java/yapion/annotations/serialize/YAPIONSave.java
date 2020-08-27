// SPDX-License-Identifier: Apache-2.0
// YAPION
// Copyright (C) 2019,2020 yoyosource

package yapion.annotations.serialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation describes a Field or Type to be saved in the process
 * of deserialization. Saving means that the Field or Type annotated by
 * this annotation will be written into the YAPION object. If this
 * annotation annotates a Type it is considered as a class description.
 *
 * The context describes the state in which the
 * {@link yapion.serializing.YAPIONSerializer} should be in for this
 * annotation to take effect.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface YAPIONSave {
    String context() default "";
}