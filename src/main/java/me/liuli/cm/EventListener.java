package me.liuli.cm;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityMotionEvent;
import me.liuli.cm.api.BaseKBEvent;
import me.liuli.cm.api.KBEvent;

public class EventListener implements Listener {
    private Server server=Server.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        BaseKBEvent baseKBEvent=new BaseKBEvent(CombatModify.attackCD,CombatModify.baseKB);

        server.getPluginManager().callEvent(baseKBEvent);

        if(!baseKBEvent.isCancelled()){
            baseKBEvent.applyToEvent(event);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMotion(EntityMotionEvent event) {
        KBEvent kbEvent;

        if(event.getEntity().isOnGround()){
            kbEvent=new KBEvent(CombatModify.horizontalKB_OG,CombatModify.verticalKB_OG,CombatModify.horizontalKB_OG);
        }else{
            kbEvent=new KBEvent(CombatModify.horizontalKB,CombatModify.verticalKB,CombatModify.horizontalKB);
        }

        server.getPluginManager().callEvent(kbEvent);

        if(!kbEvent.isCancelled()){
            kbEvent.applyToEvent(event);
        }
    }
}
