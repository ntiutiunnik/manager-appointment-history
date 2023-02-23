package com.demo.manager.appointmenthistory.model;

import com.demo.manager.appointmenthistory.model.auxiliary.AccountDto;
import com.demo.manager.appointmenthistory.model.auxiliary.DoctorScheduleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "appointment_history")
public class Appointment {

    @Id
    private String id;

    private LocalDate date;

    private Long internalId;

    private Long number;

    @JsonProperty("doctor schedule")
    private DoctorScheduleDto doctorSchedule;

    private AccountDto account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getInternalId() {
        return internalId;
    }

    public void setInternalId(Long internalId) {
        this.internalId = internalId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public DoctorScheduleDto getDoctorSchedule() {
        return doctorSchedule;
    }

    public void setDoctorSchedule(DoctorScheduleDto doctorSchedule) {
        this.doctorSchedule = doctorSchedule;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }
}
