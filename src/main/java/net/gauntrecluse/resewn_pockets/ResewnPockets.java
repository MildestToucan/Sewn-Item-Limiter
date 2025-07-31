package net.gauntrecluse.resewn_pockets;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResewnPockets implements ModInitializer {
	public static final String MOD_ID = "resewn_pockets";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello from Sewn Item Limiter!");
	}
}