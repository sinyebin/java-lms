package nextstep.courses.domain;

public class SessionImg {
    private static final long MAX_IMAGE_SIZE_KB = 1024;
    private static final String OVER_IMG_MAX_SIZE = "이미지 크기가 1MB(1024KB)를 초과합니다.";
    private static final int MIN_IMG_WIDTH = 300;
    private static final int MIN_IMG_HEIGHT = 200;
    private static final String INVALID_DIMENSIONS_MESSAGE = "이미지 크기는 최소 300x200 이상이어야 합니다.";
    private static final double REQUIRED_ASPECT_RATIO = 3.0 / 2.0;
    private static final String INVALID_ASPECT_RATIO_MESSAGE = "이미지 비율은 3:2여야 합니다.";
    private static final String INVALID_IMG_TYPE = "유효하지 않은 이미지 타입입니다.";
    private final int imgSize;
    private final int imgWidth;
    private final int imgHeight;
    private final ImgType imgType;

    public SessionImg(int imgSize, int imgWidth, int imgHeight, String type) {
        validateImgSize(imgSize);
        validateImageDimension(imgWidth, imgHeight);
        this.imgSize = imgSize;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.imgType = validateImgType(type);
    }

    private void validateImgSize(int imgSize) {
        if (imgSize > MAX_IMAGE_SIZE_KB) {
            throw new IllegalArgumentException(OVER_IMG_MAX_SIZE);
        }
    }

    private void validateImageDimension(int imgWidth, int imgHeight) {
        if (imgWidth < MIN_IMG_WIDTH && imgHeight < MIN_IMG_HEIGHT) {
            throw new IllegalArgumentException(INVALID_DIMENSIONS_MESSAGE);
        }

        if ((double) imgWidth / imgHeight != REQUIRED_ASPECT_RATIO) {
            throw new IllegalArgumentException(INVALID_ASPECT_RATIO_MESSAGE);
        }
    }

    private ImgType validateImgType(String type) {
        if (ImgType.isValidType(type)) {
            return ImgType.valueOf(type.toUpperCase());
        }
        throw new IllegalArgumentException(INVALID_IMG_TYPE);
    }
}
