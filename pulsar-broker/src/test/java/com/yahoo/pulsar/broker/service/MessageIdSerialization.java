/**
 * Copyright 2016 Yahoo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yahoo.pulsar.broker.service;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.yahoo.pulsar.client.api.MessageId;
import com.yahoo.pulsar.client.impl.MessageIdImpl;

@Test
public class MessageIdSerialization {

    @Test
    void testProtobufSerialization1() throws Exception {
        MessageId id = new MessageIdImpl(1, 2, 3);
        byte[] serializedId = id.toByteArray();
        assertEquals(MessageId.fromByteArray(serializedId), id);
    }

    @Test
    void testProtobufSerialization2() throws Exception {
        MessageId id = new MessageIdImpl(1, 2, -1);
        byte[] serializedId = id.toByteArray();
        assertEquals(MessageId.fromByteArray(serializedId), id);
    }

    @Test(expectedExceptions = NullPointerException.class)
    void testProtobufSerializationNull() throws Exception {
        MessageId.fromByteArray(null);
    }

    @Test(expectedExceptions = IOException.class)
    void testProtobufSerializationEmpty() throws Exception {
        MessageId.fromByteArray(new byte[0]);
    }
}
