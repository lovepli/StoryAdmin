> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [mp.weixin.qq.com](https://mp.weixin.qq.com/s/kcwcpE2V19Fe16ygnodiWw)

**点击关注上方 “数据前线”，**  

**设为 “置顶或星标****”，第一时间送达干货**

今天再给大家分享一下 Oracle 的常用函数。

**1、字符函数**

字符函数接受字符参数，这些参数可以是表中的列，也可以是一个字符串表达式。常用的字符函数：

```
ASCII(X)         --返回字符X的ASCII码  
                 --如：ASCII('A') 结果：65
CONCAT(X,Y)      --连接字符串X和Y  
                 --如：CONCAT('SQL','数据库开发')  结果：SQL数据库开发
CHR(X)           --返回X所指代的字符   如：CHR(65)  结果：A
INITCAP(X)       --返回X每个单词首字母大写的格式  
                 --如 INITCAP('hello world') 结果：Hello World
INSTR(X,STR[,START[,N]])  
                 --从X中查找str，并返回它出现的位置，可以指定从start开始，也可以指定从n开始
                 --如：INSTR('ABCDABCD','AB',4,1) 结果：5
LENGTH(X)        --返回X的长度  如：LENGTH('ABC') 结果：3
LOWER(X)         --X转换成小写
UPPER(X)         --X转换成大写
LTRIM(X[,TRIM_STR])                
                 --把X的左边截去trim_str中任一字符串，缺省截去空格
RTRIM(X[,TRIM_STR])              
                 --把X的右边截去trim_str中任一字符串，缺省截去空格
TRIM([TRIM_STR  FROM]X)      
                 --把X的两边截去trim_str字符串，缺省截去空格
REPLACE(X,old,new)                
                 --在X中查找old字符串，并替换成new字符串
                 --如：REPLACE('ABCDABCD','AB','X') 结果：XCDXCD
SUBSTR(X,start[,length])          
                 --返回X的字串，从start处开始，截取length个字符，缺省length，默认到结尾
                 --如：SUBSTR('ABCDEFGH',3,5) 结果：CDEFG
```

（提示：可以左右滑动代码）

**2、数字函数**

数字函数接受数字参数，参数可以来自表中的一列，也可以是一个数字表达式。

```
ABS(X)           --X的绝对值
ACOS(X)          --X的反余弦
COS(X)           --X的余弦
CEIL(X)          --大于或等于X的最小值
FLOOR(X)         --小于或等于X的最大值
LOG(X,Y)         --X为底Y的对数
MOD(X,Y)         --X除以Y的余数
POWER(X,Y)       --X的Y次幂
ROUND(X[,Y])     --X在第Y位四舍五入
SQRT(X)          --X的平方根
TRUNC(X[,Y])     --X在第Y位截断
说明：
a.  ROUND(X[,Y])，四舍五入。
在缺省 y 时，默认 y=0；比如：ROUND(3.56)=4。
y 是正整数，就是四舍五入到小数点后 y 位。ROUND(5.654,2)=5.65。
y 是负整数，四舍五入到小数点左边|y|位。ROUND(351.654,-2)=400。
b.  TRUNC(x[,y])，直接截取，不四舍五入。
在缺省 y 时，默认 y=0；比如：TRUNC (3.56)=3。
Y是正整数，就是四舍五入到小数点后 y 位。TRUNC (5.654,2)=5.65。
y 是负整数，四舍五入到小数点左边|y|位。TRUNC (351.654,-2)=300。
```

**3、日期函数**

日期函数对日期进行运算。常用的日期函数有：

```
ADD_MONTHS(d,n)       --在某一个日期 d 上，加上指定的月数 n，返回计算后的新日期。
                      --d 表示日期，n 表示要加的月数。
CURRENT_DATE          --返回当前日期，包含时间部分
LAST_DAY(d)           --返回指定日期当月的最后一天。
MONTHS_BETWEEN(d1,d2) --返回日期d1月d2之间的相差的月数
NEXT_DAY(date,char)   --从日期参数中返回char参数所指定 下一天
                      --如：NEXT_DAY(DATE'2020-6-27', '星期二') 结果：2020/6/30
ROUND(d[,fmt])        --返回一个以 fmt 为格式的四舍五入日期值， d 是日期， fmt 是格式模型。
                      --默认 fmt 为 DDD，即月中的某一天。
1、如果 fmt 为“YEAR”则舍入到某年的 1 月 1 日，即前半年舍去，后半年作为下一年。
2、如果 fmt 为“MONTH”则舍入到某月的 1 日，即前月舍去，后半月作为下一月。
3、默认为“DDD”，即月中的某一天，最靠近的天，前半天舍去，后半天作为第二天。
4、如果 fmt 为“DAY”则舍入到最近的周的周日，即上半周舍去，下半周作为下一周周日。
例如
SELECT SYSDATE,ROUND(SYSDATE),ROUND(SYSDATE,'day'),
ROUND(SYSDATE,'month'),ROUND(SYSDATE,'year') FROM dual;
与 ROUND 对应的函数时 TRUNC(d[,fmt])对日期的操作， TRUNC 与 ROUND 非常相似，
只是不对日期进行舍入，直接截取到对应格式的第一天。
EXTRACT(fmt FROM d)    --提取日期中的特定部分。
fmt 为：YEAR、MONTH、DAY、HOUR、MINUTE、SECOND。
其中 YEAR、MONTH、DAY可以为 DATE 类型匹配，也可以与 TIMESTAMP 类型匹配；
但是 HOUR、MINUTE、SECOND 必须与 TIMESTAMP 类型匹配。
HOUR 匹配的结果中没有加上时区，因此在中国运行的结果小 8 小时。
例：SELECT SYSDATE "date",
       EXTRACT(YEAR FROM SYSDATE) "year",
       EXTRACT(MONTH FROM SYSDATE) "month",
       EXTRACT(DAY FROM SYSDATE) "day",
       EXTRACT(HOUR FROM SYSTIMESTAMP) "hour",
       EXTRACT(MINUTE FROM SYSTIMESTAMP) "minute",
       EXTRACT(SECOND FROM SYSTIMESTAMP) "second"
FROM dual;
```

**4、转换函数**

转换函数将值从一种数据类型转换为另外一种数据类型。常见的转换函数有：

```
TO_CHAR(d|n[,fmt])       --把日期和数字转换为制定格式的字符串。Fmt是格式化字符串
例如：
SELECT TO_CHAR(SYSDATE,'YYYY"年"MM"月"DD"日" HH24:MI:SS') "date" FROM dual;
代码解析：
在格式化字符串中，使用双引号对非格式化字符进行引用
针对数字的格式化，格式化字符有：
9      --指定位置处显示数字
.      --指定位置返回小数点
,      --指定位置返回一个逗号
$      --数字开头返回一个美元符号
EEEE   --科学计数法表示
L      --数字前加一个本地货币符号
PR     --如果数字式负数则用尖括号进行表示 
TO_DATE(X [,fmt])        --把一个字符串以fmt格式转换成一个日期类型
TO_NUMBER(X [,fmt])      --把一个字符串以fmt格式转换为一个数字
```

**5、聚合函数**

统计函数 (聚合函数)

```
AVG()                   --求平均值
COUNT()                 --统计数目
MAX()                   --求最大值
MIN()                   --求最小值
SUM()                   --求和
```

**6、分析函数**

```
分析函数语法
function_name(<argument>,<argument>...) over(<partition_Clause><order by_Clause>);
function_name()：函数名称
argument：参数
over( )：开窗函数
partition_Clause：分区子句，数据记录集分组，partition by...
order by_Clause：排序子句，数据记录集排序，order by...
COUNT() OVER()  --统计分区中各组的行数，partition by 可选，order by 可选
SUM() OVER()    --统计分区中记录的总和，partition by 可选，order by 可选
AVG() OVER()    --统计分区中记录的平均值，partition by 可选，order by 可选
MIN() OVER()    --统计分区中记录的最小值，partition by 可选，order by 可选
MAX() OVER()    --统计分区中记录的最大值，partition by 可选，order by 可选
RANK() OVER()   --跳跃排序，partition by 可选，order by 必选
DENSE_RANK() OVER()    --连续排序，partition by 可选，order by 必选
ROW_NUMBER() OVER()  --排序，无重复值，partition by 可选，order by 必选
NTILE(n) OVER()      --partition by 可选，order by 必选
    n表示将分区内记录平均分成n份，多出的按照顺序依次分给前面的组
FIRST_VALUE() OVER() --取出分区中第一条记录的字段值，partition by 可选，order by 可选
LAST_VALUE() OVER()  --取出分区中最后一条记录的字段值，partition by 可选，order by 可选
LAG() OVER()         --取出前n行数据，partition by 可选，order by 必选
LEAD() OVER()        --取出后n行数据，partition by 可选，order by 必选
PERCENT_RANK() OVER()  --partition by 可选，order by 必选
     所在组排名序号-1除以该组所有的行数-1，排名跳跃排序
```

**7、其他函数**  

```
NVL(X,VALUE)             --如果X为空，返回value，否则返回X
NVL2(x,value1,value2)    --如果x非空，返回value1，否则返回value2
```

```
更多精彩内容，请关注「数据前线」

记得点「赞」和「在看」

爱你们
```