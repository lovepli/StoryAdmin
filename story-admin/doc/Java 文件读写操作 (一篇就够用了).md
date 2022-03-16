> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/19PZ1wvi7Kr4aaibX0C9Ig)

在学习文件流读写数据之前，让我们先看一个实用类库工具，它可以帮我们处理文件目录问题

一、File 类
--------

这个类主要操作目录和文件 不同的系统有不同的分隔符

Window 系统的分隔符是：`\\`

Linux 系统的分隔符是：`/`

为了不把文件路径写死，我们可以通过`File.separator`获取系统分隔符

```
//路径写死："C://abc.txt"，只能匹配Window系统
//路径没有写死，什么系统都可以用
String path="C:"+File.separator+"abc.txt";
```

我们可以使用 File 类的方法

1. 创建文件 / 文件夹

```
当且仅当具有该名称的文件尚不存在时，原子地创建一个由该抽象路径名命名的新的空文件。
```

1. 删除文件 / 文件夹

```
删除由此抽象路径名表示的文件或目录。
```

1. 获取文件 / 文件夹

```
返回由此抽象路径名表示的文件或目录的名称。
```

1. 判断文件 / 文件夹是否存在

```
测试此抽象路径名表示的文件或目录是否存在。
```

1. 遍历文件夹

```
返回一个字符串数组，命名由此抽象路径名表示的目录中的文件和目录。
```

listFiles() 方法我们单独拿出来讲

1. 获取文件的大小

```
返回由此抽象路径名表示的文件的长度。
```

我们对以上的方法进行测试

```
import java.io.File;
import java.io.IOException;
public class FileTest {
    //为了方便查看，这里直接抛出异常，不对异常进行处理
    public static void main(String[] args) throws IOException{
        //获取文件分隔符
        String separator=File.separator;
        //声明目录,在Window系统中C://test
        File d=new File("c:"+separator+"test");
        //判断文件/文件夹是否存在,!表示flase时执行下面语句
        if(!d.exists()) {
            /*
                创建新的目录(文件夹)，这里只有一级目录，所以可以使用mkdir()创建
                如果是C://test//a，test目录也不存在，必须使用mkdirs()创建，否则报错
            */
            d.mkdirs();
        }
        //判断是否为目录，返回true/false
        System.out.println("这是目录？"+d.isDirectory());
        //声明文件
        File f=new File("c:"+separator+"test"+separator+"ab.txt");
        //判断文件/文件夹是否存在,!表示flase时执行下面语句
        if(!f.exists()) {
            //创建新的文件
            f.createNewFile();
        }
        //获取文件名
        System.out.println(f.getName());
        //获取上一级目录
        System.out.println(f.getParent());
        //获取全局路径
        System.out.println(f.getPath());
        //判断是不是文件
        System.out.println(f.isFile());
        //获取长度
        System.out.println(f.length());
        //获取目录下的文件/文件夹
        String[] fe=d.list();
        //遍历目录下的所有文件
        for(String s:fe) {
            System.out.println(s);
        }
        //删除文件
        f.delete();
        //删除目录
        d.delete();
        }
    }
```

通过上面的代码，相信你对 File 更加的熟悉了 我们接着讲`FileFilter`过滤器

我们有个需求是：只遍历 C:\test 目录下的图片，实现 FileFilter 接口和 `listFiles(FileFilter f)` 方法我们可以很快速的实现

**FileFilter 接口如下**

```
public interface FileFilter{
    public boolean accept(File filename);
}
```

**FileFilter 接口说明** FileFilter 接口只有一个 boolean 类型的`accept`方法，当返回为 true 时，会把传递过去的 File 对象保存到 File 数组中，否则不会保存到 File 数组中。filename 是`listFiles`方法传递过去的 File 对象。我们先在 C 盘的 test 目录 (没有的话新建) 放些内容![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rgZvpYVfyoIG6G4FOjEeVDzFM6CWmOhTbIGMReeOr0huNEmCYSuwrg4pjVosPIlHibxPO4EZktJvgQ/640?wx_fmt=png)

下面我们自定义一个 FileFilter 过滤器`MyFileFilter`类

