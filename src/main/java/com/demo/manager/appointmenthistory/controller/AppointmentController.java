package com.demo.manager.appointmenthistory.controller;

import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.response.AppointmentHistoryResponse;
import com.demo.manager.appointmenthistory.service.AppointmentService;
import com.demo.manager.appointmenthistory.util.resource.GeneralResource;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Parameters({
            @Parameter(
                    name = "from",
                    required = true,
                    example = "2023-02-21"
            ),
            @Parameter(
                    name = "to",
                    required = true,
                    example = "2023-02-21"
            )}
    )
    public ResponseEntity<AppointmentHistoryResponse> getAppointmentHistory(@RequestParam("from")
                                                                            @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                            LocalDate from,
                                                                            @RequestParam("to")
                                                                            @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                            LocalDate to) {

        List<Appointment> appointments = appointmentService.getAppointmentHistory(from, to);
        return new ResponseEntity<>(new AppointmentHistoryResponse(from.toString(), to.toString(), appointments), HttpStatus.OK);
    }
}
