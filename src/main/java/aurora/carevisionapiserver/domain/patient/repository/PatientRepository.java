package aurora.carevisionapiserver.domain.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aurora.carevisionapiserver.domain.patient.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {}
