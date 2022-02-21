package fileio;

public final class ChildrenFactory {
    private ChildrenFactory() {
    }

    /**
     *
     */
    public enum ChildType {
        BABY,
        KID,
        TEEN,
        YOUNG_ADULT
    }

    /**
     * @param childType
     * @return
     */
    public static String createChild(final ChildType childType) {
        return switch (childType) {
            case BABY -> "Baby";
            case KID -> "Kid";
            case TEEN -> "Teen";
            case YOUNG_ADULT -> "Young Adult";
        };
    }
}
