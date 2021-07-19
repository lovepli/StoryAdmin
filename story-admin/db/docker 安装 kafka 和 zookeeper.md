> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [juejin.cn](https://juejin.cn/post/6844903829624848398)

1 下载镜像

```
docker pull wurstmeister/zookeeper  
docker pull wurstmeister/kafka  
复制代码
```

2 启动 zookeeper 容器

> `docker run -d --name zookeeper -p 2181:2181 -t wurstmeister/zookeeper`

3 启动 kafka 容器 

```
docker run  -d --name kafka -p 9092:9092 -e KAFKA_BROKER_ID=0 -e KAFKA_ZOOKEEPER_CONNECT=192.168.1.100:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.1.100:9092 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 -t wurstmeister/kafka
复制代码
```

这里面主要设置了 4 个参数

KAFKA_BROKER_ID=0  
KAFKA_ZOOKEEPER_CONNECT=192.168.1.100:2181  
KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.1.100:9092  
KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092  
中间两个参数的 192.168.1.100 改为宿主机器的 IP 地址，注意不能设置为127.0.0.1，如果不这么设置，可能会导致在别的机器上访问不到 kafka。

4 测试 kafka 进入 kafka 容器的命令行

```
docker exec -it kafka /bin/bash
复制代码
```

进入 kafka 所在目录

```
cd opt/kafka_2.11-2.0.0/
复制代码
```

启动消息发送方

```
./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic mykafka
复制代码
```

克隆会话 进入 kafka 所在目录

```
cd opt/kafka_2.11-2.0.0/
复制代码
```

启动消息接收方

```
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mykafka --from-beginning
复制代码
```

在消息发送方输入 123456  
在消息接收方查看  
如果看到 123456 消息发送完成  
5 集群搭建  
使用 docker 命令可快速在同一台机器搭建多个 kafka，只需要改变 brokerId 和端口

```
docker run -d --name kafka1 -p 9093:9093 -e KAFKA_BROKER_ID=1 -e KAFKA_ZOOKEEPER_CONNECT=192.168.1.100:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://192.168.1.100:9093 -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9093 -t wurstmeister/kafka
复制代码
```

5 创建 Replication 为 2，Partition 为 2 的 topic 在 kafka 容器中的 opt/kafka_2.12-1.1.0 / 目录下输入

```
bin/kafka-topics.sh --create --zookeeper 192.168.1.100:2181 --replication-factor 2 --partitions 2 --topic partopic
复制代码
```

6 查看 topic 的状态 在 kafka 容器中的 opt/kafka_2.12-1.1.0 / 目录下输入

```
bin/kafka-topics.sh --describe --zookeeper 192.168.1.100:2181 --topic partopic
复制代码
```

输出结果：

```
Topic:partopic  PartitionCount:2    ReplicationFactor:2 Configs:
    Topic: partopic Partition: 0    Leader: 0   Replicas: 0,1   Isr: 0,1
    Topic: partopic Partition: 1    Leader: 0   Replicas: 1,0   Isr: 0,1
复制代码
```

显示每个分区的 Leader 机器为 broker0，在 broker0 和 1 上具有备份，Isr 代表存活的备份机器中存活的。 当停掉 kafka1 后，

```
docker stop kafka1
复制代码
```

再查看 topic 状态，输出结果：

```
Topic:partopic  PartitionCount:2    ReplicationFactor:2 Configs:
    Topic: partopic Partition: 0    Leader: 0   Replicas: 0,1   Isr: 0
    Topic: partopic Partition: 1    Leader: 0   Replicas: 1,0   Isr: 0
复制代码
```