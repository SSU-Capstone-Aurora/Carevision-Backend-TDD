package aurora.carevisionapiserver.domain.hospital.api;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aurora.carevisionapiserver.domain.hospital.converter.HospitalConverter;
import aurora.carevisionapiserver.domain.hospital.domain.Hospital;
import aurora.carevisionapiserver.domain.hospital.dto.response.HospitalResponse.DepartmentListForNurseResponse;
import aurora.carevisionapiserver.domain.hospital.dto.response.HospitalResponse.HospitalIdentifierResponse;
import aurora.carevisionapiserver.domain.hospital.dto.response.HospitalResponse.HospitalListResponse;
import aurora.carevisionapiserver.domain.hospital.service.HospitalService;
import aurora.carevisionapiserver.global.response.BaseResponse;
import aurora.carevisionapiserver.global.response.code.status.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Nurse 💉", description = "간호사 관련 API")
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/hospitals")
public class NurseHospitalController {
    private final HospitalService hospitalService;

    @Operation(summary = "간호사 회원가입 시 병원명 조회 API", description = "서비스에 등록된 병원명을 조회합니다_예림")
    @ApiResponses({
        @ApiResponse(responseCode = "COMMMON200", description = "OK, 성공"),
    })
    @GetMapping("")
    public BaseResponse<HospitalListResponse<HospitalIdentifierResponse>> getHospitalList() {
        List<Hospital> hospitals = hospitalService.getHospitals();
        return BaseResponse.of(
                SuccessStatus._OK, HospitalConverter.toHospitalNameListResponse(hospitals));
    }

    @Operation(
            summary = "간호사 회원가입 시 병원 과 조회 API",
            description = "입력받은 병원의 id 값을 기준으로 병원 과를 조회합니다_예림")
    @ApiResponses({
        @ApiResponse(responseCode = "COMMMON200", description = "OK, 성공"),
        @ApiResponse(responseCode = "HOSPITAL400", description = "NOT_FOUND, 병원을 찾을 수 없습니다.")
    })
    @GetMapping("/departments")
    public BaseResponse<DepartmentListForNurseResponse> getDepartmentList(
            @RequestParam("hospitalId") Long hospitalId) {
        Map<Long, String> departments = hospitalService.getDepartments(hospitalId);
        return BaseResponse.of(
                SuccessStatus._OK, HospitalConverter.toDepartmentListResponseForNurse(departments));
    }
}
