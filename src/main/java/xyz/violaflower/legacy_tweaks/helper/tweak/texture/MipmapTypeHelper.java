// Legacy Mipmaps © 2025 by Permdog99 is licensed under CC BY-SA 4.0.
// To view a copy of this license, visit https://creativecommons.org/licenses/by-sa/4.0/
// Modified by Permdog99 for use under Legacy Tweaks

package xyz.violaflower.legacy_tweaks.helper.tweak.texture;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapTypes;

/**
 * Mipmap helper for creating mipmap types
 */
public class MipmapTypeHelper {

    /* Main Method */

    /**
     * Sets the mipmap type depending on the tweak setting
     * @param originals The original textures before mipped
     * @param mipmapLevel The mipmap level to mip to
     * @param cir Returns the mipmap type's method to then mip the textures
     */
    public static void setMipmapType(NativeImage[] originals, int mipmapLevel, CallbackInfoReturnable<NativeImage[]> cir) {
        if (Tweaks.MIPMAPPING.mipmapType.isEnabled()) {
            switch (Tweaks.MIPMAPPING.mipmapType.mipmapType.get()) {
                case MipmapTypes.TU1 -> {
                    cir.setReturnValue(mipmapTU1(originals, mipmapLevel));
                    cir.cancel();
                }
                case MipmapTypes.TU3 -> {
                    cir.setReturnValue(mipmapTU3(originals, mipmapLevel));
                    cir.cancel();
                }
                case MipmapTypes.TU12 -> {
                    cir.setReturnValue(mipmapTU12(originals, mipmapLevel));
                    cir.cancel();
                }
                case MipmapTypes.JAVA -> {
                }
            }
        }
    }

    /* Mipmap Type Methods */

