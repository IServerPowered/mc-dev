package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class ChatHoverable {

    private final ChatHoverable.a a;
    private final IChatBaseComponent b;

    public ChatHoverable(ChatHoverable.a chathoverable_a, IChatBaseComponent ichatbasecomponent) {
        this.a = chathoverable_a;
        this.b = ichatbasecomponent;
    }

    public ChatHoverable.a a() {
        return this.a;
    }

    public IChatBaseComponent b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            ChatHoverable chathoverable = (ChatHoverable) object;

            if (this.a != chathoverable.a) {
                return false;
            } else {
                if (this.b != null) {
                    if (!this.b.equals(chathoverable.b)) {
                        return false;
                    }
                } else if (chathoverable.b != null) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "HoverEvent{action=" + this.a + ", value=\'" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + (this.b != null ? this.b.hashCode() : 0);
        return i;
    }

    public static enum a {

        SHOW_TEXT("show_text", true), SHOW_ACHIEVEMENT("show_achievement", true), SHOW_ITEM("show_item", true), SHOW_ENTITY("show_entity", true);

        private static final Map<String, ChatHoverable.a> e = Maps.newHashMap();
        private final boolean f;
        private final String g;

        private a(String s, boolean flag) {
            this.g = s;
            this.f = flag;
        }

        public boolean a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public static ChatHoverable.a a(String s) {
            return (ChatHoverable.a) ChatHoverable.a.e.get(s);
        }

        static {
            ChatHoverable.a[] achathoverable_a = values();
            int i = achathoverable_a.length;

            for (int j = 0; j < i; ++j) {
                ChatHoverable.a chathoverable_a = achathoverable_a[j];

                ChatHoverable.a.e.put(chathoverable_a.b(), chathoverable_a);
            }

        }
    }
}
