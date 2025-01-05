package xyz.violaflower.legacy_tweaks;

//? if neoforge
/*import net.neoforged.fml.common.Mod;*/
//? if fabric {
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.networking.CustomPayloadTypeProvider;
//?}
import net.minecraft.client.Minecraft;
import net.minecraft.commands.Commands;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.violaflower.legacy_tweaks.client.LegacyTweaksClient;
import xyz.violaflower.legacy_tweaks.client.gui.screen.LTScreen;
import xyz.violaflower.legacy_tweaks.items.ItemManager;
import xyz.violaflower.legacy_tweaks.networking.LegacyTweaksNetworking;
import xyz.violaflower.legacy_tweaks.networking.NetworkingAbstractions;
import xyz.violaflower.legacy_tweaks.tweaks.TweakManager;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

// TODO properly credit Legacy4J for gamma
//? if neoforge
/*@Mod(LegacyTweaks.MOD_ID)*/
public final class LegacyTweaks {
    public static final String MOD_ID = "legacy_tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //? if neoforge {
    /*public LegacyTweaks() {
        init();
    }
    *///?}

    public static void init() {
        LOGGER.info("initialized Legacy Tweaks successfully");
        ItemManager.init();

        TweakManager tweakManager = TweakManager.getInstance();
        tweakManager.register(Tweaks.WINDOW_TITLE);
        tweakManager.register(Tweaks.STUB);
        tweakManager.register(Tweaks.MAP_CHANGES);
        tweakManager.register(Tweaks.GAMMA);
        tweakManager.register(Tweaks.F3INFO);
        tweakManager.register(Tweaks.LEGACY_CHAT);
        tweakManager.register(Tweaks.CRASH);
        tweakManager.register(Tweaks.LEGACY_ATTACK);
        tweakManager.register(Tweaks.LEGACY_UI);
        tweakManager.register(Tweaks.EYE_CANDY);
        tweakManager.register(Tweaks.LEGACY_FOG);
        tweakManager.register(Tweaks.MIPMAPPING);

        // this only works on Fabric!
        //? if fabric {
            ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
                dispatcher.register(ClientCommandManager.literal("ltscreen").executes(c -> {Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new LTScreen(Minecraft.getInstance().screen)));return 0;}));
                dispatcher.register(ClientCommandManager.literal("testc2s").executes(c -> {
                    ClientPlayNetworking.send(new CoolPacket2(90, 25.50));
                    return 0;
                }));
            });
        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> {
            dispatcher.register(Commands.literal("tests2c").executes(c -> {
                ServerPlayNetworking.getSender(c.getSource().getPlayerOrException()).sendPacket(new CoolPacket(1));
                return 0;
            }));
        });
        //?}

        // TODO check for client side
        LegacyTweaksClient.init();

        //? if fabric
        LegacyTweaksNetworking.init();
    }

    private static void registerNetworkingCodecs() {
        NetworkingAbstractions.registerCodec(CoolPacket.TYPE, CoolPacket.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_S2C);
        NetworkingAbstractions.registerCodec(CoolPacket2.TYPE, CoolPacket2.STREAM_CODEC, NetworkingAbstractions.PayloadType.PLAY_C2S);
    }
    public static void initNetworking() {
        registerNetworkingCodecs();
        NetworkingAbstractions.Server.playToServer(CoolPacket2.TYPE, (payload, context) -> {
            System.out.println("[SERVER] received packet " + payload);
        });
    }

    public record CoolPacket(int number) implements CustomPacketPayload {
        public static final Type<CoolPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "cool_packet"));
        public static final StreamCodec<FriendlyByteBuf, CoolPacket> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolPacket::number, CoolPacket::new);

        @Override
        public Type<CoolPacket> type() {
            return TYPE;
        }
    }

    public record CoolPacket2(int number, double number2) implements CustomPacketPayload {
        public static final Type<CoolPacket2> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(LegacyTweaks.MOD_ID, "cool_packet2"));
        public static final StreamCodec<FriendlyByteBuf, CoolPacket2> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, CoolPacket2::number, ByteBufCodecs.DOUBLE, CoolPacket2::number2, CoolPacket2::new);

        @Override
        public Type<CoolPacket2> type() {
            return TYPE;
        }
    }
}
