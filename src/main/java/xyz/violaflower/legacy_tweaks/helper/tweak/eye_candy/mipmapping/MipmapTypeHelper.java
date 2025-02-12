package xyz.violaflower.legacy_tweaks.helper.tweak.eye_candy.mipmapping;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.violaflower.legacy_tweaks.mixin.client.accessor.MipmapGeneratorAccessor;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;
import xyz.violaflower.legacy_tweaks.tweaks.enums.MipmapTypes;
import xyz.violaflower.legacy_tweaks.util.common.color.ColorRGBA;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Mipmap helper for creating mipmap types
 */
public class MipmapTypeHelper {
    public static ResourceLocation currentResourceLocation;

    /* Main Method */

    /**
     * Sets the mipmap type depending on the tweak setting
     * @param originals The original textures before mipped
     * @param mipmapLevel The mipmap level to mip to
     * @param cir Returns the mipmap type's method to then mip the textures
     */
    public static void setMipmapType(NativeImage[] originals, int mipmapLevel, ResourceLocation resourceLocation, CallbackInfoReturnable<NativeImage[]> cir) throws IOException {
        if (Tweaks.EYE_CANDY.mipmapping.mipmapType.isEnabled()) {
            switch (Tweaks.EYE_CANDY.mipmapping.mipmapType.mipmapType.get()) {
                case MipmapTypes.TU1 -> {
                    cir.setReturnValue(mipmapTU1(originals, mipmapLevel));
                    currentResourceLocation = null;
                    cir.cancel();
                }
                case MipmapTypes.TU3 -> {
                    cir.setReturnValue(mipmapTU3(originals, mipmapLevel));
                    currentResourceLocation = null;
                    cir.cancel();
                }
                case MipmapTypes.TU12 -> {
                    cir.setReturnValue(mipmapTU12(originals, mipmapLevel));
                    currentResourceLocation = null;
                    cir.cancel();
                }
                case MipmapTypes.LCE_ACCURATE -> {
                    cir.setReturnValue(mipmapAccurateFull(originals, mipmapLevel));
                    currentResourceLocation = null;
                    cir.cancel();
                }

                case MipmapTypes.JAVA -> {
                    cir.setReturnValue(mipmapJava(originals, mipmapLevel));
                    currentResourceLocation = null;
                    cir.cancel();
                }
            }
            // uncomment to export the generated mipmaps
            // exportGeneratedMipmaps(cir.getReturnValue(), resourceLocation);
        }
    }

    private static void exportGeneratedMipmaps(NativeImage[] images, ResourceLocation resourceLocation) throws IOException {
        if (images == null) return;
        Path path = Path.of("images/");
        path.toFile().mkdirs();
        int i = 0;
        for (NativeImage nativeImage : images) {
            Path resolve = path.resolve(resourceLocation + "/" + i + ".png");
            resolve.getParent().toFile().mkdirs();
            nativeImage.writeToFile(resolve);
            i++;
        }
    }

    // Dynamically added to MipmapGenerator via a mixin plugin
    public static void aastoreMarker() {}

    /// Gets the mipped texture based on whether there is a manual mipped texture or if accuracy mode has been set. If neither, automatic method is used
    public static NativeImage maybeGetMipmapForLevel(int mipmap, NativeImage previousOriginal, NativeImage original, ResourceLocation currentResourceLocation) {
        if (!Tweaks.EYE_CANDY.mipmapping.manualMipmapping.isEnabled()) return original;
        if (currentResourceLocation == null) return original;
        Optional<NativeImage> manualImage = fromResource(ResourceLocation.fromNamespaceAndPath(currentResourceLocation.getNamespace(), "textures/mipmaps/" + currentResourceLocation.getPath() + "/" + mipmap + ".png"));
        Optional<File> accurateFile = fromFileResource(ResourceLocation.fromNamespaceAndPath(currentResourceLocation.getNamespace(), "textures/mipmaps/" + currentResourceLocation.getPath() + "/accurate.txt"));

        if (manualImage.isPresent()) {
            return getManualMipmap(mipmap, original, manualImage.get(), currentResourceLocation);
        } else if (accurateFile.isPresent() & Tweaks.EYE_CANDY.mipmapping.mipmapType.mipmapType.get() != MipmapTypes.LCE_ACCURATE) {
            return mipmapAccurate(previousOriginal);
        } else {
            return original;
        }
    }

