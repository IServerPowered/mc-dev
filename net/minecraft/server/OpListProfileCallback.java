package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.yggdrasil.ProfileNotFoundException;

final class OpListProfileCallback implements ProfileLookupCallback {

    final MinecraftServer a;
    final OpList b;

    OpListProfileCallback(MinecraftServer minecraftserver, OpList oplist) {
        this.a = minecraftserver;
        this.b = oplist;
    }

    public void onProfileLookupSucceeded(GameProfile gameprofile) {
        this.a.getUserCache().a(gameprofile);
        this.b.add(new OpListEntry(gameprofile, this.a.p()));
    }

    public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
        NameReferencingFileConverter.a().warn("Could not lookup oplist entry for " + gameprofile.getName(), (Throwable) exception);
        if (!(exception instanceof ProfileNotFoundException)) {
            throw new FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception, (PredicateEmptyList) null);
        }
    }
}
