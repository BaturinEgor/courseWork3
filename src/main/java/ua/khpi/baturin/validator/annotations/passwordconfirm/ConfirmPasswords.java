package ua.khpi.baturin.validator.annotations.passwordconfirm;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordsConstraintValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPasswords {
    String message() default "Passwords are not the same";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
