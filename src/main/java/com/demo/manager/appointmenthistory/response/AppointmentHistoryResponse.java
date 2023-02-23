package com.demo.manager.appointmenthistory.response;

import com.demo.manager.appointmenthistory.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentHistoryResponse {

    private String from;

    private String to;

    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentHistoryResponse() {
    }

    public AppointmentHistoryResponse(String from, String to, List<Appointment> appointments) {
        this.from = from;
        this.to = to;
        this.appointments = appointments;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
