package com.poc.sst.payment;

import com.google.gson.Gson;
import com.poc.sst.event.PaymentSaldoCounted;
import com.poc.sst.payment.entity.Payment;
import com.poc.sst.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;
    @PostMapping
    public Mono<ResponseEntity<String>> createOrder(@RequestBody Mono<PaymentSaldoCounted> paymentSaldoCountedMono) {
        return paymentSaldoCountedMono.flatMap(paymentSaldoCounted -> {
            var payload = gson.toJson(paymentSaldoCounted);
            // Оборачиваем CompletableFuture в Mono
            return Mono.fromFuture(kafkaTemplate.send(KafkaUtils.PAYMENT_TOPIC_NAME, payload))
                    .map((SendResult<String, String> result) ->
                            ResponseEntity.ok("Event sent to Kafka, offset=" + result.getRecordMetadata().offset())
                    )
                    .onErrorResume(ex ->
                            Mono.just(ResponseEntity.internalServerError()
                                    .body("Failed to send event: " + ex.getMessage()))
                    );
        });
    }
}