```
import java.io.File;
import java.io.FileFilter;
//MyFileFilter类实现FileFilter
public class MyFileFilter implements FileFilter{
    @Override
    public boolean accept(File filename) {
    //获取文件名，转换为字符串，不区分大小写，截取后缀
        Boolean jpg=filename.getName().toString().toLowerCase().endsWith(".jpg");
        Boolean jpeg=filename.getName().toString().toLowerCase().endsWith(".jpeg");
        Boolean png=filename.getName().toString().toLowerCase().endsWith(".png");
        //jpg值为true时执行
        if(jpg) {
            return true;
        }
        //png值为true时执行下面
        else if(png){
            return true;
        }
        else if(jpeg) {
            return true;
        }
        else {
        return false;
        }
    }
}
```

然后我们在新建一个类进行测试

```
import java.io.File;
import java.io.IOException;
public class Test {
    //为了方便查看，这里直接抛出异常，不对异常进行处理
    public static void main(String[] args) throws IOException{
        //获取文件目录
        File d=new File("c:\\test");
        //对文件目录进行过滤遍历
        File[] files=d.listFiles(new MyFileFilter());
        //防止空指针异常，对数组进行判断，不为空时输出
        if(files!=null) {
            for(File f:files) {
                System.out.println(f);
            }
        }
    }
}
```

下面我们接着讲 IO 流

二、IO 流的介绍
---------

> 什么是 IO 流？  
>
> 流是一种抽象概念，它代表了数据的无结构化传递。按照流的方式进行输入输出，数据被当成无结构的字节序或字符序列。从流中取得数据的操作称为提取操作，而向流中添加数据的操作称为插入操作。用来进行输入输出操作的流就称为 IO 流。换句话说，IO 流就是以`流`的`方式`进行`输入输出`

简而言之，我们是以流的形式对文件读写操作。就好比我们平时拿吸管喝水，数据的传输也是和” 水流一样 “；那么 IO 流的分类是怎样的呢？

按照数据的流向分为

• **输入流：** 把`其他设备中`的数据`读取`到`内存中`的流 • **输出流：** 把`内存中`的数据`写入`到其他设备中的流

按数据类型

• **字节流：** 以二进制的存储方式的流 • **字符流：** 以字符的存储方式的流，通常处理中文

讲完了这些分类，我们来谈谈 java.io 包中的类 java.io 包中分为

• **结点类：** 直接对文件的读写操作

• **包装类**：

`转化类`：字节 / 字符数据类型的转化

`装饰类`：装饰结点类

**结点类**有哪些呢？

字节流：`OutputStream`(输出)、`InputStream`(输入)

-- 子类：FileOutputStream，FileInputStream

字符流：`Writer`(输出)、`Reader`(输入)

-- 子类 FileWriter,FileReader

这些类都是能直接对文件的读写操作

那么什么是**转化类**呢？

我们读取文件的时候会常常遇到`乱码问题`, 而转化类可以帮助我们转换为对应的字符编码格式。比如：UTF-8,GBK 等等

OutputStreamWriter(输出流使用)，InputStreamReader(输入流使用)

那么什么是**装饰类**呢？

在流的传输过程中，我们使用了**缓存**进行了” 装饰 “。

字节缓存流：`BufferedOutputStream`(输出缓存)、`BufferedInputStream`(输入缓存)

字符缓存流：`BufferedWriter`(输出缓存)、`BufferedReader`(输入缓存)

讲了那么多概念，我们来用代码验证

三、文本文件读写 (字符流)
--------------

因为一个中文占 3 个字节空间 所以，一个字节空间无法存放一个中文，这里就会用到字符流。

```
写入数据到流中
```

字符输出流：

```
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//以字符输出流写入数据
public class WriterTest {
    public static void main(String[] args){
        //声明字节输入流
        FileOutputStream os=null;
        //声明转化类
        OutputStreamWriter ow=null;
        //声明装饰类
        BufferedWriter bo=null;
        try {
            //获取文件分隔符
            String separator=File.separator;
            //声明文件：D://ab.txt
            File f=new File("D:"+separator+"ab.txt");
            //判断文件/文件夹是否存在,!表示flase时执行下面语句
            if(!f.exists()) {
                f.createNewFile();
            }
            //把文件作为参数传进去
            os=new FileOutputStream(f);
            //对输入流进行转化，以UTF-8的形式
            ow=new OutputStreamWriter(os,"UTF-8");
            //使用缓存装饰转化流
            bo=new BufferedWriter(ow);
            //写入字符串
            bo.write("加油啊");
            //换行
            bo.newLine();
            //写入字符
            bo.write('a');
            //换行
            bo.newLine();
            //以缓存的方式写入,65表示A
            bo.write(65);
            //刷新流
            bo.flush();
            System.out.println("存储成功");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                //关闭
                os.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
```

