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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class AddAppointmentServiceTest {

    @Mock
    private AppointmentDAO appointmentDAO;
    private AppointmentService underTest;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AppointmentService(appointmentDAO);
    }
    //add Appointment
    @Test
    void successfulAddAppointment(){
        //Given
        Appointment testAppointment = new Appointment(1,
                2,
                3,

                LocalDate.of(2022,Month.JUNE, 12),
                LocalTime.of(14, 23));

        given(appointmentDAO.bookAppointment((testAppointment))).willReturn(1);

        //When
        int result = underTest.bookAppointment(testAppointment);
        ArgumentCaptor<Appointment> appointmentArgumentCaptor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentDAO).bookAppointment(appointmentArgumentCaptor.capture());
        Appointment expectedAppointment = appointmentArgumentCaptor.getValue();

        //Then
        assertThat(expectedAppointment).isEqualTo(testAppointment);
        assertThat(result).isEqualTo(1);

    }

//unsuccessful adding appointment
    //testing the illegal exception - error
//
//    //Given
//    Food food = new Food(1, 1, "toast", MealType.BREAKFAST, "random", 50, 1, Day.MONDAY);
//    Person personInDb = new Person(1, "marcy", 23, 157.0, 47.0, 2000);
//    // we pass in person Id using food.getPerson_id (getter for Food Class - as personId is a property of it)
//    given(personDao.getPersonById(food.getPerson_id())).willReturn(personInDb);
//    given(foodDao.addFood(food)).willReturn(1);
//
//    //When
//    Integer actual = underTest.addFoodEntry(food);
//    //Then
//    // Assert the integer is 1
//    Integer expected = 1;
//    assertThat(actual).isEqualTo(expected);
    @Test
    void shouldThrowExceptionIfAddAppointmentUnsuccessful(){
        //Given
        Appointment testAppointment1 = new Appointment(1,
                2,
                3,

                LocalDate.of(2022,Month.JUNE, 12),
                LocalTime.of(14, 23));

        given(appointmentDAO.bookAppointment((testAppointment1))).willReturn(0);

        // When
        assertThatThrownBy(() -> {
            // Then

            int result = underTest.bookAppointment(testAppointment1);
            ArgumentCaptor<Appointment> appointmentArgumentCaptor = ArgumentCaptor.forClass(Appointment.class);
            verify(appointmentDAO).bookAppointment(appointmentArgumentCaptor.capture());
            Appointment expectedAppointment = appointmentArgumentCaptor.getValue();

             }).hasMessage("Could not book new appointment");

    }









}
