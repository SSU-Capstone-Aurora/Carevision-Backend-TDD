package aurora.carevisionapiserver.domain.admin.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import aurora.carevisionapiserver.IntegrationTestSupport;
import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.admin.repository.AdminRepository;
import aurora.carevisionapiserver.domain.admin.service.AdminService;
import aurora.carevisionapiserver.domain.bed.repository.BedRepository;
import aurora.carevisionapiserver.domain.camera.repository.VideoRepository;
import aurora.carevisionapiserver.domain.hospital.domain.Department;
import aurora.carevisionapiserver.domain.hospital.domain.Hospital;
import aurora.carevisionapiserver.domain.hospital.repository.DepartmentRepository;
import aurora.carevisionapiserver.domain.hospital.repository.HospitalRepository;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;
import aurora.carevisionapiserver.domain.nurse.dto.response.NursePreviewResponse;
import aurora.carevisionapiserver.domain.nurse.repository.NurseRepository;
import aurora.carevisionapiserver.domain.patient.repository.PatientRepository;

class AdminServiceImplTest extends IntegrationTestSupport {
    @Autowired private AdminService adminService;
    @Autowired private AdminRepository adminRepository;
    @Autowired private PatientRepository patientRepository;
    @Autowired private NurseRepository nurseRepository;
    @Autowired private HospitalRepository hospitalRepository;
    @Autowired private DepartmentRepository departmentRepository;
    @Autowired private VideoRepository videoRepository;
    @Autowired private BedRepository bedRepository;

    @AfterEach
    void tearDown() {
        bedRepository.deleteAllInBatch();
        videoRepository.deleteAllInBatch();
        patientRepository.deleteAllInBatch();
        nurseRepository.deleteAllInBatch();
        adminRepository.deleteAllInBatch();
        departmentRepository.deleteAllInBatch();
        hospitalRepository.deleteAllInBatch();
    }

    @DisplayName("관리자의 병원에 있는 간호사를 조회한다.")
    @Test
    void getActivatedNurse() {
        // given
        Department department = createDepartment();

        Admin admin = createAdmin("admin1", department);
        adminRepository.save(admin);

        Nurse nurse1 = createNurse(1L, "nurse1", department);
        Nurse nurse2 = createNurse(2L, "nurse2", department);
        List<Nurse> nurses = List.of(nurse1, nurse2);
        nurseRepository.saveAll(nurses);

        // when
        when(nurseService.getActivatedNursesByAdmin(any(Admin.class))).thenReturn(nurses);
        List<NursePreviewResponse> response = adminService.getActivatedNurses(admin);

        // then
        assertThat(response)
                .hasSize(2)
                .extracting("id", "name")
                .contains(
                        tuple(1L, "nurse1"),
                        tuple(2L, "nurse2")
                );
    }

    private static Nurse createNurse(Long id, String name, Department department) {
        return Nurse.builder().id(id).name(name).department(department).build();
    }

    private static Admin createAdmin(String username, Department department) {
        return Admin.builder().username(username).department(department).build();
    }

    private Department createDepartment() {
        Hospital hospital = createHospital();

        Department department = Department.builder().hospital(hospital).build();
        return departmentRepository.save(department);
    }

    private Hospital createHospital() {
        Hospital hospital = Hospital.builder().name("오로라병원").build();
        return hospitalRepository.save(hospital);
    }
}
