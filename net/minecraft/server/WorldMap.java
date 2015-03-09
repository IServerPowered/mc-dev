package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WorldMap extends PersistentBase {

    public int centerX;
    public int centerZ;
    public byte map;
    public byte scale;
    public byte[] colors = new byte[16384];
    public List<WorldMap.a> g = Lists.newArrayList();
    private Map<EntityHuman, WorldMap.a> i = Maps.newHashMap();
    public Map<String, MapIcon> decorations = Maps.newLinkedHashMap();

    public WorldMap(String s) {
        super(s);
    }

    public void a(double d0, double d1, int i) {
        int j = 128 * (1 << i);
        int k = MathHelper.floor((d0 + 64.0D) / (double) j);
        int l = MathHelper.floor((d1 + 64.0D) / (double) j);

        this.centerX = k * j + j / 2 - 64;
        this.centerZ = l * j + j / 2 - 64;
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.map = nbttagcompound.getByte("dimension");
        this.centerX = nbttagcompound.getInt("xCenter");
        this.centerZ = nbttagcompound.getInt("zCenter");
        this.scale = nbttagcompound.getByte("scale");
        this.scale = (byte) MathHelper.clamp(this.scale, 0, 4);
        short short0 = nbttagcompound.getShort("width");
        short short1 = nbttagcompound.getShort("height");

        if (short0 == 128 && short1 == 128) {
            this.colors = nbttagcompound.getByteArray("colors");
        } else {
            byte[] abyte = nbttagcompound.getByteArray("colors");

            this.colors = new byte[16384];
            int i = (128 - short0) / 2;
            int j = (128 - short1) / 2;

            for (int k = 0; k < short1; ++k) {
                int l = k + j;

                if (l >= 0 || l < 128) {
                    for (int i1 = 0; i1 < short0; ++i1) {
                        int j1 = i1 + i;

                        if (j1 >= 0 || j1 < 128) {
                            this.colors[j1 + l * 128] = abyte[i1 + k * short0];
                        }
                    }
                }
            }
        }

    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setByte("dimension", this.map);
        nbttagcompound.setInt("xCenter", this.centerX);
        nbttagcompound.setInt("zCenter", this.centerZ);
        nbttagcompound.setByte("scale", this.scale);
        nbttagcompound.setShort("width", (short) 128);
        nbttagcompound.setShort("height", (short) 128);
        nbttagcompound.setByteArray("colors", this.colors);
    }

    public void a(EntityHuman entityhuman, ItemStack itemstack) {
        if (!this.i.containsKey(entityhuman)) {
            WorldMap.a worldmap_a = new WorldMap.a(entityhuman);

            this.i.put(entityhuman, worldmap_a);
            this.g.add(worldmap_a);
        }

        if (!entityhuman.inventory.c(itemstack)) {
            this.decorations.remove(entityhuman.getName());
        }

        for (int i = 0; i < this.g.size(); ++i) {
            WorldMap.a worldmap_a1 = (WorldMap.a) this.g.get(i);

            if (!worldmap_a1.trackee.dead && (worldmap_a1.trackee.inventory.c(itemstack) || itemstack.y())) {
                if (!itemstack.y() && worldmap_a1.trackee.dimension == this.map) {
                    this.a(0, worldmap_a1.trackee.world, worldmap_a1.trackee.getName(), worldmap_a1.trackee.locX, worldmap_a1.trackee.locZ, (double) worldmap_a1.trackee.yaw);
                }
            } else {
                this.i.remove(worldmap_a1.trackee);
                this.g.remove(worldmap_a1);
            }
        }

        if (itemstack.y()) {
            EntityItemFrame entityitemframe = itemstack.z();
            BlockPosition blockposition = entityitemframe.getBlockPosition();

            this.a(1, entityhuman.world, "frame-" + entityitemframe.getId(), (double) blockposition.getX(), (double) blockposition.getZ(), (double) (entityitemframe.direction.b() * 90));
        }

        if (itemstack.hasTag() && itemstack.getTag().hasKeyOfType("Decorations", 9)) {
            NBTTagList nbttaglist = itemstack.getTag().getList("Decorations", 10);

            for (int j = 0; j < nbttaglist.size(); ++j) {
                NBTTagCompound nbttagcompound = nbttaglist.get(j);

                if (!this.decorations.containsKey(nbttagcompound.getString("id"))) {
                    this.a(nbttagcompound.getByte("type"), entityhuman.world, nbttagcompound.getString("id"), nbttagcompound.getDouble("x"), nbttagcompound.getDouble("z"), nbttagcompound.getDouble("rot"));
                }
            }
        }

    }

    private void a(int i, World world, String s, double d0, double d1, double d2) {
        int j = 1 << this.scale;
        float f = (float) (d0 - (double) this.centerX) / (float) j;
        float f1 = (float) (d1 - (double) this.centerZ) / (float) j;
        byte b0 = (byte) ((int) ((double) (f * 2.0F) + 0.5D));
        byte b1 = (byte) ((int) ((double) (f1 * 2.0F) + 0.5D));
        byte b2 = 63;
        byte b3;

        if (f >= (float) (-b2) && f1 >= (float) (-b2) && f <= (float) b2 && f1 <= (float) b2) {
            d2 += d2 < 0.0D ? -8.0D : 8.0D;
            b3 = (byte) ((int) (d2 * 16.0D / 360.0D));
            if (this.map < 0) {
                int k = (int) (world.getWorldData().getDayTime() / 10L);

                b3 = (byte) (k * k * 34187121 + k * 121 >> 15 & 15);
            }
        } else {
            if (Math.abs(f) >= 320.0F || Math.abs(f1) >= 320.0F) {
                this.decorations.remove(s);
                return;
            }

            i = 6;
            b3 = 0;
            if (f <= (float) (-b2)) {
                b0 = (byte) ((int) ((double) (b2 * 2) + 2.5D));
            }

            if (f1 <= (float) (-b2)) {
                b1 = (byte) ((int) ((double) (b2 * 2) + 2.5D));
            }

            if (f >= (float) b2) {
                b0 = (byte) (b2 * 2 + 1);
            }

            if (f1 >= (float) b2) {
                b1 = (byte) (b2 * 2 + 1);
            }
        }

        this.decorations.put(s, new MapIcon((byte) i, b0, b1, b3));
    }

    public Packet a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        WorldMap.a worldmap_a = (WorldMap.a) this.i.get(entityhuman);

        return worldmap_a == null ? null : worldmap_a.a(itemstack);
    }

    public void flagDirty(int i, int j) {
        super.c();
        Iterator iterator = this.g.iterator();

        while (iterator.hasNext()) {
            WorldMap.a worldmap_a = (WorldMap.a) iterator.next();

            worldmap_a.a(i, j);
        }

    }

    public WorldMap.a a(EntityHuman entityhuman) {
        WorldMap.a worldmap_a = (WorldMap.a) this.i.get(entityhuman);

        if (worldmap_a == null) {
            worldmap_a = new WorldMap.a(entityhuman);
            this.i.put(entityhuman, worldmap_a);
            this.g.add(worldmap_a);
        }

        return worldmap_a;
    }

    public class a {

        public final EntityHuman trackee;
        private boolean d = true;
        private int e = 0;
        private int f = 0;
        private int g = 127;
        private int h = 127;
        private int i;
        public int b;

        public a(EntityHuman entityhuman) {
            this.trackee = entityhuman;
        }

        public Packet a(ItemStack itemstack) {
            if (this.d) {
                this.d = false;
                return new PacketPlayOutMap(itemstack.getData(), WorldMap.this.scale, WorldMap.this.decorations.values(), WorldMap.this.colors, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
            } else {
                return this.i++ % 5 == 0 ? new PacketPlayOutMap(itemstack.getData(), WorldMap.this.scale, WorldMap.this.decorations.values(), WorldMap.this.colors, 0, 0, 0, 0) : null;
            }
        }

        public void a(int i, int j) {
            if (this.d) {
                this.e = Math.min(this.e, i);
                this.f = Math.min(this.f, j);
                this.g = Math.max(this.g, i);
                this.h = Math.max(this.h, j);
            } else {
                this.d = true;
                this.e = i;
                this.f = j;
                this.g = i;
                this.h = j;
            }

        }
    }
}
