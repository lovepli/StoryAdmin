package com.story.storyadmin.framework.http.demo2;

import java.io.Closeable;
/**
 * CloseUtil类(负责关闭流)
 */
public class CloseUtil {
    public static void closeAll(Closeable ...io) {
        for (Closeable closeable : io) {
            try {
                if(closeable!=null)
                    closeable.close();
            }catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
