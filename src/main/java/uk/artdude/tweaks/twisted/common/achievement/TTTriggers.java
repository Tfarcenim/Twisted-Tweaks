package uk.artdude.tweaks.twisted.common.achievement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import uk.artdude.tweaks.twisted.TwistedTweaks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Sam on 3/03/2018.
 */
public class TTTriggers
{
	public static CustomTrigger TRIGGER_BURN_ACID = new CustomTrigger("burn_acid");
	public static CustomTrigger TRIGGER_BLIND_ACID = new CustomTrigger("blind_acid");

	public static void init() {
		CriteriaTriggers.register(TRIGGER_BURN_ACID);
		CriteriaTriggers.register(TRIGGER_BLIND_ACID);
	}
}
