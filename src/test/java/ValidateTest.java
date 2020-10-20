import org.bohdan.web.Validation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class ValidateTest {

    private boolean actualBoolean;
    private String actualStr;

    @Test
    public void validateLatinTest() {

        actualStr = "Daniel";
        actualBoolean = Validation.validateLatin(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "Da";
        actualBoolean = Validation.validateLatin(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "123456";
        actualBoolean = Validation.validateLatin(actualStr);

        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validateCyrillicTest() {

        actualStr = "Даниил";
        actualBoolean = Validation.validateCyrillic(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "Лот";
        actualBoolean = Validation.validateCyrillic(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "123456";
        actualBoolean = Validation.validateCyrillic(actualStr);

        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validateLoginTest() {

        //true
        actualStr = "example@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "example.example@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "example1234@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "example_example@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "example-example@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertTrue(actualBoolean);

        //false
        actualStr = "example@.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "example@";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "@gmail.com";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "123456";
        actualBoolean = Validation.validateLogin(actualStr);

        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validatePhoneTest() {
        //true
        actualStr = "(123) 123-4567";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "(123) 123 4567";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "(123) 6436040";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "1234567890";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "123 6436040";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "123 643 1234";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "123 643-1234";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertTrue(actualBoolean);

        //false
        actualStr = "asdf";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "123asdf";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertFalse(actualBoolean);

        actualStr = "asdf123";
        actualBoolean = Validation.validatePhone(actualStr);

        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validatePasswordTest() {
        actualStr = "1111";
        actualBoolean = Validation.validatePassword(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "asdf";
        actualBoolean = Validation.validatePassword(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "asdf123";
        actualBoolean = Validation.validatePassword(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "123asdf";
        actualBoolean = Validation.validatePassword(actualStr);

        Assert.assertTrue(actualBoolean);

        actualStr = "asdf1234asdf1234asdf1234";
        actualBoolean = Validation.validatePassword(actualStr);

        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validateFloatTest() {
        float actualFloat = 600.32f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 10000);
        Assert.assertTrue(actualBoolean);

        actualFloat = 8400.3f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 10000);
        Assert.assertTrue(actualBoolean);

        actualFloat = 1500f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 10000);
        Assert.assertTrue(actualBoolean);

        actualFloat =  .89f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 10000);
        Assert.assertFalse(actualBoolean);

        actualFloat =  7000f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 5000);
        Assert.assertFalse(actualBoolean);

        actualFloat =  -8900f;

        actualBoolean = Validation.validateFloat(actualFloat, 300, 10000);
        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validateIntTest() {
        int actualInt =  5;

        actualBoolean = Validation.validateInt(actualInt, 1, 10);
        Assert.assertTrue(actualBoolean);

        actualInt =  -1;

        actualBoolean = Validation.validateInt(actualInt, 1, 10);
        Assert.assertFalse(actualBoolean);
    }

    @Test
    public void validateDateTest() {
        Date addDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(addDate);

        c.add(Calendar.DATE, 5);
        addDate = c.getTime();

        actualBoolean = Validation.validateDate(addDate);
        Assert.assertTrue(actualBoolean);

        addDate = new Date();

        c = Calendar.getInstance();
        c.setTime(addDate);

        c.add(Calendar.DATE, -5);
        addDate = c.getTime();

        actualBoolean = Validation.validateDate(addDate);
        Assert.assertFalse(actualBoolean);
    }
}
