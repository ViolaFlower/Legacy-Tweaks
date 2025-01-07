package xyz.violaflower.legacy_tweaks.network.payload;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import xyz.violaflower.legacy_tweaks.util.ModAsset;

public record TweakStatesPayload(FriendlyByteBuf byteBuf) implements CustomPacketPayload {
	public static final Type<TweakStatesPayload> TYPE = new Type<>(ModAsset.getResourceLocation("tweak_states_payload"));
	public static final StreamCodec<FriendlyByteBuf, TweakStatesPayload> STREAM_CODEC = new StreamCodec<>() {
		@Override
		public TweakStatesPayload decode(FriendlyByteBuf object) {
			FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
			buf.writeBytes(object.readByteArray());
			return new TweakStatesPayload(buf);
		}

		@Override
		public void encode(FriendlyByteBuf object, TweakStatesPayload object2) {
			object.writeByteArray(object2.byteBuf().array());
		}
	};
	@Override
	public Type<TweakStatesPayload> type() {
		return TYPE;
	}
}
