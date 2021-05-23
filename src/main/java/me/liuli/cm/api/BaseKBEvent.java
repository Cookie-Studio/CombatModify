package me.liuli.cm.api;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;

public class BaseKBEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public int attackCD;
    public float baseKB;

    public BaseKBEvent(int attackCD, float baseKB){
        this.attackCD=attackCD;
        this.baseKB=baseKB;
    }

    public void applyToEvent(EntityDamageByEntityEvent event){
        event.setAttackCooldown(attackCD);
        event.setKnockBack(baseKB);
    }
}
