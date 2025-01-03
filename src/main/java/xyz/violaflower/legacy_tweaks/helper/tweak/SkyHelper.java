

package xyz.violaflower.legacy_tweaks.helper.tweak;

import net.minecraft.util.Mth;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

import java.awt.*;

/**
 * Sky helper for candy tweaks that change the sky
 */
// TODO get correct TU5 color
public class SkyHelper {

    /* Set Methods */

    /**
     * Sets the sunset/rise type based on the tweak setting
     *
     * @param timeOfDay  The time of day of the world
     * @param sunriseCol The sunset/rise color to manipulate
     * @return Returns the sunset/rise color type method
     */
    public static float[] setSunsetRiseColor(float timeOfDay, float[] sunriseCol) {
        switch (Tweaks.EYE_CANDY.sunsetColors.sunsetColors.get()) {
            case 1 -> {
                return getTU1SunsetRiseColor(timeOfDay, sunriseCol);
            }
            case 2 -> {
                return getTU5SunsetRiseColor(timeOfDay, sunriseCol);
            }
            case 3 -> {
                return getJavaSunsetRiseColor(timeOfDay, sunriseCol);
            }
        }
        return getJavaSunsetRiseColor(timeOfDay, sunriseCol);
    }

    /**
     * Manipulates the original stars to be smaller by 0.05f
     *
     * @param originalStars The original stars
     * @return Returns the new stars after manipulation
     */
    // CREDIT: Legacy4J 1.7.5
    public static float drawSmallerStars(float originalStars) {
        if (Tweaks.EYE_CANDY.smallerStars.isEnabled())
            return originalStars - 0.05f;
        return originalStars;
    }

    /* Sunset/Rise Color Methods */

    /**
     * Gets the TU1-styled sunset/rise color
     *
     * @param timeOfDay  The time of day of the world
     * @param sunriseCol The sunset/rise color to manipulate
     * @return Returns the manipulated color
     */
    public static float[] getTU1SunsetRiseColor(float timeOfDay, float[] sunriseCol) {
        Color base_color = new Color(63, 43, 51);
        float red = base_color.getRed() / 255f;
        float blue = base_color.getBlue() / 255f;
        float i = Mth.cos(timeOfDay * 6.2831855F) - 0.0F;
        if (i >= -0.2F && i <= 0.4F) {
            float k = (i - -0.0F) / (i <= 0.0F ? 0.2F : 0.4F) * 0.5F + 0.5F;
            float l = 1.0F - (1.0F - Mth.sin(k * 3.1415927F)) * 0.99F;
            l *= l;
            sunriseCol[0] = k * red + red;
            sunriseCol[1] = k * k * red + 0.2F;
            sunriseCol[2] = k * k * blue + 0.2F;
            sunriseCol[3] = l;
            return sunriseCol;
        } else {
            return null;
        }
    }

    /**
     * Gets the TU5-styled sunset/rise color
     *
     * @param timeOfDay  The time of day of the world
     * @param sunriseCol The sunset/rise color to manipulate
     * @return Returns the manipulated color
     */
    public static float[] getTU5SunsetRiseColor(float timeOfDay, float[] sunriseCol) { //NEEDS CHANGING SOON
        Color base_color = new Color(63, 43, 51);
        float red = base_color.getRed() / 255f;
        float blue = base_color.getBlue() / 255f;
        float i = Mth.cos(timeOfDay * 6.2831855F) - 0.0F;
        if (i >= -0.2F && i <= 0.4F) {
            float k = (i - -0.0F) / (i <= 0.0F ? 0.2F : 0.4F) * 0.5F + 0.5F;
            float l = 1.0F - (1.0F - Mth.sin(k * 3.1415927F)) * 0.99F;
            l *= l;
            sunriseCol[0] = k * red + red;
            sunriseCol[1] = k * k * red + 0.2F;
            sunriseCol[2] = k * k * blue + 0.2F;
            sunriseCol[3] = l;
            return sunriseCol;
        } else {
            return null;
        }
    }

    /**
     * Gets the Java sunset/rise color
     *
     * @param timeOfDay  The time of day of the world
     * @param sunriseCol The sunset/rise color to manipulate
     * @return Returns the manipulated color
     */
    public static float[] getJavaSunsetRiseColor(float timeOfDay, float[] sunriseCol) {
        float f = 0.4F;
        float g = Mth.cos(timeOfDay * 6.2831855F) - 0.0F;
        float h = -0.0F;
        if (g >= -0.4F && g <= 0.4F) {
            float i = (g - -0.0F) / 0.4F * 0.5F + 0.5F;
            float j = 1.0F - (1.0F - Mth.sin(i * 3.1415927F)) * 0.99F;
            j *= j;
            sunriseCol[0] = i * 0.3F + 0.7F;
            sunriseCol[1] = i * i * 0.7F + 0.2F;
            sunriseCol[2] = i * i * 0.0F + 0.2F;
            sunriseCol[3] = j;
            return sunriseCol;
        } else {
            return null;
        }
    }
}