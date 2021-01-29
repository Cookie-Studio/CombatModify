package me.liuli.cm;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CombatModify extends PluginBase {
    public static int attackCD=1;
    public static float baseKB=1;
    public static float horizontalKB_OG=1,verticalKB_OG=1;
    public static float horizontalKB=0.5F,verticalKB=0.5F;

    @Override
    public void onEnable() {
        File cfgFile=new File(this.getDataFolder(),"config.yml");
        if(!cfgFile.exists()){
            try {
                this.getDataFolder().mkdir();
                Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cfgFile), StandardCharsets.UTF_8));
                writer.write("ac: 1\n"+
                        "basekb: 1\n"+
                        "hkb_g: 1\n"+
                        "vkb_g: 1\n"+
                        "hkb: 0.5\n"+
                        "vkb: 0.5");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Config config=new Config(cfgFile);
        attackCD=config.getInt("ac");
        baseKB= (float) config.getDouble("basekb");
        horizontalKB_OG= (float) config.getDouble("hkb_g");
        verticalKB_OG= (float) config.getDouble("vkb_g");
        horizontalKB= (float) config.getDouble("hkb");
        verticalKB= (float) config.getDouble("vkb");
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
}
