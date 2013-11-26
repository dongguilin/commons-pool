/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.pool2.impl;

import java.util.Set;

import org.apache.commons.pool2.impl.TestGenericObjectPool.SimpleFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestDefaultPooledObjectInfo {

    @Test
    public void testTiming() throws Exception {
        GenericObjectPool<String> pool =
                new GenericObjectPool<String>(new SimpleFactory());

        long t1 = System.currentTimeMillis();

        Thread.sleep(50);
        String s1 = pool.borrowObject();
        Thread.sleep(50);

        long t2 = System.currentTimeMillis();

        Thread.sleep(50);
        pool.returnObject(s1);
        Thread.sleep(50);

        long t3 = System.currentTimeMillis();

        Thread.sleep(50);
        pool.borrowObject();
        Thread.sleep(50);

        long t4 = System.currentTimeMillis();

        Set<DefaultPooledObjectInfo> strings = pool.listAllObjects();

        Assert.assertEquals(1, strings.size());

        DefaultPooledObjectInfo s1Info = strings.iterator().next();

        Assert.assertTrue(s1Info.getCreateTime() > t1);
        Assert.assertTrue(s1Info.getCreateTime() < t2);

        Assert.assertTrue(s1Info.getLastReturnTime() > t2);
        Assert.assertTrue(s1Info.getLastReturnTime() < t3);

        Assert.assertTrue(s1Info.getLastBorrowTime() > t3);
        Assert.assertTrue(s1Info.getLastBorrowTime() < t4);
    }

    @Test
    public void testGetPooledObjectType() throws Exception {
        GenericObjectPool<String> pool =
                new GenericObjectPool<String>(new SimpleFactory());

        pool.borrowObject();

        Set<DefaultPooledObjectInfo> strings = pool.listAllObjects();

        Assert.assertEquals(1, strings.size());

        DefaultPooledObjectInfo s1Info = strings.iterator().next();

        Assert.assertEquals(String.class.getName(),
                s1Info.getPooledObjectType());
    }

    @Test
    public void testGetPooledObjectToString() throws Exception {
        GenericObjectPool<String> pool =
                new GenericObjectPool<String>(new SimpleFactory());

        String s1 = pool.borrowObject();

        Set<DefaultPooledObjectInfo> strings = pool.listAllObjects();

        Assert.assertEquals(1, strings.size());

        DefaultPooledObjectInfo s1Info = strings.iterator().next();

        Assert.assertEquals(s1, s1Info.getPooledObjectToString());
    }
}
