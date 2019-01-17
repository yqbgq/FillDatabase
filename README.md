# FillDatabase  

能不能给我一个 :start: 呢？

## 用途
在学习MySQL数据库调优时，往往需要拥有一个已经填充了大量数据的数据库供我们进行实操。但是这么多数据哪里来？我们 **不可能一条条手动插入** ， **存储过程牛刀小用而且还有点麻烦** 。那么你就可以试一试 **FillDatabase** 了，简单快速帮你搞定。

## 亮点
1. 简单配置，即刻可用
2. 允许自定义字段生成器，快速方便
3. 自动解析外键约束
4. 支持多种数据类型

## 配置
1. 克隆本仓库或者直接下载 **FillDatabase.jar**
2. FillDatabase依赖于MySQL最新驱动，请确保满足了该依赖或者直接下载 **mysql-connector-java-8.0.13.jar**


## 须知
1. **FillDatabase** 暂时只支持以下数据结构：整型、浮点型、字符串、文本、布尔型、bit、Date、枚举以及集合
2. 插入速度和表中的字段数量、字段长度、外键数量等都有关

## DEMO

**构造TaskClient并执行插入任务：**
```java
public class Test {
    public static void main(String[] args){
        try {
            //TaskClient 由 ConcreteBuilder 对象构建，最后使用build()完成构造
            TaskClient client = new ConcreteBuilder()
                    .setUsername("root")    //设置数据库连接用户名
                    .setPassword("root")    //设置数据库连接密码
                    .setNumOfSQL(10000)     //设置单次提交的SQL语句数量
                    //提供数据库名、表名以及插入行数添加一个插入任务
                    .addTask("final","test1",10000)
                    //提供数据库名和插入行数为数据库中所有数据表插入相同行数的记录
                    .addTask("java",null,10000)
                    //进行校验并构建
                    .build();
            //在构造完成之后依然可以添加任务,同理也能进行部分设置
            client.addTask("final","test2",10000);
            //开始任务
            client.start();
        }catch (BuildingException e){
            e.printStackTrace();
        }
    }
}
```
程序将对名为java的数据库中所有的数据表各插入10000条记录，并分别为final.test1和final.test2表插入10000条记录。

程序控制台输出如下：
```19:56:22  Thread[main,5,main]  客户端构造成功
19:56:22  Thread[main,5,main]  开始对任务列表进行分析
19:56:23  Thread[main,5,main]  任务分析完成，检查任务表上是否存在外键
19:56:23  Thread[main,5,main]  外键检查完成，即将开始执行任务
19:56:23  Thread[pool-1-thread-1,5,main]  开始执行任务final.test1
19:56:23  Thread[pool-1-thread-2,5,main]  开始执行任务java.course
19:56:23  Thread[pool-1-thread-3,5,main]  开始执行任务java.student
19:56:23  Thread[pool-1-thread-4,5,main]  开始执行任务java.studentcourse
19:56:23  Thread[pool-1-thread-5,5,main]  开始执行任务final.test2
19:56:25  Thread[pool-1-thread-3,5,main]  java.student完成插入 10000 条记录
19:56:25  Thread[pool-1-thread-4,5,main]  java.studentcourse完成插入 10000 条记录
19:56:25  Thread[pool-1-thread-3,5,main]  完成任务java.student
19:56:25  Thread[pool-1-thread-4,5,main]  完成任务java.studentcourse
19:56:26  Thread[pool-1-thread-2,5,main]  java.course完成插入 10000 条记录
19:56:26  Thread[pool-1-thread-2,5,main]  完成任务java.course
19:56:27  Thread[pool-1-thread-5,5,main]  final.test2完成插入 10000 条记录
19:56:27  Thread[pool-1-thread-5,5,main]  完成任务final.test2
19:56:28  Thread[pool-1-thread-1,5,main]  final.test1完成插入 10000 条记录
19:56:28  Thread[pool-1-thread-1,5,main]  完成任务final.test1
```
## QA
**Q：** 为什么显示插入了10000条记录但是实际上会小于或者等于10000条记录？

**A：** 当表中存在唯一性约束的列时，当程序尝试插入的数据在表中已经存在，则该条SQL语句将会被无视。


**Q：** 程序报错堆空间溢出？

**A：** 为了提高插入的速度，**FillDatabase** 会在生成多条SQL语句后再统一提交给MySQL，在提交之前，这些SQL语句都会占用内存空间。一般来说，出现这样的情况有以下两种原因：1. **同时插入过多的数据表**，暂存了太多的SQL语句。2. **文本类型字段会占用较大的空间**，批量插入引发占用空间爆炸。为了解决这个问题，可以采用以下两种手段：1. **调用TaskClient对象的setNumOfSql()方法，减少暂存的SQL语句数量**，这会导致插入的速度降低，默认为10000条语句。2. **减少同时执行的任务数量，多批次执行start()方法。**

**Q：** 三种自定义错误代表什么含义？

**A：** **BuildingException** 代表在构建TaskClient时出错。  **MissTableException** 代表在查找指定数据表时时出错。  **UnMatchedRefTableException** 代表在分析外键时发现错误。比如外键依赖的表中没有记录。 

**Q:** **FillDatabase** 让我的数据库出现错误该怎么办

**A:** 很抱歉，我们只建议 **FillDatabase** 仅用于学习，您的数据库出现的错误我们无能为力。