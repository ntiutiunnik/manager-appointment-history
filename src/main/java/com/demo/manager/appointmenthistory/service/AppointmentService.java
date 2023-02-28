package com.demo.manager.appointmenthistory.service;

import com.demo.manager.appointmenthistory.exception.CustomCrudException;
import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.repository.AppointmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointmentHistory(LocalDate from, LocalDate to) {
        if (from == null || to == null || from.isAfter(to)) {
            throw new CustomCrudException("Invalid parameters specified for fetching appointment history.");
        }

        if (from.isAfter(LocalDate.now())) {
            throw new CustomCrudException("Data from future cannot be retrieved.");
        }

        return appointmentRepository.findAllByDateBetween(from, to, Sort.by(Sort.Direction.ASC, "doctorSchedule.timeSlot.startTime"));
    }
}
