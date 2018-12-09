package ua.khpi.baturin.validator.annotations.dateconfirm;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.validator.ConstraintContextUtil;

public class DateConstraintValidator
        implements ConstraintValidator<ConfirmDate, UserDto> {
    @Override
    public void initialize(ConfirmDate confirmDate) {

    }

    @Override
    public boolean isValid(UserDto userDto,
            ConstraintValidatorContext constraintValidatorContext) {
        String date = userDto.getBirthday();
        Date birthday = null;
        try {
            birthday = Date.valueOf(date);
            Date currentDate = new Date(
                    Calendar.getInstance().getTime().getTime());
            if (birthday.compareTo(currentDate) > 0) {
                ConstraintContextUtil.addConstraintToField("birthday",
                        "Date greater than current",
                        constraintValidatorContext);
                return false;
            }
        } catch (IllegalArgumentException e) {
            ConstraintContextUtil.addConstraintToField("birthday",
                    "Invalid date, date must match pattern \"yyy-mm-dd\"",
                    constraintValidatorContext);
            return false;
        }
        return true;
    }
}
