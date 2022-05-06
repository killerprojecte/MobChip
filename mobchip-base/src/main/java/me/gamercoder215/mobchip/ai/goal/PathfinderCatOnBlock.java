package me.gamercoder215.mobchip.ai.goal;

import java.lang.reflect.Field;

import org.bukkit.entity.Cat;
import org.jetbrains.annotations.NotNull;

import me.gamercoder215.mobchip.ai.SpeedModifier;
import net.minecraft.world.entity.ai.goal.CatSitOnBlockGoal;

/**
 * Represents a Pathfinder for a Cat to sit on a block
 */
public final class PathfinderCatOnBlock extends Pathfinder implements SpeedModifier {

	private double speedMod;

	/**
	 * Constructs a PathfinderCatOnBlock from a NMS CatSitOnBlockGoal.
	 * @param g NMS Goal
	 */
	public PathfinderCatOnBlock(@NotNull CatSitOnBlockGoal g) {
		super(Pathfinder.getEntity(g, "g"));

		try {
			Field a = CatSitOnBlockGoal.class.getSuperclass().getDeclaredField("b");
			a.setAccessible(true);
			this.speedMod = a.getDouble(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs a PathfinderCatOnBlock.
	 * @param cat Cat to use
	 * @param speedMod Speed Modifier
	 */
	public PathfinderCatOnBlock(@NotNull Cat cat, double speedMod) {
		super(cat);
		
		this.speedMod = speedMod;
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
	public CatSitOnBlockGoal getHandle() {
		return new CatSitOnBlockGoal((net.minecraft.world.entity.animal.Cat) nmsEntity, speedMod);
	}

}
