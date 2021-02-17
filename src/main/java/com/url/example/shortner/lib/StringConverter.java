package com.url.example.shortner.lib;

public class StringConverter {
    public static String encodeString(String s) {
        String encodedString = "";
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i + 1 < s.length()
                    && s.charAt(i)
                    == s.charAt(i + 1)) {
                i++;
                count++;
            }
            encodedString = encodedString + s.charAt(i)
                    + "" + count;
        }
        return encodedString;
    }
}
