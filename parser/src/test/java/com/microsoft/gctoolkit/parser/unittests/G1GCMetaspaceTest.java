// Copyright (c) Microsoft Corporation.
// Licensed under the MIT License.
package com.microsoft.gctoolkit.parser.unittests;

import com.microsoft.gctoolkit.parser.diary.TestLogFile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class G1GCMetaspaceTest extends ParserTest {

    @Test
    public void countMetaspaceRecordsTest() {
        int i = 0;
        System.out.println("==========> countMetaspaceRecordsTest <=============");
        System.out.println(Path.of("."));
        System.out.println();
        for (String name : details) {
            try {
                Path path = new TestLogFile("g1gc/details/" + name).getFile().toPath();
                System.out.println(path.toString());
                TestResults testResults = testRegionalSingleLogFile(path);
                assertEquals(permGenMetaspaceCounts[i++], testResults.getMetaSpaceRecordCount(), "Meta or Perm space record count mismatch: ");
            } catch (IOException ioe) {
                fail(ioe.getMessage());
            }
        }

    }

    private String[] details = {
            "gc.log.4.31_1_aug_sep_current.zip"
    };

    private int[] permGenMetaspaceCounts = {
            15
    };
}
