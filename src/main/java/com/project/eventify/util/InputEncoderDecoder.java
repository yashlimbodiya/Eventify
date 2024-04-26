package com.project.eventify.util;


import java.util.Base64;

public class InputEncoderDecoder {


    //encode() return encrypted value of provided input string
    public static String encode(String input) {
        byte[] encryptedInputByteArray = Base64.getEncoder().encode(input.getBytes());
        return new String(encryptedInputByteArray);
    }

    //decode() return decrypted value of provided encrypted input string
    public static String decode(String encrytedInput) {
        return new String(Base64.getDecoder().decode(encrytedInput));
    }
}
