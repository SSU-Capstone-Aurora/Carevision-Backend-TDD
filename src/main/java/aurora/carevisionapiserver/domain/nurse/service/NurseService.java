package aurora.carevisionapiserver.domain.nurse.service;

import java.util.List;

import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;

public interface NurseService {
    List<Nurse> getActivatedNursesByAdmin(Admin admin);
}