字符输入流：

```
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
//以字符串输入流读取数据
public class Readerest {
    public static void main(String[] args){
        //声明字节输入流
        FileInputStream is=null;
        //声明转化类
        InputStreamReader ir=null;
        //声明装饰类
        BufferedReader bi=null;
        try {
            //获取文件分隔符
            String separator=File.separator;
            //声明文件：D://ab.txt
            File f=new File("D:"+separator+"ab.txt");
            //判断文件/文件夹是否存在,!表示flase时执行下面语句
            if(!f.exists()) {
                f.createNewFile();
            }
            //把文件作为参数传进去
            is=new FileInputStream(f);
            //对输入流进行转化，以UTF-8的形式
            ir=new InputStreamReader(is,"UTF-8");
            //使用缓存装饰转化流
            bi=new BufferedReader(ir);
            //方式一:一行读取到控制台，返回字符串
//            System.out.println(bi.readLine());
//            System.out.println(bi.readLine());
            System.out.println("---方式二,遍历方式---");
            //末尾以-1结束
            String s;
            while((s=bi.readLine())!=null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                //只需要关闭最外层
                bi.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
```

我们**使用更加简洁**的方式 使用 1.7 版本的新特性处理异常：在 try 后面加 () 字节输出流：重点是要加入`flush()`方法刷新流，不然没有写进内容

```
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//以字符输出流写入数据
public class WriterTest {
    public static void main(String[] args) throws IOException{
        //获取文件分隔符
            String separator=File.separator;
            //声明文件：D://ab.txt
            File f=new File("D:"+separator+"ab.txt");
            //判断文件/文件夹是否存在,!表示flase时执行下面语句
            if(!f.exists()) {
                //创建新文件
                f.createNewFile();
            }
            try(BufferedWriter bo=new BufferedWriter(new OutputStreamWriter(new
              FileOutputStream(f),"UTF-8" ))){ 
                //写入字符串
                bo.write("加油啊");
                //换行
                bo.newLine();
                //写入字符
                bo.write('a');
                //换行
                bo.newLine();
                //以缓存的方式写入,65表示A
                bo.write(65);
              }catch(IOException e) {
                  e.printStackTrace();
             }
    }
}
```

字符输入流

```
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//以字符输入流读取数据
public class ReaderTest {
    public static void main(String[] args) throws IOException{
            //获取文件分隔符
            String separator=File.separator;
            //声明文件：D://ab.txt
            File f=new File("D:"+separator+"ab.txt");
            //判断文件/文件夹是否存在,!表示flase时执行下面语句
            if(!f.exists()) {
                f.createNewFile();
            }
            try(BufferedReader br=new BufferedReader(new InputStreamReader(new
                    FileInputStream(f),"UTF-8" ))){                  
                //方式一：一行读取到控制台，返回字符串
//                System.out.println(br.readLine());
//                System.out.println(br.readLine());
//                System.out.println("---方式二,遍历方式---");
                //不为空时结束
                String s;
                while((s=br.readLine())!=null) {
                    System.out.println(s);
                }
              }catch(IOException e) {
                  e.printStackTrace();
             }
    }
}
```

四、二进制文本读写 (字节流)
---------------

字节流的操作和字符流的操作一样，只不过使用的类不一样。

```
写入数据到流中
```

我把输入流和输出流放同一个文件

