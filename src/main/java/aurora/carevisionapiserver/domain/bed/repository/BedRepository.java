package aurora.carevisionapiserver.domain.bed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aurora.carevisionapiserver.domain.bed.domain.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {}
