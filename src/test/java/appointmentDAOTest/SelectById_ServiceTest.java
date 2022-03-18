package appointmentDAOTest;
import com.bnta.appointment.Appointment;
import com.bnta.appointment.AppointmentDAO;
import com.bnta.appointment.AppointmentService;
import com.bnta.exception.AppointmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;



public class SelectById_ServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;

    private AppointmentService underTest;

    @BeforeEach
    void setUp() {
       // MockitoAnnotations.openMocks(this);
        appointmentDAO = Mockito.mock(AppointmentDAO.class);
        underTest = new AppointmentService(appointmentDAO);
    }

    @Test
    void selectAppointmentByIdSuccessful() {
        //given
        int id = 1;
        Appointment expected = new Appointment(id,
                2,
                3,
                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));


        given(appointmentDAO.selectAppointmentById(expected.getAppointmentId())).willReturn(expected);

        //when

        Appointment actual = underTest.selectAppointmentById(id);
        //Undertest is an instance of appointmentService using mock appointmentDAO

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldNotGetAppointmentIdIfIdNull() {
        //given
        Integer id = null;

        //when
        assertThatThrownBy(() -> underTest.selectAppointmentById(id))
                .isInstanceOf(AppointmentNotFoundException.class)
                .hasMessageContaining("Appointment not found");
        //then

        //this is verifying that appointmentDAO is not accessed as id is null
        verify(appointmentDAO, never()).selectAppointmentById(anyInt());

    }

    @Test
    void shouldNotGetAppointmentIfIdIsIncorrect(){

    }

}


