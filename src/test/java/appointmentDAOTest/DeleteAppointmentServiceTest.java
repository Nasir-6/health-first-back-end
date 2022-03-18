package appointmentDAOTests;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DeleteAppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);
    }

    @Test
    void successfulDeleteAppointment(){
        //Given
        int id = 1;
        Appointment testAppointment = new Appointment(id,
                2,
                3,

                LocalDate.of(2022, Month.JUNE, 12),
                LocalTime.of(14, 23));

        given(appointmentDAO.deleteAppointmentById(id)).willReturn(0);
        //if you look at the Appointment service, we invoked the selectAppointment method, the mocking doesn't have
        // access to the selectAppointment method, so we need to add the parameter for selectAppointment and pass testAppointment through it.
        given(appointmentDAO.selectAppointmentById(id)).willReturn(testAppointment);

        //When
        int actual = underTest.deleteAppointmentById(id);
        ArgumentCaptor<Integer> appointmentArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(appointmentDAO).deleteAppointmentById(appointmentArgumentCaptor.capture());
        Integer expectedAppointment = appointmentArgumentCaptor.getValue();

        //Then
        assertThat(expectedAppointment).isEqualTo(id);
        assertThat(actual).isEqualTo(0);
    }


    @Test
    void unsuccessfulDeleteAppointment(){
        //Given
        Integer id = null;
//        Appointment testAppointment = new Appointment(id,
//                2,
//                3,
//
//                LocalDate.of(2022, Month.JUNE, 12),
//                LocalTime.of(14, 23));
//
//        given(appointmentDAO.deleteAppointmentById(id)).willReturn(null);
//        given(appointmentDAO.selectAppointmentById(id)).willReturn(testAppointment);

        //When
        assertThatThrownBy(() -> {
            //Then

            int result = underTest.deleteAppointmentById(id);
            ArgumentCaptor<Integer> appointmentArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            verify(appointmentDAO).deleteAppointmentById(appointmentArgumentCaptor.capture());
            Integer expectedAppointment = appointmentArgumentCaptor.getValue();

        }).hasMessage("Sorry " + id + " could not be found");
    }

    @Test
    void wrongIdTNotDeleteAppointment() {
        //Given
        Integer id = 15;
        Appointment testAppointment = new Appointment(1,
                2,
                3,
                LocalDate.of(2022, Month.APRIL, 17),
                LocalTime.of(10, 30));
        given(appointmentDAO.selectAppointmentById(id)).willReturn(null);

        assertThatThrownBy(() -> {

            int result = underTest.deleteAppointmentById(id);
            ArgumentCaptor<Integer> appointmentArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
            verify(appointmentDAO).deleteAppointmentById(appointmentArgumentCaptor.capture());
            Integer expectedAppointment = appointmentArgumentCaptor.getValue();

        }).hasMessage("Sorry " + id + " could not be found");
    }




//    public int removePerson(int id) {
//        boolean exists = doesPersonWithIdExists(id);
//        if (!exists) {
//            throw new IllegalStateException("person with id " + id + " not found");
//        }
//        return personDAO.deletePerson(id);
//    }






}