```
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//以字节输入流写入数据
public class Test {
    public static void main(String[] args) throws IOException{
                    //获取文件分隔符
        String separator=File.separator;
        //声明文件：D://ab.txt
        File f=new File("D:"+separator+"ab.txt");
        //判断文件/文件夹是否存在,!表示flase时执行下面语句
        if(!f.exists()) {
            f.createNewFile();
        }
        try(DataOutputStream ds=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)))){
            ds.writeUTF("a");
            ds.writeInt(100);
            ds.write(65);
            System.out.println("---输出流结束--");
        }catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("---输入流开始--");
        //以下内容为输入流
        File f2=new File("D:"+separator+"ab.txt");
        try(DataInputStream ds=new DataInputStream(new BufferedInputStream(new FileInputStream(f2)))){
            System.out.println(ds.readUTF());
            System.out.println(ds.readInt());
            System.out.println(ds.read());
        }catch(IOException e) {
            e.printStackTrace();
        }        
    }
}
```

以上关于字节流和字符流的代码中，我们对文件的写入操作是覆盖原有的内容，但有时候我们需要追加内容时，应该怎么做呢？

在`FileOutputStream`类中有构造方法`FileOutputStream(File file, boolean append)`

File file：表示写入数据的目的地

boolean append：表示追加是否开启 ，当我们设置为`true`时，说明开启追加

我们的原代码：new FileOutputStream(f)

```
File f=new File("D:"+separator+"ab.txt");
FileOutputStream os=new FileOutputStream(f)
```

开启追加模式的代码：

```
File f=new File("D:"+separator+"ab.txt");
//这里是变化的地方，后面的参数
FileOutputStream os=new FileOutputStream(f,true)
```

上面介绍的都是文件或者文件夹，但是我们突发异想，怎么把文件压缩和解压呢？下面我们对此进行展开讲解

五、Zip 文件的读写
-----------

使用到的类：

•ZipOutputStream•ZipInputStream•ZipEntry•FileOutputStream•FileInputStream•File

1、Zip 文件的压缩
-----------

> `单个/多个文件压缩步骤`
>
> 1、设置原文件和目标文件 (压缩文件名)
>
> 2、打开输出 zip 文件，指向压缩文件
>
> 3、添加一个`ZipEntry`，使用`putNextEntry`方法
>
> 4、打开一个输入文件，读数据，向`ZipEntry`写数据，关闭输入文件
>
> 5、重复以上两个步骤，写入多个文件到 zip 文件中
>
> 6、关闭 zip 文件

首先，我们先介绍单个文件的压缩

```
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipOutOneTest {
    public static void main(String[] args){
        //要压缩的原文件
        File f=new File("d:\\ab.txt");
        //目标文件
        File zipfile=new File("d:\\ab.zip");
        try {
        //步骤二、打开zip文件，指向压缩的目标文件    
        ZipOutputStream zipout=new ZipOutputStream(new FileOutputStream(zipfile));
        //步骤三、添加ZipEntry，往Zip放文件名
        zipout.putNextEntry(new ZipEntry(f.getName()));
        //设置注释,可以不设置
        zipout.setComment("txt.file zip");
        //步骤四、打开输入文件，读取数据
        InputStream in=new FileInputStream(f);
        //压缩过程，把读取到的文件数据遍历写入到压缩文件中
        int temp=0;
        while((temp=in.read())!=-1) {
            //把读取到的文件数据遍历写入到压缩文件中
            zipout.write(temp);
        }
        //步骤五、关闭输入文件流
        in.close();
        //步骤六、关闭压缩文件
        zipout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("压缩成功");
    }
}
```

下面我们来看看多个文件压缩，重复步骤 3、4

```
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
//以字节输入流写入数据
public class MathTest {
    public static void main(String[] args){
        //要压缩的原文件目录,这个要和电脑的文件路径一致
        File f=new File("D:\\学习\\博客");
        //目标文件，起名字
        File zipfile=new File("d:\\个人博客.zip");
        try {
        //步骤二、打开zip文件，指向压缩的目标文件    
        ZipOutputStream zipout=new ZipOutputStream(new FileOutputStream(zipfile));
        int temp=0;
        if(f.isDirectory()) {
            //获取所有的文件/文件名
            File[] files= f.listFiles();
            //遍历files
            for(File fs:files) {
                //步骤三、添加ZipEntry，往Zip放文件名
                zipout.putNextEntry(new ZipEntry(fs.getName()));
                //设置注释,可以不设置
                zipout.setComment("小庄的个人博客");
                //步骤四、打开输入文件，读取数据
                InputStream in=new FileInputStream(fs);
                //压缩过程，把读取到的文件数据遍历写入到压缩文件中
                while((temp=in.read())!=-1) {
                    //把读取到的文件数据遍历写入到压缩文件中
                    zipout.write(temp);
                }
                //步骤五、关闭输入文件流
                in.close();
            }
        }
        //步骤六、关闭压缩文件
        zipout.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("压缩成功");
    }
}
```

