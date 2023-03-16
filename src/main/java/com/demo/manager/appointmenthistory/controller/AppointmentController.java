package com.demo.manager.appointmenthistory.controller;

import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.response.AppointmentHistoryResponse;
import com.demo.manager.appointmenthistory.service.AppointmentService;
import com.demo.manager.appointmenthistory.resource.GeneralResource;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = GeneralResource.PATH + "/appointment")
@Tag(name = "Appointment History", description = "Get appointment history for specified period")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(
            produces = "application/json"
    )
    @Parameter(
            name = "from",
            required = true,
            description = "Date as 'dd-MM-yyyy'",
            example = "21-02-2023"
    )
    @Parameter(
            name = "to",
            required = true,
            description = "Date as 'dd-MM-yyyy'",
            example = "22-02-2023"
    )
    public ResponseEntity<AppointmentHistoryResponse> getAppointmentHistory(@RequestParam("from")
                                                                            @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                                            LocalDate from,
                                                                            @RequestParam("to")
                                                                            @DateTimeFormat(pattern = "dd-MM-yyyy")
                                                                            LocalDate to) {

        List<Appointment> appointments = appointmentService.getAppointmentHistory(from, to);
        return new ResponseEntity<>(new AppointmentHistoryResponse(from, to, appointments), HttpStatus.OK);
    }
}
