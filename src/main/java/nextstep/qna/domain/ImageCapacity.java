package nextstep.qna.domain;

public class ImageCapacity {
    private final Long capacity;
    private static final Long MAX_CAPACITY = 1_000_000L;

    public ImageCapacity(Long capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
    }

    private void validateCapacity(Long capacity) {
        if(capacity > MAX_CAPACITY){
            throw new IllegalArgumentException("이미지 용량 크기는 1MB 이하여야 합니다.");
        }
    }
}
