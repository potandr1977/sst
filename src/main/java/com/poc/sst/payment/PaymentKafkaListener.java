package com.poc.sst.payment;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.poc.sst.person.handlers.PersonCreatedHandler;
import com.poc.sst.primitive.DomainEvent;
import com.poc.sst.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentKafkaListener {

    private final PersonCreatedHandler personCreatedHandler;
    private final Gson gson;

    @KafkaListener(topics = KafkaUtils.PAYMENT_TOPIC_NAME, groupId = KafkaUtils.PAYMENT_GROUP_NAME)
    public void listen(String message) {
        Gson gson = new Gson();
        String type;

        try {
            var obj = JsonParser.parseString(message).getAsJsonObject();
            type = obj.get("type").getAsString();
        }
        catch(Exception ex)
        {
            log.error(ex.getMessage());
            return;
        }

        switch(type) {
            case "com.poc.sst.event.PaymentSaldoCounted":

                break;
            case "com.poc.sst.event.AccountCrated":
                break;
            case "com.poc.sst.event.PaymentCreated":
                break;
            default: return;
        }

        var event = gson.fromJson(message, DomainEvent.class);

    }
}
