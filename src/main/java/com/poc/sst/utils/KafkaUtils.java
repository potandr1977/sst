package com.poc.sst.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaUtils {

    public static final String PERSON_TOPIC_NAME = "person-topic";
    public static final String PERSON_GROUP_NAME = "person-group";
    public static final String ACCOUNT_TOPIC_NAME = "account-topic";
    public static final String ACCOUNT_GROUP_NAME = "account-group";
    public static final String PAYMENT_TOPIC_NAME = "payment";
    public static final String PAYMENT_GROUP_NAME = "1";
    public static final String BOOTSTRAP_SERVERS = "localhost:9092";
}
