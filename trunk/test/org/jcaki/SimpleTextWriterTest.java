/*
 *
 * Copyright (c) 2008, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jcaki;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.After;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleTextWriterTest {

    File tmpDir;
    File tmpFile;

    @Before
    public void before() {
        tmpDir = new File(Systems.getJavaIoTmpDir() + "/jcaki");
        if (!tmpDir.exists())
            tmpDir.mkdir();
        tmpFile = new File(tmpDir, "jcaki.txt");
    }

    @After
    public void after() {
        tmpFile.delete();
    }

    @Test
    public void WriteStringTest() throws IOException {
        new SimpleTextWriter(tmpFile).write("Hello World!");
        Assert.assertEquals(new SimpleTextReader(tmpFile).asString(), "Hello World!");
        new SimpleTextWriter(tmpFile).write(null);
        Assert.assertEquals(new SimpleTextReader(tmpFile).asString(), "");
        new SimpleTextWriter(tmpFile).write("");
        Assert.assertEquals(new SimpleTextReader(tmpFile).asString(), "");
    }

    @Test
    public void WriteStringKeepOpenTest() throws IOException {
        SimpleTextWriter sfw = new SimpleTextWriter
                .Builder(tmpFile)
                .keepOpen()
                .build();
        sfw.write("Hello");
        sfw.write("Merhaba");
        sfw.write("");
        sfw.write(null);
        IOs.closeSilently(sfw);
        Assert.assertEquals("HelloMerhaba", new SimpleTextReader(tmpFile).asString());

    }

    @Test (expected = IOException.class)
    public void keepOpenExcepionTest() throws IOException {
        SimpleTextWriter sfw = new SimpleTextWriter
                .Builder(tmpFile)
                .build();
        sfw.write("Hello");
        sfw.write("Now it will throw an exception..");
    }

    @Test
    public void WriteMultiLineStringTest() throws IOException {
        List<String> strs = new ArrayList<String>(Arrays.asList("Merhaba", "Dunya", ""));
        new SimpleTextWriter(tmpFile).writeLines(strs);
        List<String> read = new SimpleTextReader(tmpFile).asStringList();
        for (int i = 0; i < read.size(); i++) {
            Assert.assertEquals( read.get(i), strs.get(i));
        }
    }
}
