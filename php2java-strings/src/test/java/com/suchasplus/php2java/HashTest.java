package com.suchasplus.php2java;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.suchasplus.php2java.Hash.*;
import static org.junit.Assert.assertEquals;

/**
 * Hash Tester.
 *
 * @author suchasplus
 * @version 1.0
 */
public class HashTest
{

    private Process ps = null;
    final private static String input = "test string";
    final private static String PHP_ELF = "/usr/local/bin/php";

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: md5(String input)
     */
    @Test
    public void testMd5() throws Exception {
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo md5('" + input + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);
        assertEquals(hash, md5(input));
    }

    /**
     * Method: md5_raw(String input)
     */
    @Test
    public void testMd5_raw() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: md5_file(File input_file)
     */
    @Test
    @Ignore
    public void testMd5_file() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: md5_file_raw(File input_file)
     */
    @Test
    @Ignore
    public void testMd5_file_raw() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: sha1(String input)
     */
    @Test
    public void testSha1() throws Exception {
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo sha1('" + input + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);
        assertEquals(hash, sha1(input));
    }

    /**
     * Method: sha1_raw(String input)
     */
    @Test
    public void testSha1_raw() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: sha1_file(File input_file)
     */
    @Test
    @Ignore
    public void testSha1_file() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: sha1_file_raw(File input_file)
     */
    @Test
    @Ignore
    public void testSha1_file_raw() throws Exception {
        //TODO: Test goes here...
    }


    /**
     * Method: byteArray2StringHex(byte[] byteArray)
     */
    @Test
    public void testByteArray2StringHex() throws Exception {
        //TODO: Test goes here...
/* 
try { 
   Method method = Hash.getClass().getMethod("byteArray2StringHex", byte[].class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }


    private String getExecRet(String[] cmd) {

        try {
            ps = Runtime.getRuntime().exec(cmd);

            BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            int read;
            char[] buffer = new char[102400];
            StringBuffer output = new StringBuffer();

            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            // Waits for the command to finish.
            ps.waitFor();

            ps.destroy();

            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ps.destroy();
        }

        return null;
    }
} 
