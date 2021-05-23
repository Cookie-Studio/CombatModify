package me.liuli.cm.utils;

import cn.nukkit.entity.Entity;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.SimpleAxisAlignedBB;

public class MotionUtils {
    /**
     * @author CCBlueX
     * VanillaKickBypass in Fly
     */
    public static double calcGround(Entity entity){
        final AxisAlignedBB playerBoundingBox = entity.getBoundingBox();
        double blockHeight = 1D;

        for(double ground = entity.y; ground > 0D; ground -= blockHeight) {
            final AxisAlignedBB customBox = new SimpleAxisAlignedBB(playerBoundingBox.getMaxX(), ground + blockHeight, playerBoundingBox.getMaxZ(), playerBoundingBox.getMinX(), ground, playerBoundingBox.getMinZ());

            if(entity.level.getCollisionBlocks(customBox).length>0) {
                if(blockHeight <= 0.05D)
                    return ground + blockHeight;

                ground += blockHeight;
                blockHeight = 0.05D;
            }
        }

        return 0F;
    }
}
