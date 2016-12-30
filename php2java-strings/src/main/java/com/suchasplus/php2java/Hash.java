package com.suchasplus.php2java;


import java.io.File;
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
            return MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
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
        byte[] hash = md5_file_raw(input_file);
        return byteArray2StringHex(hash);
    }

    /**
     * Calculates the md5 hash digest with raw binary format of a given file
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc1321">RSA Data Security, Inc. MD5 Message-Digest Algorithm</a>
     * @param input_file The filename
     * @return byte[] the digest in raw binary format with a length of 16
     */
    public static byte[] md5_file_raw(File input_file) {
        //fixme
        byte[] hash = {};
        return hash;
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
            return MessageDigest.getInstance("SHA-1").digest(input.getBytes("UTF-8"));
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
        byte[] hash = sha1_file_raw(input_file);
        return byteArray2StringHex(hash);
    }

    /**
     * Calculate the sha1 hash digest with raw binary format of a file
     *
     * @see <a href="http://www.faqs.org/rfcs/rfc3174">US Secure Hash Algorithm 1</a>
     * @param input_file The filename
     * @return raw binary format with a length of 20
     */
    public static byte[] sha1_file_raw(File input_file) {
        //fixme
        byte[] hash = {};
        return hash;
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
}
