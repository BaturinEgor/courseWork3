package ua.khpi.baturin.validator.annotations.passwordconfirm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.validator.ConstraintContextUtil;

public class PasswordsConstraintValidator
        implements ConstraintValidator<ConfirmPasswords, UserDto> {
    @Override
    public void initialize(ConfirmPasswords confirmPasswords) {

    }

    @Override
    public boolean isValid(UserDto userDto,
            ConstraintValidatorContext constraintValidatorContext) {
        if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return true;
        }
        ConstraintContextUtil.addConstraintToField("message",
                "Passwords are not the same", constraintValidatorContext);
        return false;
    }
}
