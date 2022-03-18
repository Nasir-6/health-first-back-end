package patientTests.serviceTests;

import com.bnta.patient.PatientDAO;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

// testing Find All Patients
public class FindPatientTest {

    @Mock
    private PatientDAO patientDAO;
    private PatientService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PatientService(patientDAO);
    }

    // one if there are patients, one if there are no patients

    @Test
    void findPatientsWhenExists() {
     //   Given

        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);


        Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(new Patient(2,
                "John",
                "07910975166",
                "johndc@gmail.com",
                BloodType.B)));

        //When
        List<Patient> output = underTest.findAllPatients();
        /* what do these three lines do??
        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(patientDAO).insertPatient(patientArgumentCaptor.capture());
        Patient expectedPatient = patientArgumentCaptor.getValue();*/

        //Then
        assertThat(output.get(0)).isEqualTo(examplePatient);
    }


}