    /**
     * Used to create TU1 to TU2 style mipmaps
     * @param originals The original textures before mipped
     * @param mipmap The mipmap level to mip to
     * @return Returns the created mipped textures
     */
    public static NativeImage[] mipmapTU1(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int color = getColor(nativeImage, l * 2 + 1, m * 2 + 1);
                            int color2 = blend(getColor(nativeImage, l * 2, m * 2), getColor(nativeImage, l * 2 + 1, m * 2), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 || i == 2 ? color : color2);
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    /**
     * Used to create TU3 to TU11 style mipmaps
     * @param originals The original textures before mipped
     * @param mipmap The mipmap level to mip to
     * @return Returns the created mipped textures
     */
    public static NativeImage[] mipmapTU3(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int color = getColor(nativeImage, l * 2 + 1, m * 2 + 1);
                            int color2 = blend(getColor(nativeImage, l * 2 + 0, m * 2 + 0), getColor(nativeImage, l * 2 + 1, m * 2 + 0), getColor(nativeImage, l * 2 + 0, m * 2 + 1), getColor(nativeImage, l * 2 + 1, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 ? color : color2);
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    /**
     * Used to create TU12 style mipmaps
     * @param originals The original textures before mipped
     * @param mipmap The mipmap level to mip to
     * @return Returns the created mipped textures
     */
    public static NativeImage[] mipmapTU12(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);
            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i == 2 ? 0 : i - 1];
                    NativeImage nativeImage2 = new NativeImage(i == 2 ? (nativeImage.getWidth() >> 2) : (nativeImage.getWidth() >> 1), i == 2 ? (nativeImage.getHeight() >> 2) : (nativeImage.getHeight() >> 1), false);

                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();
                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            int colorMipped1 = getColor(nativeImage, i == 2 ? (l * 4 + 2) : (l * 2 + 1), i == 2 ? (m * 4 + 2) : (m * 2 + 1));

                            int colorMipped3 = blend(getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            int colorMipped4 = blend(getColor(nativeImage, l * 2, m * 2), getColor(nativeImage, l * 2 + 1, m * 2), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 ? colorMipped1 : (i == 2 ? colorMipped1 : (i == 3 ? colorMipped3 : colorMipped4)));
                        }
                    }
                    nativeImages[i] = nativeImage2;
                }
            }
            return nativeImages;
        }
    }

    /**
     * Used to create Java style mipmaps
     * @param originals The original textures before mipped
     * @param mipmap The mipmap level to mip to
     * @return Returns the created mipped textures
     */
    public static NativeImage[] mipmapJava(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];
            boolean bl = hasAlpha(nativeImages[0]);

            for(int i = 1; i <= mipmap; ++i) {
                if (i < originals.length) {
                    nativeImages[i] = originals[i];
                } else {
                    NativeImage nativeImage = nativeImages[i - 1];
                    NativeImage nativeImage2 = new NativeImage(nativeImage.getWidth() >> 1, nativeImage.getHeight() >> 1, false);
                    int j = nativeImage2.getWidth();
                    int k = nativeImage2.getHeight();

                    for(int l = 0; l < j; ++l) {
                        for(int m = 0; m < k; ++m) {
                            setColor(nativeImage2, l, m, blend(getColor(nativeImage, l * 2 + 0, m * 2 + 0), getColor(nativeImage, l * 2 + 1, m * 2 + 0), getColor(nativeImage, l * 2 + 0, m * 2 + 1), getColor(nativeImage, l * 2 + 1, m * 2 + 1), bl));
                        }
                    }

                    nativeImages[i] = nativeImage2;
                }
            }

            return nativeImages;
        }
    }

    /* Color Property Methods */

    /**
     * Checks whether a texture has an alpha
     * @param image The textures to check
     * @return Returns the boolean value
     */
    private static boolean hasAlpha(NativeImage image) {
        for(int i = 0; i < image.getWidth(); ++i) {
            for(int j = 0; j < image.getHeight(); ++j) {
                if (getColor(image, i, j) >> 24 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Blends multiple textures/colors together
     * @param one First color variable
     * @param two Second color variable
     * @param three Third color variable
     * @param four Fourth color variable
     * @param checkAlpha Checks whether the texture has an alpha
     * @return Returns the new texture with blending
     */
    private static int blend(int one, int two, int three, int four, boolean checkAlpha) {
        if (checkAlpha) {
            float f = 0.0F;
            float g = 0.0F;
            float h = 0.0F;
            float i = 0.0F;
            if (one >> 24 != 0) {
                f += getColorFraction(one >> 24);
                g += getColorFraction(one >> 16);
                h += getColorFraction(one >> 8);
                i += getColorFraction(one >> 0);
            }

            if (two >> 24 != 0) {
                f += getColorFraction(two >> 24);
                g += getColorFraction(two >> 16);
                h += getColorFraction(two >> 8);
                i += getColorFraction(two >> 0);
            }

            if (three >> 24 != 0) {
                f += getColorFraction(three >> 24);
                g += getColorFraction(three >> 16);
                h += getColorFraction(three >> 8);
                i += getColorFraction(three >> 0);
            }

            if (four >> 24 != 0) {
                f += getColorFraction(four >> 24);
                g += getColorFraction(four >> 16);
                h += getColorFraction(four >> 8);
                i += getColorFraction(four >> 0);
            }

            f /= 4.0F;
            g /= 4.0F;
            h /= 4.0F;
            i /= 4.0F;
            int j = (int)(Math.pow((double)f, 0.45454545454545453) * 255.0);
            int k = (int)(Math.pow((double)g, 0.45454545454545453) * 255.0);
            int l = (int)(Math.pow((double)h, 0.45454545454545453) * 255.0);
            int m = (int)(Math.pow((double)i, 0.45454545454545453) * 255.0);
            if (j < 96) {
                j = 0;
            }

            return j << 24 | k << 16 | l << 8 | m;
        } else {
            int n = getColorComponent(one, two, three, four, 24);
            int o = getColorComponent(one, two, three, four, 16);
            int p = getColorComponent(one, two, three, four, 8);
            int q = getColorComponent(one, two, three, four, 0);
            return n << 24 | o << 16 | p << 8 | q;
        }
    }

    /**
     * Gets the color component from color fractions
     * @param one First color
     * @param two Second color
     * @param three Third color
     * @param four Fourth color
     * @param bits Fifth color
     * @return Returns the color component
     */
    private static int getColorComponent(int one, int two, int three, int four, int bits) {
        float f = getColorFraction(one >> bits);
        float g = getColorFraction(two >> bits);
        float h = getColorFraction(three >> bits);
        float i = getColorFraction(four >> bits);
        float j = (float)((double)((float)Math.pow((double)(f + g + h + i) * 0.25, 0.45454545454545453)));
        return (int)((double)j * 255.0);
    }

    /**
     * Creates the color fractions
     */
    private static final float[] COLOR_FRACTIONS = Util.make(new float[256], (list) -> {
        for(int i = 0; i < list.length; ++i) {
            list[i] = (float)Math.pow((float)i / 255.0F, 2.2);
        }
    });

    /**
     * Gets the color fractions
     * @param value Color value
     * @return Returns COLOR_FRACTIONS
     */
    private static float getColorFraction(int value) {
        return COLOR_FRACTIONS[value & 255];
    }

    /* NativeImage Conversion Methods */

    /**
     * Used to convert the "setColor" method from NativeImage to the correct MC version
     * @param nativeImage NativeImage to use
     * @param a First color variable
     * @param b Second color variable
     * @param c Third color variable
     */
    public static void setColor(NativeImage nativeImage, int a, int b, int c) {
        nativeImage.setPixelRGBA(a, b, c);
    }

    /**
     * Used to convert the "getColor" method from NativeImage to the correct MC version
     * @param nativeImage NativeImage to use
     * @param a First color variable
     * @param b Second color variable
     * @return Returns the color from the NativeImage
     */
    public static int getColor(NativeImage nativeImage, int a, int b) {
        return nativeImage.getPixelRGBA(a, b);
    }
}
