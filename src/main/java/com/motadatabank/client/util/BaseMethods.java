package com.motadatabank.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseMethods
{

    public static boolean checkSuccessfulLogin(String input)
    {

        return input.trim().equals("Logged In Successfully");

    }

    public static boolean isActive(String input)
    {

        return input.trim().equals("Session Active");

    }

    public static String validate(String input, Predicate<String> conditions, BufferedReader reader, String message, int limit) throws IOException
    {

        if (input != null)
        {
            if (!input.trim().isEmpty() && conditions.test(input))
            {

                return input;

            }

            else
            {

                if (limit != 0)
                {
                    System.out.print("\n" + message);

                    return validate(reader.readLine(), conditions, reader, message, --limit);

                }
                else
                {

                    return null;

                }
            }

        }
        else
        {

            return null;

        }
    }

    public static boolean isNumber(String input)
    {

        Pattern pattern = Pattern.compile("^[0-9]+$");

        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public static boolean isValidAmount(String input)
    {

        return isNumber(input) && Integer.parseInt(input) > 0;

    }

    public static boolean isValidPassword(String password)
    {

        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        return m.matches();

    }

    public static boolean isValidName(String name)
    {

        String regex = "^[A-Za-z]+$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(name);

        return m.matches();
    }

    public static boolean isValidEmail(String email)
    {

        String pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(email);

        return m.matches();

    }

    public static boolean isValidContactNumber(String phone)
    {

        if (phone == null)
            return false;

        Pattern pattern = Pattern.compile("^\\d{10}$");

        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();

    }


}
