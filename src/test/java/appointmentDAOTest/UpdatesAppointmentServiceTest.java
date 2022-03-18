package appointmentDAOTest;

import com.bnta.appointment.Appointment;
import com.bnta.appointment.AppointmentDAO;
import com.bnta.appointment.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class UpdatesAppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);
    }
    @Test
    void successfulAppointmentUpdatesById(){
        //Given

        Integer id = 1;

        Appointment appointmentDB = new Appointment(id,
                2,
                3,
                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));

        given(appointmentDAO.selectAppointmentById(id)).willReturn(appointmentDB);

        Appointment updateAppointment = new Appointment(id,
                5,
                4,
                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));

        given(appointmentDAO.updateAppointment(id, updateAppointment)).willReturn(1);

        //When
        Integer actual = underTest.updateAppointment(id, updateAppointment);

        //then
        Integer expected = 1;
        assertThat(actual).isEqualTo(expected);
    }
    }

