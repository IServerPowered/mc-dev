package net.minecraft.server;

import com.google.common.base.Function;

final class DisplayStateFunctionCobbleVariant implements Function {

    public String a(ItemStack itemstack) {
        return EnumCobbleVariant.a(itemstack.getData()).c();
    }

    public Object apply(Object object) {
        return this.a((ItemStack) object);
    }
}
