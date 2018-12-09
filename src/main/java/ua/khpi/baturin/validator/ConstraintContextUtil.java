package ua.khpi.baturin.validator;

import javax.validation.ConstraintValidatorContext;

public class ConstraintContextUtil {
    public static void addConstraintToField(String field, String message,
            ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field).addConstraintViolation();
    }
}
