package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

public class PacketStatusOutServerInfo implements Packet<PacketStatusOutListener> {

    private static final Gson a = (new GsonBuilder()).registerTypeAdapter(ServerPing.c.class, new ServerPing.c.a()).registerTypeAdapter(ServerPing.a.class, new ServerPing.a.a()).registerTypeAdapter(ServerPing.class, new ServerPing.b()).registerTypeHierarchyAdapter(IChatBaseComponent.class, new IChatBaseComponent.a()).registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.a()).registerTypeAdapterFactory(new ChatTypeAdapterFactory()).create();
    private ServerPing b;

    public PacketStatusOutServerInfo() {}

    public PacketStatusOutServerInfo(ServerPing serverping) {
        this.b = serverping;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.b = (ServerPing) PacketStatusOutServerInfo.a.fromJson(packetdataserializer.c(32767), ServerPing.class);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(PacketStatusOutServerInfo.a.toJson((Object) this.b));
    }

    public void a(PacketStatusOutListener packetstatusoutlistener) {
        packetstatusoutlistener.a(this);
    }

    public void a(PacketListener packetlistener) {
        this.a((PacketStatusOutListener) packetlistener);
    }
}
