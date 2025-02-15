package aurora.carevisionapiserver.domain.admin.service;

import java.util.List;

import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.nurse.dto.response.NursePreviewResponse;

public interface AdminService {
    List<NursePreviewResponse> getActivatedNurses(Admin admin);
}
