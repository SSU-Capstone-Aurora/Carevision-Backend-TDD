package aurora.carevisionapiserver.domain.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.admin.service.AdminService;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;
import aurora.carevisionapiserver.domain.nurse.dto.response.NursePreviewResponse;
import aurora.carevisionapiserver.domain.nurse.service.NurseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final NurseService nurseService;

    @Override
    public List<NursePreviewResponse> getActivatedNurses(Admin admin) {
        List<Nurse> nurses = nurseService.getActivatedNursesByAdmin(admin);

        return nurses.stream()
                .map(nurse -> NursePreviewResponse.of(nurse.getId(), nurse.getName()))
                .collect(Collectors.toList());
    }
}