    /// Gets the manual mipped image for use within the game, and fails if the image size is not correct
    public static NativeImage getManualMipmap(int mipmap, NativeImage original, NativeImage manualImage, ResourceLocation currentResourceLocation) {
        int j = original.getWidth();
        int k = original.getHeight();
        int a = manualImage.getWidth();
        int b = manualImage.getHeight();
        if (a != j && b != k) {
            System.err.println("Using automatic mipmapping for %s at level %s, width and height are incorrect: %sx%s instead of %sx%s".formatted(currentResourceLocation.getPath(), mipmap, a, b, j, k));
            return original;
        } else if (a != j) {
            System.err.println("Using automatic mipmapping for %s at level %s, width is incorrect: %s instead of %s".formatted(currentResourceLocation.getPath(), mipmap, a, j));
            return original;
        } else if (b != k) {
            System.err.println("Using automatic mipmapping for %s at level %s, height is incorrect: %s instead of %s".formatted(currentResourceLocation.getPath(), mipmap, b, k));
            return original;
        } else {
            return manualImage;
        }
    }

    /// Gets an {@link Optional}`<`{@link NativeImage}`>` from a {@link ResourceLocation}
    private static Optional<NativeImage> fromResource(ResourceLocation resourceLocation) {
        Optional<Resource> resource1 = Minecraft.getInstance().getResourceManager().getResource(resourceLocation);
        if (resource1.isEmpty()) return Optional.empty();
        Resource resource = resource1.get();
        NativeImage nativeImage;
        try (InputStream inputStream = resource.open()){


            try {
                nativeImage = NativeImage.read(inputStream);
            } catch (Throwable var10) {
                try {
                    inputStream.close();
                } catch (Throwable var8) {
                    var10.addSuppressed(var8);
                }

                throw var10;
            }
        } catch (IOException var11) {
            System.err.println("Using missing texture, unable to load %s: %s".formatted(resourceLocation, var11));
            return Optional.empty();
        }
        return Optional.of(nativeImage);
    }

