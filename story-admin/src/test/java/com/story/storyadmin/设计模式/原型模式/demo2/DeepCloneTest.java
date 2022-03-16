package com.story.storyadmin.设计模式.原型模式.demo2;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 结论
 *
 * 深克隆后每个孙悟空都有自己的金箍棒，而浅克隆后每个孙悟空用的金箍棒实质上还是同一根。
 */
public class DeepCloneTest {
    public static void main(String[] args) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try {
            QiTianDaSheng newObject = (QiTianDaSheng) qiTianDaSheng.clone();
            System.out.print("深克隆后 ");
            System.out.println("金箍棒是否一直：" + (qiTianDaSheng.jinGuBang == newObject.jinGuBang));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        QiTianDaSheng newObject=qiTianDaSheng.shalllowClone(qiTianDaSheng);
        System.out.print("浅克隆后 ");
        System.out.println("金箍棒是否一直：" + (qiTianDaSheng.jinGuBang == newObject.jinGuBang));
    }
}