这里压缩没有使用到缓存，可以自己练习一下。压缩讲完了，那么讲讲解压缩吧

2、Zip 文件的解压缩
------------

解压缩是压缩的反操作。

> `单个/多个文件解压缩步骤`
>
> 1、打开输入 zip 文件
>
> 2、获取下一个 ZipEntry，使用 getNextEntry 方法
>
> 3、新建一个目标文件，从 ZipEntry 读取数据，向目标文件写数据
>
> 4、关闭目标文件
>
> 5、重复以上两个步骤，从 zip 包中读取数据到多个目标文件
>
> 6、关闭 zip 文件

单个或多个文件均可用

```
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
//以字节输入流写入数据
public class MathTest {
    public static void main(String[] args){
        try {
            //zip文件位置
            File file=new File("d:\\毕业论文.zip");
            //实例化ZipFile
            ZipFile zipfile=new ZipFile(file);
            //步骤一、打开输入Zip文件
            ZipInputStream zipIn=new ZipInputStream(new FileInputStream(file));
            //步骤二,获取下一个ZipEntry，使用getNextEntry方法
            ZipEntry entry=null;
        //如果ZipEntry不为空，则继续遍历
        while((entry=zipIn.getNextEntry())!=null) {
            //步骤三、新建目标文件，，根据entry获取压缩包中的文件/目录名字
            File f=new File("d:\\测试\\"+entry.getName());
            //判断这个文件路径是否已经存在
            if(!f.getParentFile().exists()) {
                //不存在则创建目录
                f.getParentFile().mkdirs();
            }
            //判断文件/文件夹是否存在
            if(!f.exists()) {
                //不存在，则判断是不是文件夹
                if(f.isDirectory()) {
                    //是文件夹就新建文件夹
                    f.mkdirs();
                }else {
                    //不是文件夹就新建文件
                    f.createNewFile();
                }
            }
            //如果这个不是目录，只是一个文件的话，那么就进行读写操作
            if(!f.isDirectory()) {
                //输出流，输出到指定的文件
                OutputStream out=new FileOutputStream(f);
                //使用装饰类
                BufferedOutputStream bo=new BufferedOutputStream(out);
                //获取每个ZipEntry的输入流
                InputStream in=zipfile.getInputStream(entry);
                //步骤四、对每一个ZipEntry进行遍历读取并写入到目标文件中
                int temp=0;
                while((temp=in.read())!=-1) {
                    //写入目标文件
                    bo.write(temp);
                }
                //关闭写入流
                in.close();
                //关闭输出流
                out.close();
            }
        }
        //步骤六、关闭压缩文件
        zipIn.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("解压缩成功");
    }
}
```

上面代码我们使用了`BufferedOutputStream` 缓存，大大提高了效率。

我们接着讲序列化和反序列化

六、序列化和反序列化
----------

1、序列化和反序列化的概念
-------------

把对象以流的方式写入到文件中保存 -->`序列化`

反过来，把文件中保存的对象以流的方式进行读取出来 -->`反系列化`

**使用到的类和接口**

•ObjectOutputStream•FileOutputStream• 实体类（对象）•Serializable(接口)

2、序列化实现
-------

> 步骤：
>
> 1、建立一个实体类 User, 并设置属性，get 和 set 方法
>
> 2、User 类实现 Serializable(接口)
>
> 3、建立一个操作类，将 User 对象的内容以流的方式写入到指定的文件中

新建 User 类实现 Serializable

```
import java.io.Serializable;
public class User implements Serializable{
    private String name;
    private int age;
    //无参构造
    public User(){}
    //有参构造
    public User(String name,int age) {
        this.name=name;
        this.age=age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User []";
    }
}
```

建立一个操作类

