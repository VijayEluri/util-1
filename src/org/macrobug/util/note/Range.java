package org.macrobug.util.note;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Range {
	long min() default Long.MIN_VALUE;
	long max() default Long.MAX_VALUE;
}
