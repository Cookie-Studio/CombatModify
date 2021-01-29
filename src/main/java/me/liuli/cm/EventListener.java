package me.liuli.cm;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityMotionEvent;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        event.setAttackCooldown(CombatModify.attackCD);
        event.setKnockBack(CombatModify.baseKB);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMotion(EntityMotionEvent event) {
        if(event.getEntity().isOnGround()){
            event.getMotion().x *= CombatModify.horizontalKB_OG;
            event.getMotion().y *= CombatModify.verticalKB_OG;
            event.getMotion().z *= CombatModify.horizontalKB_OG;
        }else{
            event.getMotion().x *= CombatModify.horizontalKB;
            event.getMotion().y *= CombatModify.verticalKB;
            event.getMotion().z *= CombatModify.horizontalKB;
        }
    }
}