```
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class FileTest {
    public static void main(String[] args) throws IOException{
        //打开输出文件
        String s=File.separator;
        File file=new File("d:\\"+s+"User.txt");
        //判断文件是否存在，若不存在就新建
        if(!file.exists()) {
            //新建文件
            file.createNewFile();
        }
        //使用对象序列,实例化
        ObjectOutputStream op=new ObjectOutputStream(new FileOutputStream(file));
        //将对象写入
        op.writeObject(new User("张三",18));
        //关闭流
        op.close();
    }
}
```

注意：User 类必须要实现 Serializable，否则会报错！

> Serializable 接口

源码中这个接口什么内容都没有

```
public interface Serializable{}
```

源码介绍：类的序列化由实现 java.io.Serializable 接口的类启用。不实现此接口的类将不会使任何状态序列化或反序列化。

这说明 Serializable 是个`标记型接口`，做记号的作用。

3、反序列化实现
--------

上面我们已经了解到：反序列化意思是把文件中保存的对象进行读取 使用到的类

•ObjectInputStream•FileInputStream•User（系列化自定义的类）

我们通过代码进行观察

```
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
public class FileTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //打开输出文件
        String s=File.separator;
        File file=new File("d:\\"+s+"User.txt");
        //判断文件是否存在，若不存在就新建
        if(!file.exists()) {
            //新建文件
            file.createNewFile();
        }
        //使用对象序列,实例化
        ObjectInputStream oi=new ObjectInputStream(new FileInputStream(file));
        User user=(User)oi.readObject();
        System.out.println(user.toString());
        //关闭流
        oi.close();
    }
}
```

4、反序列化中遇到的问题以及解决方法
------------------

先看看序列化和反序列化的流程：![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rgZvpYVfyoIG6G4FOjEeVDzINM5FCjek94fz21L2sup3FrHYx5YxPs8AqGUQg4e2nBhbt2mxCiciarA/640?wx_fmt=png)

问题：每次修改类的定义，都会给 class 文件生成新的序列号

解决方法：无论怎么修改类的定义，都不生成新的序列号，给类绑定一个序列号。使用：

```
private static final long serialVersionUID = 42L;
```

全部代码如下：

```
import java.io.Serializable;
public class User implements Serializable{
    private static final long serialVersionUID = 42L;
    private String name;
    private int age;
    //无参构造
    public User(){}
    //有参构造
    public User(String name,int age) {
        this.name=name;
        this.age=age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User []";
    }
}
```

被关键字修饰的变量不能序列化：

1、`transient` 瞬态关键字

2、`static` 静态关键字

七、文件锁
-----

这个模块的知识涉及到多线程的内容，多线程我们后面会讲。这里我们对文件锁做简单的介绍。

官方的定义：锁是用于通过多个线程控制对`共享资源`的访问的工具。 通常，锁提供对共享资源的独占访问：一次只能有一个线程可以获取锁，并且对共享资源的所有访问都要求首先获取锁。 **但是**，一些锁可能允许`并发访问`共享资源，如`ReadWriteLock`的读锁。

从官方的定义我们了解到，锁在多线程访问中发挥着” 分配资源 “的作用，我们可以根据具体情况设置不同锁的功能，比如，一次只能有一个线程可以获取锁或者运行并发访问共享资源。

**文件锁分为**：

1、独占锁 --> 写锁

2、共享锁 --> 读锁

在多线程环境下，写锁的过程中该锁一次只能被一个线程所持有，只能被一个线性写 在多线程环境下，读锁的过程中指该锁可被多个线程所持有，多个线程都可以读

> 文件锁是怎么实现的呢？

要锁定一个文件，我们可以调用 FileChannel 类的`lock`或`tryLock`方法 **lock()** ：在整个文件上获得一个独占锁，这个方法将阻塞直到获得锁, 除非调用 lock() 线程中断或调用 lock() 的通道关闭。

```
//使用FileChannel的open方法打开要加锁的文件路径path
FileChannel channel=FileChannel.open(path);
//对文件使用lock加锁
FileLock lock=channel.lock();
```

**tryLock()**: 在整个文件上获得一个独占锁，或者无法获得锁的情况下返回`null`，这是非阻塞的。

