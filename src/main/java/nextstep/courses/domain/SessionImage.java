package nextstep.courses.domain;

public class SessionImage {
    private static final long MAX_IMAGE_SIZE_KB = 1024;
    private static final String OVER_IMG_MAX_SIZE = "이미지 크기가 1MB(1024KB)를 초과합니다.";
    private static final int MIN_IMG_WIDTH = 300;
    private static final int MIN_IMG_HEIGHT = 200;
    private static final String INVALID_DIMENSIONS_MESSAGE = "이미지 크기는 최소 300x200 이상이어야 합니다.";
    private static final String INVALID_ASPECT_RATIO_MESSAGE = "이미지 비율은 3:2여야 합니다.";
    private static final String INVALID_IMG_TYPE = "유효하지 않은 이미지 타입입니다.";
    private final int imgSize;
    private final int imgWidth;
    private final int imgHeight;
    private final ImageType imageType;

    public SessionImage(int imgSize, int imgWidth, int imgHeight, String type) {
        validateImgSize(imgSize);
        validateImageDimension(imgWidth, imgHeight);
        this.imgSize = imgSize;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        this.imageType = ImageType.fromString(type);
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

        if (3 * imgHeight != 2 * imgWidth) {
            throw new IllegalArgumentException(INVALID_ASPECT_RATIO_MESSAGE);
        }
    }
}
