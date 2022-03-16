> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/gNmRKqcgEZokUmDjRxPj8Q)

![](https://mmbiz.qpic.cn/mmbiz_png/TtgsXZeib3BD6KwDZHBFKJ9KuA2PcJs6j6hvjvVlPZhGFuTy1wDOUXuoxND4HYjbtpJOgd27qg40a56heib1Nwsw/640?wx_fmt=png)

（一）关于加密算法
=========

信息加密是现在几乎所有项目都需要用到的技术，身份认证、单点登陆、信息通讯、支付交易等场景中经常会需要用到加密算法，所谓加密算法，**就是将原本的明文通过一系列算法操作变成密文**。接下来就介绍一下目前比较常用的一些加密算法，本期不涉及算法底层，以应用介绍和代码展示为主。

如果只想了解原理，可跳过代码部分，代码可直接拿来使用。

（二）MD5 算法
=========

准确来讲，**MD5 不是一种加密算法**，而是一种**摘要算法**，MD5 能将明文输出为 128bits 的字符串，这个**字符串是无法再被转换成明文的**。网上一些 MD5 解密网站也只是保存了一些字符串对应的 md5 串，通过已经记录的 md5 串来找出原文。

我做过的几个项目中经常见到 MD5 用在加密上的场景。比如对密码的加密，生成一个密码后，使用 MD5 生成一个 128 位字符串保存在数据库中，用户输入密码后也先生成 MD5 串，再去数据库里比较。因此我们在找回密码时是无法得到原来的密码的，因为明文密码根本不会被保存。

```
public class MD5 {
    /**
     * 生成MD5
     * @param str
     * @return
     */
    public String encode(String str) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            result = md.digest();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return parseByte2HexStr(result);
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MD5 md5=new MD5();
        String content = "测试test";
        System.out.println(md5.encode(content));
    }
}
```

（三）SHA1 算法
==========

SHA1 也是和 MD5 类似的**信息摘要算法**，但是它比 MD5 更加安全。

```
public class SHA1 {
    public String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes("utf-8"));
            byte[] digest = md.digest();
            return byteToHexString(digest);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String byteToHexString(byte[] bytes) {
        return String.valueOf(Hex.encodeHex(bytes));
    }
    public static void main(String[] args) {
        SHA1 sha1 = new SHA1();
        String content = "测试test";
        System.out.println(sha1.encode(content));
    }
}
```

（四）AES 算法
=========

AES 是很常见的**对称加密算法**，所谓对称加密，就是**通过密钥加密后可以再通过密钥解密**。我接触过的某个国企现在内部就是采用 AES 的方式实现集成登陆。第三方系统提供一个接收用户信息的接口，该国企将用户信息 AES 加密后通过这个接口传递给第三方系统，第三方系统自行实现登陆操作。这里需要注意的是密钥十分重要，如果密钥丢失，就有信息泄漏的风险。

```
public class AES {
    /**
     * 将传入的明文转换为密文
     * @param str
     * @param pwd
     * @return
     */
    public String encode(String str,String pwd) {
        byte[] result = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(pwd.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = str.getBytes();
            result = cipher.doFinal(byteContent);

        } catch (Exception e) {
            return null;
        }

        return parseByte2HexStr(result);
    }

    /**
     * 将传入的密文转换为明文
     * @param str
     * @param pwd
     * @return
     */
    public String decode(String str,String pwd) {
        byte[] result = null;
        byte[] content = parseHexStr2Byte(str);
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(pwd.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(content);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new String(result);
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    public static void main(String[] args) {
        AES aes = new AES();
        String content = "测试加密";
        // AES的密钥长度最好是16位(不是必须)
        String pwd = "javayznbjavayznb";

        // 加密
        System.out.println("加密前：" + content);
      String encodeResultStr = aes.encode(content,pwd);
      System.out.println("加密后：" + encodeResultStr);
      // 解密
      String decodeResultStr = aes.decode(encodeResultStr,pwd);
      System.out.println("解密后：" + decodeResultStr);
    }

}
```

（五）DES
======

DES 也是一种**对称加密算法**，但是在安全性、效率和灵活性上比 AES 略差，但是也能保证安全，DES 也需要通过密钥进行加密，通过密钥进行解密，因此密钥很重要：

```
public class DES {
    /**
     * 将传入的明文转换为密文
     * @param str
     * @param pwd
     * @return
     */
    public String encode(String str,String pwd) {
        byte[] result = null;
        try {
            DESKeySpec keySpec = new DESKeySpec(pwd.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = str.getBytes();
            result = cipher.doFinal(byteContent);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return parseByte2HexStr(result);
    }

    /**
     * 将传入的密文转换为明文
     * @param str
     * @param pwd
     * @return
     */
    public String decode(String str,String pwd) {
        byte[] result = null;
        byte[] content = parseHexStr2Byte(str);
        try {
            DESKeySpec keySpec = new DESKeySpec(pwd.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(content);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new String(result);
    }

    /**
     * 将二进制转换成十六进制
     *
     * @param buf
     * @return
     */
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        DES des = new DES();
        String content = "测试test";
        // DES的密钥长度必须是8位(小于8位则会报错，8位之后对加密结果不会产生影响)
        String pwd = "javayznb";
        // 加密
        System.out.println("加密前：" + content);
        String encodeResultStr = des.encode(content,pwd);
        System.out.println("加密后：" + encodeResultStr);
        //解密
        String decodeResultStr = des.decode(encodeResultStr,pwd);
        System.out.println("解密后：" + decodeResultStr);
    }
}
```

（六）RSA
======

RSA 是目前最具影响力的公钥加密算法，并且可以用于加密和验签。支付宝支付对接时用的加密方式就是 RSA。在 RSA 中，存在一对密钥，分别称为**公钥和私钥，通过私钥由个人保存，公钥可能多人持有**。

RSA 的主要应用场景就是**加密和验签**，加密就不用说了，**验签是指通过私钥对消息进行签名，使得消息无法篡改和伪造。**

**加密方式**：B 传加密数据给 A

1、A 生成公钥和私钥，私钥自己保留，公钥任何人可以获取。

2、B 拿到公钥，将数据通过公钥加密

3、A 收到密文，通过私钥解密。

**验签方式**：A 传消息给 B 1、A 生成公钥和私钥，私钥自己保留，公钥任何人可以获取。

2、A 使用私钥对消息加签，并将加签后的消息传给 B。

3、B 通过公钥验签，如果返回是 true 则说明消息是 A 发过来的且未被篡改。

```
public class TestRSA {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "待加密的文字内容";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            System.out.println("加签后："+sign);
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}
```

（七）总结
=====

关于加密这个问题不管是工作中还是面试中都会被提及，也必须要掌握。不要求加密的代码能手写，但是要知道每种加密算法是干什么的，是什么样的效果。