package aurora.carevisionapiserver.domain.nurse.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import aurora.carevisionapiserver.IntegrationTestSupport;
import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.admin.repository.AdminRepository;
import aurora.carevisionapiserver.domain.hospital.domain.Department;
import aurora.carevisionapiserver.domain.hospital.domain.Hospital;
import aurora.carevisionapiserver.domain.hospital.repository.DepartmentRepository;
import aurora.carevisionapiserver.domain.hospital.repository.HospitalRepository;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;
import aurora.carevisionapiserver.domain.patient.repository.PatientRepository;

@Transactional
class CustomNurseRepositoryImplTest extends IntegrationTestSupport {
    @Autowired private CustomNurseRepositoryImpl customNurseRepository;
    @Autowired private AdminRepository adminRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private NurseRepository nurseRepository;
    @Autowired private HospitalRepository hospitalRepository;
    @Autowired private DepartmentRepository departmentRepository;

    @DisplayName("관리자가 관리하는 활성화된 간호사를 조회한다.")
    @Test
    void getActivatedNurseByAdmin() {
        // given
        Department department1 = createDepartment("depart1");
        Department department2 = createDepartment("depart2");

        Admin admin = createAdmin("admin1", department1);
        adminRepository.save(admin);

        Nurse nurse1 = createNurse("nurse1", true, department1);
        Nurse nurse2 = createNurse("nurse2", false, department1);
        Nurse nurse3 = createNurse("nurse3", false, department2);
        Nurse nurse4 = createNurse("nurse4", true, department2);
        nurseRepository.saveAll(List.of(nurse1, nurse2, nurse3, nurse4));

        // when
        List<Nurse> response = customNurseRepository.getActivatedNurseByAdmin(admin);

        // then
        assertThat(response)
                .hasSize(1)
                .extracting("username", "isActivated")
                .contains(tuple("nurse1", true));
    }

    private static Nurse createNurse(String username, boolean isActivated, Department department) {
        return Nurse.builder()
                .username(username)
                .isActivated(isActivated)
                .department(department)
                .build();
    }

    private static Admin createAdmin(String username, Department department) {
        return Admin.builder().username(username).department(department).build();
    }

    private Department createDepartment(String name) {
        Hospital hospital = createHospital();

        Department department = Department.builder().name(name).hospital(hospital).build();
        return departmentRepository.save(department);
    }

    private Hospital createHospital() {
        Hospital hospital = Hospital.builder().name("오로라병원").build();
        return hospitalRepository.save(hospital);
    }
}
