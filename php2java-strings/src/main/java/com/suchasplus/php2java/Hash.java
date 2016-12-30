package com.suchasplus.php2java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * using java.security.Security.getProviders()
 * which contains MD2 MD5 SHA-1 SHA-256 SHA-384 SHA-512
 *
 */
public class Hash {

    final static String MD5 = "MD5";
    final static String SHA1 = "SHA-1";
    final static String SHA256 = "SHA-256";
    final static String SHA384 = "SHA-384";
    final static String SHA512 = "SHA-512";

    /**
     * Calculates the md5 hash of a given string
     *
     * @link http://www.php.net/manual/en/function.md5.php
     * @see <a href="http://www.faqs.org/rfcs/rfc1321">RSA Data Security, Inc. MD5 Message-Digest Algorithm</a>
     *
     * @param input The string
     * @return String Returns the hash as a 32-character hexadecimal number
     */
    public static String md5(String input) {
        byte[] hash = md5_raw(input);
        return byteArray2StringHex(hash);
    }

    /**
     * Calculate the md5 hash digest with raw binary format of a string
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc1321">RSA Data Security, Inc. MD5 Message-Digest Algorithm</a>
     * @param input The String
     * @return byte[] the digest in raw binary format with a length of 16
     */
    public static byte[] md5_raw(String input) {
        try {
            return MessageDigest.getInstance(MD5).digest(input.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Security.getProviders() DO NOT contains MD5 ?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 NOT supported ?", e);
        }
    }

    /**
     * Calculates the md5 hash of a given file
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc1321">RSA Data Security, Inc. MD5 Message-Digest Algorithm</a>
     * @param input_file The filename
     * @return a 32-character hexadecimal number of String
     */
    public static String md5_file(File input_file) {
        byte[] hash = md5_file_raw(Paths.get(input_file.getPath()));
        return byteArray2StringHex(hash);
    }

    /**
     * Calculates the md5 hash digest with raw binary format of a given file
     *
     * File example : md5_file_raw(Paths.get(input_file.getPath()));
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc1321">RSA Data Security, Inc. MD5 Message-Digest Algorithm</a>
     * @param input_file_path The file path
     * @return byte[] the digest in raw binary format with a length of 16
     */
    public static byte[] md5_file_raw(Path input_file_path) {
        try {
            byte[] data = getFileContent2Byte(input_file_path);
            return MessageDigest.getInstance(MD5).digest(data);
        }  catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Security.getProviders() DO NOT contains MD5 ?", e);
        }
    }

    /**
     * Calculate the sha1 hash of a string
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc3174">US Secure Hash Algorithm 1</a>
     * @param input The String
     * @return a 40-character hexadecimal number of String
     */
    public static String sha1(String input) {
        byte[] hash = sha1_raw(input);
        return byteArray2StringHex(hash);
    }

    /**
     * Calculate the sha1 hash digest with raw binary format of a string
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc3174">US Secure Hash Algorithm 1</a>
     * @param input The String
     * @return raw binary format with a length of 20
     */
    public static byte[] sha1_raw(String input) {
        try {
            return MessageDigest.getInstance(SHA1).digest(input.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Security.getProviders() DO NOT contains SHA-1 ?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 NOT supported ?", e);
        }
    }

    /**
     * Calculate the sha1 hash of a file
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc3174">US Secure Hash Algorithm 1</a>
     * @param input_file The filename
     * @return a 40-character hexadecimal number of String
     */
    public static String sha1_file(File input_file) {
        byte[] hash = sha1_file_raw(Paths.get(input_file.getPath()));
        return byteArray2StringHex(hash);
    }

    /**
     * Calculate the sha1 hash digest with raw binary format of a file
     *
     * File example : sha1_file_raw (  );
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc3174">US Secure Hash Algorithm 1</a>
     * @param input_file_path The filename
     * @return raw binary format with a length of 20
     */
    public static byte[] sha1_file_raw(Path input_file_path) {
        try {
            byte[] data = getFileContent2Byte(input_file_path);
            return MessageDigest.getInstance(SHA1).digest(data);
        }  catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Security.getProviders() DO NOT contains MD5 ?", e);
        }
    }

    /**
     * transform byte array to hex String
     *
     * @param byteArray raw hash digest
     * @return String hex based hash string
     */
    private static String byteArray2StringHex(byte[] byteArray) {
        StringBuilder hex = new StringBuilder(byteArray.length * 2);

        for (byte b : byteArray) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }

    private static byte[] getFileContent2Byte(Path file_path) {
        try {
            return Files.readAllBytes(file_path);
        } catch(IOException e) {
            throw new RuntimeException("File " + file_path + " NOT Accessible", e);
        }
    }
}
