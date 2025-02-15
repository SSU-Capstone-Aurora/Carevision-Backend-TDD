package aurora.carevisionapiserver.domain.nurse.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;
import aurora.carevisionapiserver.domain.nurse.repository.NurseRepository;
import aurora.carevisionapiserver.domain.nurse.service.NurseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NurseServiceImpl implements NurseService {
    private final NurseRepository nurseRepository;

    @Override
    public List<Nurse> getActivatedNursesByAdmin(Admin admin) {
        return nurseRepository.getActivatedNurseByAdmin(admin);
    }
}
