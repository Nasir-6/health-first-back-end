package patientTests.controllerTests;

import com.bnta.patient.PatientController;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

public class AddPatientControllerTest {

    @Mock
    private PatientService patientService;
    private PatientController underTest;

    @Test
    void successfulAddPatient(){
        // Given
        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);
        Mockito.when(patientService.addNewPatient(eq(examplePatient))).thenReturn(1);
        Mockito.when(patientService.findAllPatients()).thenReturn(List.of(new Patient(2,
                "John",
                "07910975166",
                "johndc@gmail.com",
                BloodType.B)));

        // When

        // Then
    }

}
