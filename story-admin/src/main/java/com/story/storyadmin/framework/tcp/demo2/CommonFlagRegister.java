package com.story.storyadmin.framework.tcp.demo2;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
public class CommonFlagRegister implements ReciveRegister {
    public CommonFlagRegister(Object o) {
    }

    public CommonFlagRegister() {

    }

    @Override
    public byte[] read(InputStream in) throws IOException {
        return new byte[0];
    }
}
