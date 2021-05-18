package main.part3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            String s = Integer.toHexString(0xff & b);
            s = (s.length() == 1) ? "0" + s : s;
            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }
}

