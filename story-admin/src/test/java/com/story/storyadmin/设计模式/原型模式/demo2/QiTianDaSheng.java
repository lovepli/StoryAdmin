package com.story.storyadmin.设计模式.原型模式.demo2;

import java.io.*;
import java.util.Date;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
//孙悟空有七十二变,拔猴毛生成一个金箍棒
//使用JDK的克隆机制，
//实现Cloneable并重写clone方法
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {

    public JinGuBang jinGuBang;

    public QiTianDaSheng() {
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    //深克隆
    public QiTianDaSheng deepClone() {
        try {
            //内存中操作完成、对象读写，是通过字节码直接操作
            //与序列化操作类似
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream bis = new ObjectInputStream(bais);

            //完成一个新的对象,底层是使用new创建的一个对象
            //详情可以了解readObject方法
            QiTianDaSheng qiTianDaSheng = (QiTianDaSheng) bis.readObject();
            //每个猴子的生日不一样，所以每次拷贝的时候，把生日改一下
            qiTianDaSheng.birthday = new Date();
            return qiTianDaSheng;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //浅克隆，就是简单的赋值
    public QiTianDaSheng shalllowClone(QiTianDaSheng target) {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.height = target.height;
        qiTianDaSheng.weight = target.weight;

        qiTianDaSheng.jinGuBang = target.jinGuBang;
        qiTianDaSheng.birthday = new Date();
        return qiTianDaSheng;

    }
}
