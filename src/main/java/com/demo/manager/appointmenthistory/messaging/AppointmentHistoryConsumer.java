package com.demo.manager.appointmenthistory.messaging;

import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.repository.AppointmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class AppointmentHistoryConsumer {
    private static final Logger logger = LogManager.getLogger(AppointmentHistoryConsumer.class);

    private final String topicName = "${spring.kafka.topic.name}";

    private final AtomicReference<Map<Long, Appointment>> mapAtomicReference = new AtomicReference<>(new ConcurrentHashMap<>());

    private final AppointmentRepository appointmentRepository;

    public AppointmentHistoryConsumer(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @KafkaListener(topics = topicName)
    public void consumeMessage(Appointment appointment) {
        logger.info("Received appointment with id: {}.", appointment.getInternalId());

        mapAtomicReference.getAndUpdate(map -> {
            map.put(appointment.getInternalId(), appointment);
            return map;
        });
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void processInBatch() {
        Map<Long, Appointment> appointmentMap = mapAtomicReference.getAndSet(new ConcurrentHashMap<>());
        if (appointmentMap.isEmpty()) {
            return;
        }

        Collection<Appointment> appointments = appointmentMap.values();
        logger.info("Going to save {} appointments.", appointments.size());

        appointmentRepository.saveAll(appointments);
    }
}
