package xyz.violaflower.legacy_tweaks.util.common.color;

// TODO add in comments
public class ColorRGBA {
    public int red;
    public int blue;
    public int green;
    public int alpha;

    public int COMPONENT_MASK = (1 << 8) - 1;

    private static final int ALPHA_COMPONENT_OFFSET = 24;
    private static final int RED_COMPONENT_OFFSET = 16;
    private static final int GREEN_COMPONENT_OFFSET = 8;
    private static final int BLUE_COMPONENT_OFFSET = 0;
    
    public ColorRGBA() {
    }

    public ColorRGBA(int red, int green, int blue, int alpha) {
        this.setAllColors(red, green, blue, alpha);
    }

    public ColorRGBA(int packedColor, int alpha) {
        this.setColorAndAlpha(packedColor, alpha);
    }

    public ColorRGBA(int packedColor) {
        this.setColor(packedColor);
    }

    public static int unpackRed(int packedColor) {
        return new ColorRGBA(packedColor).getRed();
    }

    public static int unpackGreen(int packedColor) {
        return new ColorRGBA(packedColor).getGreen();
    }

    public static int unpackBlue(int packedColor) {
        return new ColorRGBA(packedColor).getBlue();
    }

    public static int unpackAlpha(int packedColor) {
        return new ColorRGBA(packedColor).getAlpha();
    }

    public int toBGRA(int packedColor) {
        return Integer.reverseBytes(Integer.rotateLeft(packedColor, 8));
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setRed(int red) {
        this.red = red << RED_COMPONENT_OFFSET;
    }

    public void setGreen(int green) {
        this.green = green << GREEN_COMPONENT_OFFSET;
    }

    public void setBlue(int blue) {
        this.blue = blue << BLUE_COMPONENT_OFFSET;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha << ALPHA_COMPONENT_OFFSET;
    }

    public void setColor(int packedColor) {
        this.red = packedColor >> RED_COMPONENT_OFFSET & COMPONENT_MASK;
        this.green = packedColor >> GREEN_COMPONENT_OFFSET & COMPONENT_MASK;
        this.blue = packedColor >> BLUE_COMPONENT_OFFSET & COMPONENT_MASK;
        this.alpha = packedColor >> ALPHA_COMPONENT_OFFSET & COMPONENT_MASK;
    }

    public void setAllColors(int red, int green, int blue, int alpha) {
        this.red = red << RED_COMPONENT_OFFSET;
        this.green = green << GREEN_COMPONENT_OFFSET;
        this.blue = blue << BLUE_COMPONENT_OFFSET;
        this.alpha = alpha << ALPHA_COMPONENT_OFFSET;
    }

    public void setColorAndAlpha(int packedColor, int alpha) {
        this.red = packedColor >> RED_COMPONENT_OFFSET & COMPONENT_MASK;
        this.green = packedColor >> GREEN_COMPONENT_OFFSET & COMPONENT_MASK;
        this.blue = packedColor >> BLUE_COMPONENT_OFFSET & COMPONENT_MASK;
        this.alpha = alpha << ALPHA_COMPONENT_OFFSET;
    }

    public void setTransparent() {
        this.alpha = 0;
    }

    public void setOpaque() {
        this.alpha = 255 << ALPHA_COMPONENT_OFFSET;
    }

    public int getPackedColor() {
        return (this.alpha & COMPONENT_MASK) << ALPHA_COMPONENT_OFFSET |
                (this.red & COMPONENT_MASK) << RED_COMPONENT_OFFSET |
                (this.green & COMPONENT_MASK) << GREEN_COMPONENT_OFFSET |
                (this.blue & COMPONENT_MASK) << BLUE_COMPONENT_OFFSET;
    }
}
