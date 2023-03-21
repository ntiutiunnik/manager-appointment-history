package com.demo.manager.appointmenthistory.service;

import com.demo.manager.appointmenthistory.model.Appointment;
import com.demo.manager.appointmenthistory.repository.AppointmentRepository;
import com.demo.tools.doctorappointment.autoconfigure.exception.CustomCrudException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private LocalDate from;

    private LocalDate to;

    private final Sort defaultSort = Sort.by(Sort.Direction.ASC, "doctorSchedule.timeSlot.startTime");

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        from = LocalDate.of(1997, 12, 19);
        to = LocalDate.of(1997, 12, 20);
    }

    @Test
    public void testGetAppointmentHistory() {
        when(appointmentRepository.findAllByDateBetween(from, to, defaultSort)).thenReturn(List.of(new Appointment()));

        List<Appointment> appointmentList = appointmentService.getAppointmentHistory(from, to);
        assertEquals(1, appointmentList.size());
        verify(appointmentRepository, times(1)).findAllByDateBetween(from, to, defaultSort);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDates")
    public void testGetAppointmentHistoryInvalidDates(LocalDate from, LocalDate to) {
        assertThrows(CustomCrudException.class, () -> appointmentService.getAppointmentHistory(from, to));
    }

    private static List<Arguments> provideInvalidDates() {
        LocalDate date = LocalDate.of(1997, 12, 20);
        LocalDate futureDate = LocalDate.of(3000, 12, 20);

        return List.of(
                Arguments.of(null, null),
                Arguments.of(date.plusDays(1L), date),
                Arguments.of(futureDate, futureDate)
        );
    }
}
