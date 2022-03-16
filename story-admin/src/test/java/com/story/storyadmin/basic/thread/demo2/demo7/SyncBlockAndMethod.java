package thread.demo7;

/**
 * 底层实现原理 视频讲解的很详细 各种锁，全面的认识
 * // javac SyncBlockAndMethod.java编译Java文件
 * // javap -verbose SyncBlockAndMethod.class 查看class文件的编译指令文件
 */
public class SyncBlockAndMethod {
    public void syncsTask() {
        //同步代码库
        synchronized (this) {
            System.out.println("Hello");
            synchronized (this){
                System.out.println("World");
            }
        }
    }

    public synchronized void syncTask() {
        System.out.println("Hello Again");
    }

}
