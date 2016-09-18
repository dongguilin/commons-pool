package org.apache.commons.pool2.me.simple;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by guilin on 2016/9/18.
 */
public class StringBufferFactory extends BasePooledObjectFactory<StringBuffer> {
    @Override
    public StringBuffer create() throws Exception {
        return new StringBuffer();
    }

    @Override
    public PooledObject<StringBuffer> wrap(StringBuffer obj) {
        return new DefaultPooledObject<StringBuffer>(obj);
    }

    @Override
    public void passivateObject(PooledObject<StringBuffer> p) throws Exception {
        p.getObject().setLength(0);
    }
}
