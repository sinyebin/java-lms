package nextstep.courses.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum ImgType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");

    private static final Set<String> ALLOWED_EXTENSIONS;

    static {
        Set<String> extensions = new HashSet<>();
        for (ImgType type : ImgType.values()) {
            extensions.add(type.extension.toLowerCase());
        }
        ALLOWED_EXTENSIONS = Collections.unmodifiableSet(extensions);
    }

    private final String extension;

    ImgType(String extension) {
        this.extension = extension;
    }

    public static boolean isValidType(String extension) {
        return ALLOWED_EXTENSIONS.contains(extension.toLowerCase());
    }
}
