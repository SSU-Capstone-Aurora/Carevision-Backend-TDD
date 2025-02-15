package aurora.carevisionapiserver.domain.nurse.dto.response;

public record NursePreviewResponse(Long id, String name) {
    public static NursePreviewResponse of(Long id, String name) {
        return new NursePreviewResponse(id, name);
    }
}