```
//这里的channel对象使用上面的,只是使用方法是tryLock
FileLock lock=channel.tryLock();
```

在 IO 流中，我们可以通过`getChannel()`方法获取 FileChannel 对象

> 我们还可以锁定文件的一部分：

```
FileLock lock(long start,long size,boolean shared)
或
FileLock tryLock(long start,long size,boolean shared)
```

我们对上面代码进行分析：

1. 前面两个参数是文件加锁的长度（容量）2.shared 表示共享锁是否开启，默认为`false`

我们通过代码来学习一下 **写锁**

```
import java.nio.channels.FileLock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class FileLockTest {
    public static void main(String[] args) throws IOException, InterruptedException{
        FileOutputStream os=null;
        try {
            File f=new File("d:"+File.separator+"ab.txt");
            //如果文件不存在
            if(!f.exists()) {
                //创建新的文件
                f.createNewFile();
            }
            //打开输出流
            os=new FileOutputStream(f);
            //对文件写入操作
            os.write(65);
            //使用lock
            //FileLock lock=os.getChannel().lock();
            //FileLock lock=os.getChannel().lock(0,Long.MAX_VALUE,false);//设置独占锁,指定所有内容
            //使用tryLock
            FileLock lock2=os.getChannel().tryLock();
            if(lock2!=null) {
                System.out.println("Locked File");
//                Thread.sleep(100);//等待100毫秒
//                lock2.release();//释放锁
            }
            //关闭文件锁
            //lock.close();
            lock2.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //关闭流
            os.close();
        }
    }
}
```

**读锁**: 我们对上面写锁的文件进行读锁操作

```
import java.nio.channels.FileLock;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class MathTest {
    public static void main(String[] args) throws IOException{
        FileInputStream is=null;
        try {
            File f=new File("d:"+File.separator+"abd.txt");
            //如果文件不存在
            if(!f.exists()) {
                //提示文件不存在
                System.out.println("sorry! File not found");
            }
            //打开输入流
            is=new FileInputStream(f);
            //必须设置为共享锁(true)才能读取，否则会报异常
            FileLock lock=is.getChannel().lock(0,Long.MAX_VALUE,true);
            //FileLock lock=is.getChannel().lock();错误用法
            //以字节为单位返回锁定区域的大小。
            System.out.println(lock.size());
            //释放锁
            lock.release();
            //关闭锁
            lock.close();
        }finally {
                //关闭流
                is.close();
        }
    }
}
```

== 如果上面代码的 lock() 方法没有设置为共享锁，则会出现异常 == 异常：`NonWritableChannelException`

![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rgZvpYVfyoIG6G4FOjEeVDzEiaaJicu80ibxFI2IaW7XCUlictElXEb6RQb2catDq8MRgHbyFw8Dj3q5w/640?wx_fmt=png)异常介绍：尝试写入最初未打开的通道进行写入时抛出未检查的异常。出现异常原因：原因是这个文件是独占锁, 不可读操作

本篇的内容就到这里结束了，有什么想说的欢迎留言

![](https://mmbiz.qpic.cn/sz_mmbiz_gif/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYWzECicYgRW6f1kmDfgw405hPZhplVCs1TGAAPIF31OeLIMH2nTTfoVg/640?wx_fmt=gif)





![](https://mmbiz.qpic.cn/sz_mmbiz_jpg/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIYe5tFiaoMACjoK1ZciasqcvBZBBA2Bgfnfauk1AmRTicgb6ThpZAba133w/640?wx_fmt=jpeg)

![](https://mmbiz.qpic.cn/sz_mmbiz_png/rVVgBxVy9rh8zH4gNN7hKfqTrZa2nibIY7ADOartJp2PcBZfxWlL7NNOHibWTA8hhtC9LYNaA1VoenibYSVLvZPLw/640?wx_fmt=png)

扫码关注我

主动笃职，认真负责、

求实进取，互动互助。

![](https://mmbiz.qpic.cn/mmbiz_gif/FIBZec7ucCiaOJv1xaGlYbnpgUyOXeX35h4VplHTjV7EHZam8F7iccGJDGuqbMZHrrHz6ljQoEmMWI0ic2CfkLkuA/640?wx_fmt=gif)