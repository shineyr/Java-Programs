package com.ria.gradle.collections;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Common use cases of String in Java
 */
public class StringExample {
    public static void main(String[] args) {
        /**
         * 1. String split(...)
         */
        String str = "A,B,C,D";

        String[] strArray = str.split(",");

        System.out.println(Arrays.toString(strArray));

        /**
         * Pattern.split(...)
         */

        Pattern pattern = Pattern.compile(",");
        String[] strArray2 = pattern.split(str);

        System.out.println(Arrays.toString(strArray2));


        /**
         * String.join(...)
         */
        String[] elements = {"Learn", "to", "be", "happy"};
        String strElement = String.join(" ", elements);

        System.out.println(strElement);

        /**
         * Byte to String
         */
        String data = "Being a happier programmer.";
        byte[] byteArray = data.getBytes();

        String strFromByte = new String(byteArray);
        String strFromByte2 = new String(byteArray, Charset.defaultCharset());

        System.out.println("byteArray: " + byteArray); // byteArray: [B@7852e922
        System.out.println("Arrays.toString(byteArray): " + Arrays.toString(byteArray)); // Arrays.toString(byteArray): [66, 101, 105, 110, 103, 32, 97, 32, 104, 97, 112, 112, 105, 101, 114, 32, 112, 114, 111, 103, 114, 97, 109, 109, 101, 114, 46]

        System.out.println("strFromByte: " + strFromByte); // strFromByte: Being a happier programmer.

        System.out.println("strFromByte2: " + strFromByte2);// strFromByte2: Being a happier programmer.

        System.out.println("Charset.defaultCharset(): " + Charset.defaultCharset()); // Charset.defaultCharset(): UTF-8

    }
}
