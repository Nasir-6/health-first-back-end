package appointmentDAOTest;

import com.bnta.appointment.Appointment;
import com.bnta.appointment.AppointmentDAO;
import com.bnta.appointment.AppointmentService;
import com.bnta.exception.AppointmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.client.ExpectedCount;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.client.ExpectedCount.never;

public class ViewAllAppointmentsServiceTest {

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
    void canViewAllAppointments() {
        //given
        int id = 1;
        Appointment appointment1 = new Appointment(id,
                2,
                3,
                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));

        Appointment appointment2 = new Appointment(id,
                2,
                3,
                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));
        List<Appointment> expectedAppointmentList= new ArrayList<>();
        expectedAppointmentList.add(appointment1);
        expectedAppointmentList.add(appointment2);

        given(appointmentDAO.viewAllAppointments()).willReturn(expectedAppointmentList);

        //when
        List<Appointment> actualAppointmentList = underTest.viewAllAppointments();

        //then
        //Undertest is an instance of appointmentService using mock appointmentDAO

        //Then
        assertThat(actualAppointmentList).isEqualTo(expectedAppointmentList);

    }

    @Test
    void viewAllAppointmentIsEmpty(){
        //given
        given(appointmentDAO.viewAllAppointments()).willReturn(null);

        //When
        assertThatThrownBy(() -> { underTest.viewAllAppointments();})
                //.isInstanceOf(AppointmentNotFoundException.class)
                .hasMessage("No appointments found.");

        //Then

//        assertThatThrownBy(() -> {
//            underTest.selectUserByID(20);}
//        ).hasMessage("User with ID 20 does not exist");

//        verify(appointmentDAO, never(
//    (anyInt());


    }




}
