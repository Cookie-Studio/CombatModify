package me.liuli.cm.api;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.entity.EntityMotionEvent;

public class KBEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public double xPosMultipler;
    public double yPosMultipler;
    public double zPosMultipler;

    public KBEvent(double xPosMultipler, double yPosMultipler, double zPosMultipler){
        this.xPosMultipler = xPosMultipler;
        this.yPosMultipler = yPosMultipler;
        this.zPosMultipler = zPosMultipler;
    }

    public void applyToEvent(EntityMotionEvent event){
        event.getMotion().x *= xPosMultipler;
        event.getMotion().y *= yPosMultipler;
        event.getMotion().z *= zPosMultipler;
    }
}
