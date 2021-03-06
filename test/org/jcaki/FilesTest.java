package org.jcaki;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

public class FilesTest {
    @Test
    public void MD5CalculationTest() throws IOException {
        assertEquals("873362e429c261e3596ad1d387ad152e",
                Bytes.toHex(Files.calculateMD5(new File("test/file_for_md5.txt"))));
    }

    @Ignore("Not a unit test")
    @Test
    public void fileAppendTest() throws IOException {
        Files.appendFiles(new File("apended.txt"), new File("test/file_for_md5.txt"), new File("test/multi_line_text_file.txt"));
    }

    @Ignore("Not a unit test")    
    @Test
    public void testHexDump() throws IOException {
        Files.hexDump(new File("test/multi_line_text_file.txt"), -1);
    }

}
