package com.demo.manager.appointmenthistory.response;

import com.demo.manager.appointmenthistory.model.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentHistoryResponse {

    private LocalDate from;

    private LocalDate to;

    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentHistoryResponse() {
    }

    public AppointmentHistoryResponse(LocalDate from, LocalDate to, List<Appointment> appointments) {
        this.from = from;
        this.to = to;
        this.appointments = appointments;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
