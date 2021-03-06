/*
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codefollower.lealone.test.jdbc.dml;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import com.codefollower.lealone.test.jdbc.TestBase;

public class InsertTest extends TestBase {
    @Test
    public void run() throws Exception {
        createTableIfNotExists("InsertTest");
        createTableIfNotExists("InsertTest2");
        testInsert();
    }

    void testInsert() throws Exception {
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('01', 'a1', 'b', 51)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('02', 'a1', 'b', 61)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('03', 'a1', 'b', 61)");

        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('25', 'a2', 'b', 51)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('26', 'a2', 'b', 61)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('27', 'a2', 'b', 61)");

        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('50', 'a1', 'b', 12)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('51', 'a2', 'b', 12)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('52', 'a1', 'b', 12)");

        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('75', 'a1', 'b', 12)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('76', 'a2', 'b', 12)");
        stmt.executeUpdate("INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES('77', 'a1', 'b', 12)");

        String sql = "INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) VALUES"
                + " ('01', 'a1', 'b', 12), ('02', 'a1', 'b', 12), ('03', 'a1', 'b', 12)"
                + ",('25', 'a1', 'b', 12), ('26', 'a1', 'b', 12), ('27', 'a1', 'b', 12)"
                + ",('50', 'a1', 'b', 12), ('51', 'a1', 'b', 12), ('52', 'a1', 'b', 12)"
                + ",('75', 'a1', 'b', 12), ('76', 'a1', 'b', 12), ('77', 'a1', 'b', 12)";

        assertEquals(12, stmt.executeUpdate(sql));

        sql = "INSERT INTO InsertTest2(_rowkey_, f1, cf1.f2, cf2.f3) VALUES"
                + " ('01', 'a1', 'b', 12), ('02', 'a1', 'b', 12), ('03', 'a1', 'b', 12)"
                + ",('25', 'a1', 'b', 12), ('26', 'a1', 'b', 12), ('27', 'a1', 'b', 12)"
                + ",('50', 'a1', 'b', 12), ('51', 'a1', 'b', 12), ('52', 'a1', 'b', 12)"
                + ",('75', 'a1', 'b', 12), ('76', 'a1', 'b', 12), ('77', 'a1', 'b', 12)";

        assertEquals(12, stmt.executeUpdate(sql));

        sql = "INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) SELECT _rowkey_, f1, cf1.f2, cf2.f3 FROM InsertTest2";
        assertEquals(12, stmt.executeUpdate(sql));

        sql = "INSERT INTO InsertTest(_rowkey_, f1, cf1.f2, cf2.f3) "
                + "DIRECT SELECT _rowkey_, f1, cf1.f2, cf2.f3 FROM InsertTest2";
        assertEquals(12, stmt.executeUpdate(sql));
    }
}
