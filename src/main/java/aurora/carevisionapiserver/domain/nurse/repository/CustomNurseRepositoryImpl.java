package aurora.carevisionapiserver.domain.nurse.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import aurora.carevisionapiserver.domain.admin.domain.Admin;
import aurora.carevisionapiserver.domain.nurse.domain.Nurse;
import aurora.carevisionapiserver.domain.nurse.domain.QNurse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CustomNurseRepositoryImpl implements CustomNurseRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Nurse> getActivatedNurseByAdmin(Admin admin) {
        QNurse nurse = QNurse.nurse;

        return queryFactory
                .selectFrom(nurse)
                .where(nurse.isActivated.isTrue())
                .where(nurse.department.eq(admin.getDepartment()))
                .stream()
                .toList();
    }
}
