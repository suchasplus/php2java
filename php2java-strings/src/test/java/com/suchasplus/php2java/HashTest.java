package com.suchasplus.php2java;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.*;
import java.util.Random;

import static com.suchasplus.php2java.Hash.*;
import static org.junit.Assert.assertEquals;

/**
 * Hash Tester.
 *
 * @author suchasplus
 * @version 1.0
 */
public class HashTest {

    private Process ps = null;
    final private static String INPUT_STRING = "test string";
    final private static String PHP_ELF = "/usr/local/bin/php";

    final private static String FILE_PATH = "/tmp/php2java-hash.file";

    @Before
    public void before() throws Exception {
        File file = new File(FILE_PATH);
        FileUtils.writeStringToFile(
                file,
                generateString(
                        new Random(System.currentTimeMillis() * System.currentTimeMillis()),
                        FILE_PATH,
                        1024),
                "UTF-8");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: md5(String INPUT_STRING)
     */
    @Test
    public void testMd5() throws Exception {
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo md5('" + INPUT_STRING + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);

        System.out.println("md5 Hash:" + hash);

        assertEquals(hash, md5(INPUT_STRING));
    }

    /**
     * Method: md5_raw(String INPUT_STRING)
     */
    @Test
    @Ignore
    public void testMd5_raw() throws Exception {
    }

    /**
     * Method: md5_file(Path input_file_path)
     */
    @Test
    public void testMd5_file() throws Exception {
        File file = new File(FILE_PATH);
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo md5_file('" + FILE_PATH + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);

        System.out.println("md5 file Hash:" + hash);

        assertEquals(hash, md5_file(file));
    }

    /**
     * Method: md5_file_raw(Path input_file_path)
     */
    @Test
    @Ignore("UNKNOWN for raw compare")
    public void testMd5_file_raw() throws Exception {
    }

    /**
     * Method: sha1(String INPUT_STRING)
     */
    @Test
    public void testSha1() throws Exception {
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo sha1('" + INPUT_STRING + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);

        System.out.println("sha1 Hash:" + hash);

        assertEquals(hash, sha1(INPUT_STRING));
    }

    /**
     * Method: sha1_raw(String INPUT_STRING)
     */
    @Test
    @Ignore
    public void testSha1_raw() throws Exception {
    }

    /**
     * Method: sha1_file(Path input_file_path)
     */
    @Test
    public void testSha1_file() throws Exception {
        File file = new File(FILE_PATH);
        String[] cmd = new String[3];
        cmd[0] = PHP_ELF;
        cmd[1] = "-r";
        cmd[2] = "echo sha1_file('" + FILE_PATH + "');"; //java runtime exec会自动在两侧加双引号
        String hash = getExecRet(cmd);

        System.out.println("sha1 file Hash:" + hash);

        assertEquals(hash, sha1_file(file));
    }

    /**
     * Method: sha1_file_raw(Path input_file_path)
     */
    @Test
    @Ignore
    public void testSha1_file_raw() throws Exception {
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
            StringBuilder output = new StringBuilder();

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

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
} 
