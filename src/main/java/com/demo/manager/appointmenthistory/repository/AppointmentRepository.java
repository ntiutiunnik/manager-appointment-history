package com.demo.manager.appointmenthistory.repository;

import com.demo.manager.appointmenthistory.model.Appointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    @Query("{'date' : { $gte: ?0, $lte: ?1 } }")
    List<Appointment> findAllByDateBetween(LocalDate from, LocalDate to, Sort sort);
}
