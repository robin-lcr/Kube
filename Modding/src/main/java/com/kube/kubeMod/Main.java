package com.kube.kubeMod;

import com.kube.kubeMod.proxy.CommonProxy;
import com.kube.kubeMod.util.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
{
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
    @EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
    	System.out.println("PretInit");
    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
    	System.out.println("Init");
    }

    @EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
    	System.out.println("PostInit");
    }
}
