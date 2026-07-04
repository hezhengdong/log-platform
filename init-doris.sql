-- 创建数据库
CREATE DATABASE IF NOT EXISTS log_platform;

-- 建表
CREATE TABLE IF NOT EXISTS log_platform.logs (
    timestamp DATETIME NOT NULL,
    service VARCHAR(64) NOT NULL,
    level VARCHAR(16) NOT NULL,
    message STRING NOT NULL,
    logger_name VARCHAR(128) NOT NULL,
    thread_name VARCHAR(64) NOT NULL
)
PROPERTIES ("replication_num" = "1");

-- 从 Kafka 拉数据
USE log_platform;
CREATE ROUTINE LOAD logs_load ON logs
COLUMNS(timestamp, service, level, message, logger_name, thread_name)
PROPERTIES (
    "max_batch_interval" = "5",
    "max_batch_rows" = "200000",
    "exec_mem_limit" = "268435456",
    "format" = "json",
    "jsonpaths" = "[
        \"$.@timestamp\",
        \"$.service\",
        \"$.level\",
        \"$.message\",
        \"$.logger_name\",
        \"$.thread_name\"
    ]"
)
FROM KAFKA (
    "kafka_broker_list" = "kafka:9092",
    "kafka_topic" = "app-logs",
    "property.group.id" = "doris_v4",
    "property.kafka_default_offsets" = "OFFSET_BEGINNING"
);
