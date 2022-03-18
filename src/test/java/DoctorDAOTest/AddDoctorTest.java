package DoctorDAOTest;

import com.bnta.appointment.Appointment;
import com.bnta.doctor.Doctor;
import com.bnta.doctor.DoctorDAO;
import com.bnta.doctor.DoctorService;
import com.bnta.exception.IllegalStateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AddDoctorTest {
    @Mock
    private DoctorDAO doctorDAO;
    private DoctorService underTest;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new DoctorService(doctorDAO);
    }

    //Add Doctor

    @Test
    void successfulAddDoctor(){
       //Given
        Doctor testDoctor = new Doctor(1,
                "Michael Jordan",
                "Free Throw Line");

        given(doctorDAO.addDoctor(testDoctor)).willReturn(1);

        //When
        int result = doctorDAO.addDoctor(testDoctor);
        ArgumentCaptor<Doctor> doctorArgumentCaptor = ArgumentCaptor.forClass(Doctor.class);
        verify(doctorDAO).addDoctor(doctorArgumentCaptor.capture());
        Doctor expectedDoctor = doctorArgumentCaptor.getValue();

        //Then
        assertThat(testDoctor).isEqualTo(expectedDoctor);
        assertThat(result).isEqualTo(1);


    }
    @Test
    void unsuccessfulAddDoctorWhenNull() {
        //Given
        Doctor testDoctor = new Doctor(1,
                "Michael Jordan",
                "Free Throw Line");


        given(doctorDAO.addDoctor((testDoctor))).willReturn(0);

        //Then
        assertThatThrownBy(() -> {
            int result = underTest.addDoctor(testDoctor);
            ArgumentCaptor<Doctor> doctorArgumentCaptor = ArgumentCaptor.forClass(Doctor.class);
            verify(doctorDAO).addDoctor(doctorArgumentCaptor.capture());
            Doctor expectedDoctor = doctorArgumentCaptor.getValue();

        }).hasMessage("Could not register new doctor.");
    }

}
