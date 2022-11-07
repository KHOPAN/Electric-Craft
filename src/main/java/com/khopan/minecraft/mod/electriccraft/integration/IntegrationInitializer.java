package com.khopan.minecraft.mod.electriccraft.integration;

import java.lang.reflect.Method;

import com.khopan.minecraft.mod.electriccraft.ElectricCraft;

import net.fabricmc.loader.FabricLoader;

@SuppressWarnings("deprecation")
public class IntegrationInitializer {
	public static final FabricLoader FABRIC_LOADER = FabricLoader.INSTANCE;
	public static final String MOD_NAME = ElectricCraft.MOD_NAME;

	public static void preInitialize() {

	}

	public static void initialize() {
		IntegrationInitializer.detectOptional("create", "Create", "CreateIntegration");
	}

	public static void preInitializeClient() {

	}

	public static void initializeClient() {
		IntegrationInitializer.detectOptionalClient("create", "Create", "CreateIntegration");
	}

	public static void detectRequired(String ModIdentifier, String ModName, String ClassName) {
		IntegrationInitializer.required(ModIdentifier, ModName, ClassName, false);
	}

	public static void detectRequiredClient(String ModIdentifier, String ModName, String ClassName) {
		IntegrationInitializer.required(ModIdentifier, ModName, ClassName, true);
	}

	public static void required(String ModIdentifier, String ModName, String ClassName, boolean IsClient) {
		if(IntegrationInitializer.FABRIC_LOADER.getModContainer(ModIdentifier).isPresent()) {
			IntegrationInitializer.optional(ModIdentifier, ModName, ClassName, IsClient);
		} else {
			throw new RuntimeException(String.format("Could not found %s mod, make sure you installed the required dependencies for %s.", ModName, IntegrationInitializer.MOD_NAME));
		}
	}

	public static void detectOptional(String ModIdentifier, String ModName, String ClassName) {
		IntegrationInitializer.optional(ModIdentifier, ModName, ClassName, false);
	}

	public static void detectOptionalClient(String ModIdentifier, String ModName, String ClassName) {
		IntegrationInitializer.optional(ModIdentifier, ModName, ClassName, true);
	}

	private static void optional(String ModIdentifier, String ModName, String ClassName, boolean IsClient) {
		if(IntegrationInitializer.FABRIC_LOADER.getModContainer(ModIdentifier).isPresent()) {
			ElectricCraft.LOGGER.info(String.format("Detected %s mod, Initializing " + (IsClient ? "client" : "") + " integration with %s", ModName, ModName));

			try {
				Class<?> IntegrationClass = Class.forName("com.khopan.minecraft.mod.electricCraft.integration." + ModIdentifier + "." + ClassName);
				Method Method = IntegrationClass.getDeclaredMethod("initialize" + (IsClient ? "Client" : ""));
				Method.invoke(null);
			} catch(Throwable Errors) {
				Errors.printStackTrace();
			}
		}
	}
}
