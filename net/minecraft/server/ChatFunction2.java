package net.minecraft.server;

import com.google.common.base.Function;

final class ChatFunction2 implements Function {

    public IChatBaseComponent a(IChatBaseComponent ichatbasecomponent) {
        IChatBaseComponent ichatbasecomponent1 = ichatbasecomponent.f();

        ichatbasecomponent1.setChatModifier(ichatbasecomponent1.getChatModifier().n());
        return ichatbasecomponent1;
    }

    public Object apply(Object object) {
        return this.a((IChatBaseComponent) object);
    }
}
