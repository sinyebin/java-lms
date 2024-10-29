package nextstep.courses.domain;

public class SessionImg {
    private static final long MAX_IMAGE_SIZE_KB = 1024;
    private static final String OVER_IMG_MAX_SIZE = "이미지 크기가 1MB(1024KB)를 초과합니다.";
    private final int imgSize;


    public SessionImg(int imgSize) {
        validateImgSize(imgSize);
        this.imgSize = imgSize;
    }

    private void validateImgSize(int imgSize) {
        if (imgSize > MAX_IMAGE_SIZE_KB) {
            throw new IllegalArgumentException(OVER_IMG_MAX_SIZE);
        }
    }
}
