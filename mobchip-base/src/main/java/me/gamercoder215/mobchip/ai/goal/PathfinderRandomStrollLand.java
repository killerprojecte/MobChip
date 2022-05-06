package me.gamercoder215.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

import me.gamercoder215.mobchip.ai.Probable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

/**
 * Represents a Pathfinder to randomly stroll <strong>only on land</strong>.
 */
public final class PathfinderRandomStrollLand extends PathfinderRandomStroll implements Probable {
    
    /**
     * The default probability to stroll.
     */
    public static final float DEFAULT_PROBABILITY = 0.001F;

    private float probability;

    public PathfinderRandomStrollLand(@NotNull WaterAvoidingRandomStrollGoal g) {
        super(g);

        this.probability = Pathfinder.getFloat(g, "j");
    }

    /**
     * Constructs a PathfinderRandomStrollLand with no speed modifier.
     * @param c Creature to use
     */
    public PathfinderRandomStrollLand(@NotNull Creature c) {
        this(c, 1);
    }

    /**
     * Constructs a PathfinderRandomStrollLand with {@link #DEFAULT_PROBABILITY}.
     * @param c Creature to use
     * @param speedMod Speed Modifier while strolling
     */
    public PathfinderRandomStrollLand(@NotNull Creature c, double speedMod) {
        this(c, speedMod, DEFAULT_PROBABILITY);
    }

    /**
     * Constructs a PathfinderRandomStrollLand
     * @param c Creature to use
     * @param speedMod Speed Modifier while strolling
     * @param probability probability (between 0.0 - 1.0) of initiating a stroll
     */
    public PathfinderRandomStrollLand(@NotNull Creature c, double speedMod, float probability) {
        super(c, speedMod);

        this.probability = probability;
    }

    @Override
    public float getProbability() {
        return this.probability;
    }

    @Override
    public void setProbability(float prob) {
        this.probability = prob;
    }

    @Override
    public WaterAvoidingRandomStrollGoal getHandle() {
        return new WaterAvoidingRandomStrollGoal((PathfinderMob) nmsEntity, this.getSpeedModifier(), probability);
    }

}
