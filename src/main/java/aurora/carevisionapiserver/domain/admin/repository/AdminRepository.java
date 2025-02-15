package aurora.carevisionapiserver.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aurora.carevisionapiserver.domain.admin.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {}
