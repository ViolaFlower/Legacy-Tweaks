package xyz.violaflower.legacy_tweaks;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class LegacyTweaksMixinPlugin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {

	}

	@Override
	public String getRefMapperConfig() {
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return true;
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

	}

	@Override
	public List<String> getMixins() {
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// we need to inject after an AASTORE instruction
		if (mixinClassName.equals("xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.mipmapping.MipmapGeneratorMixin")) {
			for (MethodNode method : targetClass.methods) {
				for (AbstractInsnNode instruction : method.instructions) {
					if (instruction.getOpcode() == Opcodes.AASTORE) {
						// add a call to aastoreMarker()
						method.instructions.insert(instruction, new MethodInsnNode(Opcodes.INVOKESTATIC, "xyz/violaflower/legacy_tweaks/helper/tweak/eye_candy/mipmapping/MipmapTypeHelper", "aastoreMarker", "()V"));
					}
				}
			}
		}
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}
}
