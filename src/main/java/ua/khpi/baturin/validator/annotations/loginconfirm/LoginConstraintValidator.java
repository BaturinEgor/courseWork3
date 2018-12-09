package ua.khpi.baturin.validator.annotations.loginconfirm;

import java.net.URI;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ua.khpi.baturin.dto.UserDto;
import ua.khpi.baturin.entity.User;
import ua.khpi.baturin.service.contract.UserService;
import ua.khpi.baturin.validator.ConstraintContextUtil;

public class LoginConstraintValidator
        implements ConstraintValidator<ConfirmLogin, UserDto> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(ConfirmLogin confirmDate) {

    }

    @Override
    public boolean isValid(UserDto userDto,
            ConstraintValidatorContext constraintValidatorContext) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder
                .fromCurrentRequestUri();
        URI newUri = builder.build().toUri();
        String uri = newUri.toString();

        User userByLogin = userService.findByLogin(userDto.getLogin());
        if (userByLogin != null) {
            if (uri.contains("update")) {
                return true;
            } else {
                ConstraintContextUtil.addConstraintToField("message",
                        "User with given login is already exist",
                        constraintValidatorContext);
                return false;
            }
        }
        return true;
    }
}
