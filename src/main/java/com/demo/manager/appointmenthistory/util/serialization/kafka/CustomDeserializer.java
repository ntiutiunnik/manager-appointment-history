package com.demo.manager.appointmenthistory.util.serialization.kafka;

import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.model.auxiliary.DoctorScheduleDto;
import com.demo.manager.appointmenthistory.model.auxiliary.TimeSlotDto;
import com.demo.tools.doctorappointment.autoconfigure.exception.CustomCrudException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

public class CustomDeserializer implements Deserializer<Appointment> {
    private static final Logger logger = LogManager.getLogger(CustomDeserializer.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Appointment deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                logger.info("Null received at deserializing Appointment.");
                return null;
            }

            Appointment appointment = objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Appointment.class);
            formAppointment(appointment);

            return appointment;
        } catch (Exception e) {
            logger.error("Error when deserializing byte[] to Appointment: {}.", e.getMessage());
            throw new SerializationException(e.getMessage());
        }
    }

    @Override
    public void close() {
    }

    private void formAppointment(Appointment appointment) {
        if (appointment == null) {
            return;
        }

        String id = appointment.getId();
        if (StringUtils.isNotEmpty(id)) {
            appointment.setInternalId(Long.valueOf(id));
            appointment.setId(null);

            DoctorScheduleDto doctorScheduleDto = appointment.getDoctorSchedule();
            if (doctorScheduleDto != null) {
                TimeSlotDto timeSlotDto = doctorScheduleDto.getTimeSlot();
                if (timeSlotDto != null) {
                    LocalDateTime startTime = timeSlotDto.getStartTime();
                    if (startTime != null) {
                        appointment.setDate(startTime.toLocalDate());
                        return;
                    }
                }
            }
        }

        throw new CustomCrudException("Invalid Appointment message provided.");
    }
}