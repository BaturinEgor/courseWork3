package ua.khpi.baturin.validator.annotations.dateconfirm;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DateConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmDate {

    String message() default "Invalid date, date must match pattern \"yyy-mm-dd\"";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
