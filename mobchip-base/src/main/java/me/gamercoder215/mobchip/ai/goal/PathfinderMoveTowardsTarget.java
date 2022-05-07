package me.gamercoder215.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

import me.gamercoder215.mobchip.ai.SpeedModifier;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;

/**
 * Represents a Pathfinder for a Creature to move towards its target
 */
public final class PathfinderMoveTowardsTarget extends Pathfinder implements SpeedModifier, Ranged {

    /**
     * Default Range for moving towards a target
     */
    public static final float DEFAULT_RANGE = 50;

    private float range;
    private double speedMod;

    /**
     * Constructs a PathfinderMoveTowardsTarget from a NMS MoveTowardsTargetGoal.
     * @param g Goal to use
     */
    public PathfinderMoveTowardsTarget(@NotNull MoveTowardsTargetGoal g) {
        super(Pathfinder.getCreature(g, "a"));

        this.range = Pathfinder.getFloat(g, "g");
        this.speedMod = Pathfinder.getDouble(g, "f");
    }

    /**
     * Creates a PathfinderMoveTowardsTarget with the default speed modifier.
     * @param c Creature to use
     */
    public PathfinderMoveTowardsTarget(@NotNull Creature c) {
        this(c, DEFAULT_SPEED_MODIFIER);
    }

    /**
     * Creates a PathfinderMoveTowardsTarget with the {@link #DEFAULT_RANGE}.
     * @param c Creature to use.
     * @param speedMod Speed Modifier while moving
     */
    public PathfinderMoveTowardsTarget(@NotNull Creature c, double speedMod) {
        this(c, speedMod, DEFAULT_RANGE);
    }

    /**
     * Creates a PathfinderMoveTowardsTarget.
     * @param c Creature to use
     * @param speedMod Speed Modifier while moving
     * @param range Range to look for target
     */
    public PathfinderMoveTowardsTarget(@NotNull Creature c, double speedMod, float range) {
        super(c);

        this.speedMod = speedMod;
        this.range = range;
    }

    @Override
    public float getRange() {
        return this.range;
    }

    @Override
    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public double getSpeedModifier() {
        return this.speedMod;
    }

    @Override
    public void setSpeedModifier(double mod) {
        this.speedMod = mod;
    }

    @Override
    public MoveTowardsTargetGoal getHandle() {
        return new MoveTowardsTargetGoal((PathfinderMob) nmsEntity, speedMod, range);
    }

}
