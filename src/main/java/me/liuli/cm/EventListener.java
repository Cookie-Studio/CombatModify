package me.liuli.cm;

import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityMotionEvent;
import me.liuli.cm.api.BaseKBEvent;
import me.liuli.cm.api.KBEvent;
import me.liuli.cm.utils.MotionUtils;

public class EventListener implements Listener {
    private final Server server=Server.getInstance();

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
            double height=MotionUtils.calcGround(event.getEntity());
            if(height>CombatModify.heightLimit){
                double pct=(CombatModify.overheightReduce-(height-CombatModify.heightLimit))/CombatModify.overheightReduce;
                double hor=CombatModify.doReduceHorizonal?CombatModify.horizontalKB*pct:CombatModify.horizontalKB;
                kbEvent=new KBEvent(hor,CombatModify.verticalKB*pct,hor);
            }else{
                kbEvent=new KBEvent(CombatModify.horizontalKB,CombatModify.verticalKB,CombatModify.horizontalKB);
            }
        }

        server.getPluginManager().callEvent(kbEvent);

        if(!kbEvent.isCancelled()){
            kbEvent.applyToEvent(event);
        }
    }
}
