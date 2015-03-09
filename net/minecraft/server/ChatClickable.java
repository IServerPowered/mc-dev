package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class ChatClickable {

    private final ChatClickable.a a;
    private final String b;

    public ChatClickable(ChatClickable.a chatclickable_a, String s) {
        this.a = chatclickable_a;
        this.b = s;
    }

    public ChatClickable.a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            ChatClickable chatclickable = (ChatClickable) object;

            if (this.a != chatclickable.a) {
                return false;
            } else {
                if (this.b != null) {
                    if (!this.b.equals(chatclickable.b)) {
                        return false;
                    }
                } else if (chatclickable.b != null) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public String toString() {
        return "ClickEvent{action=" + this.a + ", value=\'" + this.b + '\'' + '}';
    }

    public int hashCode() {
        int i = this.a.hashCode();

        i = 31 * i + (this.b != null ? this.b.hashCode() : 0);
        return i;
    }

    public static enum a {

        OPEN_URL("open_url", true), OPEN_FILE("open_file", false), RUN_COMMAND("run_command", true), TWITCH_USER_INFO("twitch_user_info", false), SUGGEST_COMMAND("suggest_command", true), CHANGE_PAGE("change_page", true);

        private static final Map<String, ChatClickable.a> g = Maps.newHashMap();
        private final boolean h;
        private final String i;

        private a(String s, boolean flag) {
            this.i = s;
            this.h = flag;
        }

        public boolean a() {
            return this.h;
        }

        public String b() {
            return this.i;
        }

        public static ChatClickable.a a(String s) {
            return (ChatClickable.a) ChatClickable.a.g.get(s);
        }

        static {
            ChatClickable.a[] achatclickable_a = values();
            int i = achatclickable_a.length;

            for (int j = 0; j < i; ++j) {
                ChatClickable.a chatclickable_a = achatclickable_a[j];

                ChatClickable.a.g.put(chatclickable_a.b(), chatclickable_a);
            }

        }
    }
}
