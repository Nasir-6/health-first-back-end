package patientTests.serviceTests;

import com.bnta.exception.PatientNotFoundException;
import com.bnta.patient.PatientDAO;
import com.bnta.patient.PatientDBAccess;
import com.bnta.exception.IllegalStateException;
import com.bnta.patient.BloodType;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import jdk.jfr.SettingDefinition;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AddPatientServiceTest {

    @Mock private PatientDAO patientDAO;
    private PatientService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new PatientService(patientDAO);
    }

    @Test
    void testIfPatientDoesExist() {
        //Given
        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);

        boolean expected = true;
        Mockito.when(patientDAO.selectPatientById(eq(examplePatient.getPatientNhsId()))).thenReturn(examplePatient);
        Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(examplePatient));

        //When
        boolean result = underTest.doesPatientWithIdExist(examplePatient.getPatientNhsId());


        //Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testIfPatientDoesNotExist() {
        Patient examplePatient2 =
                new Patient(6,
                        "Hank",
                        "079164890211",
                        "hank@gmail.com",
                        BloodType.AB);

        List<Patient> exampleList = new ArrayList<Patient>();
        exampleList.add(examplePatient2);
        //Given

        boolean expected = false;
        Mockito.when(patientDAO.selectPatientById(eq(6))).thenReturn(examplePatient2);
        Mockito.when(patientDAO.selectAllPatients()).thenReturn(exampleList);

        //When
        boolean result = underTest.doesPatientWithIdExist(1);

        //Then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void successfulAddPatient(){
        // Given
        Patient examplePatient =
                new Patient(2,
                "John",
                "07910975166",
                "johndc@gmail.com",
                BloodType.B);

        Mockito.when(patientDAO.insertPatient(eq(examplePatient))).thenReturn(1);


        /* Don't need this right now since addPatient doesn't use
         * patientDAO.selectAllPatients() to check if patient already exists!
         * If we add this check, need to mock this here!
        Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(new Patient(2,
                "Dave",
                "07910975555",
                "davethedude@gmail.com",
                BloodType.A)));
        */

        // When
        int result = underTest.addNewPatient(examplePatient);
        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(patientDAO).insertPatient(patientArgumentCaptor.capture());
        Patient expectedPatient = patientArgumentCaptor.getValue();

        // Then
        assertThat(expectedPatient).isEqualTo(examplePatient);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void throwErrorIfAddGoesWrong() {
        // Given
        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);

        Mockito.when(patientDAO.insertPatient(eq(examplePatient))).thenReturn(0);

        // When? Nothing?

        // Then
        assertThatThrownBy(() -> {
            underTest.addNewPatient(examplePatient);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("Could not register new patient.");

        // We don't need this, right? Because we're always calling patientDAO.insertPatient
        // in the PatientService.
//        Mockito.verify(patientDAO, Mockito.never()).insertPatient(examplePatient);
    }

 /*   @Test
    void addInvalidPatientBody(){
        // Given
        Patient examplePatient = new Patient();
        Mockito.when(patientDAO.insertPatient(eq(examplePatient))).thenThrow(java.lang.IllegalStateException.class);
        java.lang.IllegalStateException badPatient = new java.lang.IllegalStateException("Could not register new patient.");

        // When
        int result = underTest.addNewPatient(examplePatient);
        ArgumentCaptor<java.lang.IllegalStateException> exceptionArgumentCaptor = ArgumentCaptor.forClass(IllegalStateException.class);
        java.lang.IllegalStateException expectedException = exceptionArgumentCaptor.getValue();

        // Then
        assertThat(expectedException).isEqualTo(badPatient);
        assertThat(result).isNull();
    }*/

    @Test
    void shouldThrowWhenPatientIsNull() {
        //Given
        // When
        // Then
        assertThatThrownBy(() -> {
            underTest.addNewPatient(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Patient cannot be null");

        Mockito.verifyNoInteractions(patientDAO);
//        Mockito.verify(patientDAO, Mockito.never()).addPatient(null);
    }

    @Test
    void shouldThrowWhenPatientNameIsNull() {
        //Given
        Patient examplePatient =
                new Patient(2,
                        null,
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);
        // When
        // Then
        assertThatThrownBy(() -> {
            underTest.addNewPatient(examplePatient);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("Patient cannot have empty fields");

     //   Mockito.verifyNoInteractions(patientDAO);
    }

    @Test
    void shouldThrowWhenPatientIdIsNull() {
        Patient examplePatient =
                new Patient(0,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);

        assertThatThrownBy(() -> {
            underTest.addNewPatient(examplePatient);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage( "Patient cannot have empty fields");

//        Mockito.verify(personDAO, Mockito.never()).insertPerson(person);
     //   Mockito.verifyNoInteractions(examplePatient);
    }

    @Test
    void shouldThrowWhenPatientBloodTypeIsNull() {
        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        null);

        assertThatThrownBy(() -> {
            underTest.addNewPatient(examplePatient);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage( "Patient cannot have empty fields");

//        Mockito.verify(personDAO, Mockito.never()).insertPerson(person);
        //   Mockito.verifyNoInteractions(examplePatient);
    }

    @Test
    void successfulUpdatePatient(){
        // Given
        Patient exampleUpdatePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        BloodType.B);

        Mockito.when(patientDAO.updatePatient(eq(2), eq(exampleUpdatePatient))).thenReturn(1);


         /*Don't need this right now since addPatient doesn't use
         * patientDAO.selectAllPatients() to check if patient already exists!
         * If we add this check, need to mock this here!*/
        /*Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(new Patient(2,
                "Dave",
                "07910975555",
                "davethedude@gmail.com",
                BloodType.A)));*/


        // When
        int result = underTest.updatePatient(2, exampleUpdatePatient);
        ArgumentCaptor<Patient> patientArgumentCaptor = ArgumentCaptor.forClass(Patient.class);
        Mockito.verify(patientDAO).updatePatient(eq(exampleUpdatePatient.getPatientNhsId()), patientArgumentCaptor.capture());
        Patient expectedPatient = patientArgumentCaptor.getValue();

        // Then
        assertThat(expectedPatient).isEqualTo(exampleUpdatePatient);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void itCanDeletePatient() {

       // Given
        Patient examplePatient =
                new Patient(2,
                        "John",
                        "07910975166",
                        "johndc@gmail.com",
                        null);

        Mockito.when(patientDAO.selectAllPatients()).thenReturn(List.of(
                examplePatient
        ));

        Mockito.when(patientDAO.selectPatientById(examplePatient.getPatientNhsId())).thenReturn(examplePatient);

        //When

        Mockito.when(patientDAO.deletePatient(examplePatient)).thenReturn(1);
        int result = underTest.deletePatientById(examplePatient.getPatientNhsId());

        assertThat(result).isEqualTo(1);
    }

}
