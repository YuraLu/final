package by.epam.lukashevich.domain.util.validator.user.impl;

import by.epam.lukashevich.domain.util.manager.UtilManager;
import by.epam.lukashevich.domain.util.validation.UserValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserValidatorImplTest {

    private final UserValidator validator = UtilManager.getInstance().getUserValidator();

    @Test
    public void testValidate_shortUserName() {

        boolean result = validator.validate("s", "pass123");

        assertFalse(result);
    }

    @Test
    public void testValidate_incorrect_userNameEnd() {
        boolean result = validator.validate("incorrect?", "pass123");

        assertFalse(result);
    }

    @Test
    public void testValidate_longUserName() {
        boolean result = validator.validate("LongLongLongUserName", "pass123");

        assertFalse(result);
    }

    @Test
    public void testValidatePassword_underscoreAltPasswordBeginning() {

        boolean result = validator.validate("admin", "_wrongPassword");

        assertFalse(result);
    }


    @Test
    public void validateWithTwoParams_longPassword_false() {
        //given
        //when
        boolean result = validator.validate("admin",
                "PasswordPasswordPasswordPassword");
        //then
        assertFalse(result);
    }

    @Test
    public void validateWithTwoParams_validParameters_true() {
        //given
        //when
        boolean result = validator.validate("admin", "pass123");
        //then
        assertTrue(result);
    }


    @Test
    public void validate_changePassword_false() {

        boolean result = validator.validateNewPassword("pass", "pass", "pass");

        assertFalse(result);
    }

    @Test
    public void validate_changePassword_true() {

        boolean result = validator.validateNewPassword("pass123", "pass1234", "pass1234");

        assertTrue(result);
    }


    @Test
    public void validate_userWithAllParameters_true() {
        boolean result = validator.validate("admin",
                "pass123", "pass123", "Admin", "admin@gmail.com");
        assertTrue(result);
    }

    @Test
    public void testValidate_badEmail_false() {
        boolean result = validator.validate("admin",
                "pass123", "pass123", "Admin", "admin?@gmail.com");

        assertFalse(result);
    }


    @Test
    public void testValidate_correctParams_true() {
        boolean result = validator.validate("admin",
                "pass123", "pass123", "Admin", "admin@gmail.com");

        assertTrue(result);
    }
}