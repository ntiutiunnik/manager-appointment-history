package com.demo.manager.appointmenthistory.model.auxiliary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorScheduleDto {

    private Long id;

    private DoctorDto doctor;

    @JsonProperty("time slot")
    private TimeSlotDto timeSlot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;
    }

    public TimeSlotDto getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlotDto timeSlot) {
        this.timeSlot = timeSlot;
    }
}
