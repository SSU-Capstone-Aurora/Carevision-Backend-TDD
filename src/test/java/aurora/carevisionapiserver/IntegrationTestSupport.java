package aurora.carevisionapiserver;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import aurora.carevisionapiserver.domain.nurse.service.NurseService;

@ActiveProfiles("local")
@SpringBootTest
public abstract class IntegrationTestSupport {
    @MockBean protected NurseService nurseService;
}