    /// Gets an {@link Optional}`<`{@link File}`>` from a {@link ResourceLocation}
    private static Optional<File> fromFileResource(ResourceLocation resourceLocation) {
        Optional<Resource> resource1 = Minecraft.getInstance().getResourceManager().getResource(resourceLocation);
        if (resource1.isEmpty()) return Optional.empty();
        Resource resource = resource1.get();
        File file;
        try (InputStream inputStream = resource.open()){

            try {
                file = new File(resourceLocation.getPath());
            } catch (Throwable var10) {
                try {
                    inputStream.close();
                } catch (Throwable var8) {
                    var10.addSuppressed(var8);
                }

                throw var10;
            }
        } catch (IOException var11) {
            System.err.println("Failed to find file, setting empty.".formatted(resourceLocation, var11));
            return Optional.empty();
        }
        return Optional.of(file);
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
                            int color2 = vanillaBlend(getColor(nativeImage, l * 2, m * 2), getColor(nativeImage, l * 2 + 1, m * 2), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 || i == 2 ? color : color2);
                        }
                    }
                    nativeImages[i] = maybeGetMipmapForLevel(i, nativeImage, nativeImage2, currentResourceLocation);
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
                            int color2 = vanillaBlend(getColor(nativeImage, l * 2 + 0, m * 2 + 0), getColor(nativeImage, l * 2 + 1, m * 2 + 0), getColor(nativeImage, l * 2 + 0, m * 2 + 1), getColor(nativeImage, l * 2 + 1, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 ? color : color2);
                        }
                    }
                    nativeImages[i] = maybeGetMipmapForLevel(i, nativeImage, nativeImage2, currentResourceLocation);
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

                            int colorMipped3 = vanillaBlend(getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            int colorMipped4 = vanillaBlend(getColor(nativeImage, l * 2, m * 2), getColor(nativeImage, l * 2 + 1, m * 2), getColor(nativeImage, l * 2, m * 2 + 1), getColor(nativeImage, l * 2, m * 2 + 1), bl);
                            setColor(nativeImage2, l, m, i == 1 ? colorMipped1 : (i == 2 ? colorMipped1 : (i == 3 ? colorMipped3 : colorMipped4)));
                        }
                    }
                    nativeImages[i] = maybeGetMipmapForLevel(i, nativeImage, nativeImage2, currentResourceLocation);
                }
            }
            return nativeImages;
        }
    }

    /// Takes a {@link NativeImage} and mips it accurately to LCE
    public static NativeImage mipmapAccurate(NativeImage original) {
        NativeImage newImage = new NativeImage(original.getWidth() >> 1, original.getHeight() >> 1, false);
        int j = newImage.getWidth();
        int k = newImage.getHeight();
        for(int l = 0; l < j; ++l) {
            for (int m = 0; m < k; ++m) {
                setColor(newImage, l, m, crispBlend(getColor(original, l * 2 + 1, m * 2), getColor(original, l * 2, m * 2 + 1)));
            }
        }
        return newImage;
    }

    /**
     * Used to create Legacy style mipmaps
     * @param originals The original textures before mipped
     * @param mipmap The mipmap level to mip to
     * @return Returns the created mipped textures
     */
    public static NativeImage[] mipmapAccurateFull(NativeImage[] originals, int mipmap) {
        if (mipmap + 1 <= originals.length) {
            return originals;
        } else {
            NativeImage[] nativeImages = new NativeImage[mipmap + 1];
            nativeImages[0] = originals[0];

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
                            setColor(nativeImage2, l, m, crispBlend(getColor(nativeImage, l * 2 + 1, m * 2), getColor(nativeImage, l * 2, m * 2 + 1)));
                        }
                    }

                    nativeImages[i] = maybeGetMipmapForLevel(i, nativeImage, nativeImage2, currentResourceLocation);
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
     * @deprecated This isn't used.
     */
    @Deprecated
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
                            setColor(nativeImage2, l, m, vanillaBlend(getColor(nativeImage, l * 2 + 0, m * 2 + 0), getColor(nativeImage, l * 2 + 1, m * 2 + 0), getColor(nativeImage, l * 2 + 0, m * 2 + 1), getColor(nativeImage, l * 2 + 1, m * 2 + 1), bl));
                        }
                    }

                    nativeImages[i] = maybeGetMipmapForLevel(i, nativeImage, nativeImage2, currentResourceLocation);
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
     * Blends four packed colors together using the vanilla MC method
     * @param one First packed color
     * @param two Second packed color
     * @param three Third packed color
     * @param four Fourth packed color
     * @param checkAlpha Checks whether the texture has an alpha
     * @return Returns the newly blended packed color
     */
    private static int vanillaBlend(int one, int two, int three, int four, boolean checkAlpha) {
        return MipmapGeneratorAccessor.legacyTweaks$getAlphaBlend(one, two, three, four, checkAlpha);
    }

    /**
     * Blends two packed colors together using a crisp system. Based on Legacy Console Edition's method for blending
     * @param one First packed color
     * @param two Second packed color
     * @return Returns the newly blended packed color
     */
    public static int crispBlend(int one, int two) {
        ColorRGBA newColor = new ColorRGBA();
        ColorRGBA colorOne = new ColorRGBA(one);
        ColorRGBA colorTwo = new ColorRGBA(two);

        int packedColor;

        if ((colorOne.getAlpha() + colorTwo.getAlpha()) < 255) {
            packedColor = (colorOne.getRed() + colorTwo.getRed()) / 2 << 16 | (colorOne.getGreen() + colorTwo.getGreen()) / 2 << 8 | (colorOne.getBlue() + colorTwo.getBlue()) / 2;
        } else if (colorTwo.getAlpha() < colorOne.getAlpha()) {
            packedColor = (colorOne.getRed() * 255 + colorTwo.getRed()) / 256 << 16 | 0xff000000 | (colorOne.getGreen() * 255 + colorTwo.getGreen()) / 256 << 8 | (colorOne.getBlue() * 255 + colorTwo.getBlue()) / 256;
        } else {
            packedColor = (colorOne.getRed() + colorTwo.getRed() * 255) / 256 << 16 | 0xff000000 | (colorOne.getGreen() + colorTwo.getGreen() * 255) / 256 << 8 | (colorOne.getBlue() + colorTwo.getBlue() * 255) / 256;
        }

        newColor.setColor(packedColor);

        return newColor.getPackedColor();
    }

    /* NativeImage Conversion Methods */

    /**
     * Used to convert the "setColor" method from {@link NativeImage} for convenience
     * @param nativeImage NativeImage to use
     * @param x X position for the specific color
     * @param y Y position for the specific color
     * @param color Packed color
     */
    public static void setColor(NativeImage nativeImage, int x, int y, int color) {
        nativeImage.setPixelRGBA(x, y, color);
    }

    /**
     * Used to convert the "setColor" method from {@link NativeImage} for convenience
     * @param nativeImage NativeImage to use
     * @param x X position for the specific color
     * @param y Y position for the specific color
     * @return Returns the packed color
     */
    public static int getColor(NativeImage nativeImage, int x, int y) {
        return nativeImage.getPixelRGBA(x, y);
    }
}
