package org.apache.commons.pool2.me.simple;

import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by guilin on 2016/9/18.
 */
public class Client {

    public static void main(String[] args) throws IOException {
        ReaderUtil readerUtil = new ReaderUtil(new GenericObjectPool<StringBuffer>(new StringBufferFactory()));
        String str = readerUtil.readToString(new FileReader(new File("E:/test/FilePathSrcTest.txt")));
        System.out.println(str);
        str = readerUtil.readToString(new FileReader(new File("E:/test/FilePathSrcTest.txt")));
        System.out.println(str);

    }
}
