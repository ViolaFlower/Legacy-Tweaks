package xyz.violaflower.legacy_tweaks.util.common.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;

public class SoundUtil {

    private static final Minecraft minecraft = Minecraft.getInstance();
    public static final RandomSource randomPitch = SoundInstance.createUnseededRandom();

    public static void playSound(SoundEvent sound, SoundSource soundSource, float soundPitch, float soundVolume){
        minecraft.getSoundManager().play(new SimpleSoundInstance(sound.getLocation(), soundSource, soundVolume,soundPitch, randomPitch, false, 0, SoundInstance.Attenuation.NONE, 0.0, 0.0, 0.0, true));
    }
    public static void playFullVolumeSound(SoundEvent sound, SoundSource soundSource, float soundPitch){
        playSound(sound, soundSource, soundPitch, 1.0f);
    }
    public static void playRandomSound(SoundEvent sound, SoundSource soundSource, float soundPitch){
        playFullVolumeSound(sound, soundSource, soundPitch + (randomPitch.nextFloat() - 0.5f) / 10);
    }
    public static void playFullPitchSound(SoundEvent sound, SoundSource soundSource){
        playFullVolumeSound(sound, soundSource, 1.0f);
    }
}
