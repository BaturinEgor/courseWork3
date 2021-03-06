package ua.khpi.baturin.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ua.khpi.baturin.validator.annotations.loginconfirm.ConfirmLogin;
import ua.khpi.baturin.validator.annotations.passwordconfirm.ConfirmPasswords;

@ConfirmPasswords
@ConfirmLogin
public class UserDto {

    private Long id;

    @NotNull(message = "Логин не может быть пустым")
    @Pattern(regexp = "^[A-Za-z0-9_]{8,32}$", message = "логин должен состоять только из латинских букв и иметь длинну 8-32 символов")
    private String login;

    @NotNull(message = "Пароль не должен быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}$", message = "пароль должен состоять только из латинских букв и иметь длинну 8-32 символов")
    private String password;

    @NotNull(message = "Подтвердите пароль")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,32}$", message = "пароль должен состоять только из латинских букв и иметь длинну 8-32 символов")
    private String confirmPassword;

    @NotNull(message = "Укажите своё имя")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+", message = "Не верно введено имя")
    private String firstName;

    @NotNull(message = "Укажите свою фамилию")
    @Size(min = 1, max = 100)
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+", message = "Не верно введена фамилия")
    private String lastName;

    private String role;

    private String message;

    public UserDto() {
    }

    public UserDto(Long id,
            @NotNull(message = "Login required") @Pattern(regexp = "^[A-Za-z0-9_]{8,32}$", message = "Login length must be between 8 and 32 symbols and contain only letters of the Latin alphabet") String login,
            @NotNull(message = "Password required") @Pattern(regexp = "^[a-zA-Z0-9]{8,32}$", message = "Password length must be between 8 and 32 symbols and contain only digits and Latin letters") String password,
            @NotNull(message = "Confirm password") @Pattern(regexp = "^[a-zA-Z0-9]{8,32}$", message = "Password length must be between 8 and 32 symbols and contain only digits and Latin letters") String confirmPassword,
            @NotNull(message = "Email required") String email,
            @NotNull(message = "First name required") @Size(min = 1, max = 100) @Pattern(regexp = "^[a-zA-Zа-яА-Я]+", message = "Invalid first name") String firstName,
            @NotNull(message = "Last name required") @Size(min = 1, max = 100) @Pattern(regexp = "^[a-zA-Zа-яА-Я]+", message = "Invalid last name") String lastName,
            @NotNull(message = "Birthday must match pattern yyyy-mm-dd") String birthday,
            @NotNull(message = "Choose role") String role, String message) {
        super();
        this.id = id;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.message = message;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserDto other = (UserDto) obj;
        if (confirmPassword == null) {
            if (other.confirmPassword != null) {
                return false;
            }
        } else if (!confirmPassword.equals(other.confirmPassword)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!login.equals(other.login)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
            return false;
        }
        return true;
    }

}
