//package stevekung.mods.moreplanets.core.blocks.base;
//
//import java.util.Random;
//
//import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
//import micdoodle8.mods.galacticraft.core.util.WorldUtil;
//import net.minecraft.block.BlockBreakable;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.init.Items;
//import net.minecraft.world.World;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class BlockNetherTeleporter extends BlockBreakable
//{
//    public BlockNetherTeleporter(String name)
//    {
//        super("ice", Material.rock, false);
//        this.setBlockTextureName("ice");
//        this.setStepSound(soundTypeGlass);
//        this.setLightLevel(0.75F);
//        this.setHardness(2.0F);
//        this.setTickRandomly(true);
//        this.setBlockName(name);
//    }
//
//    @Override
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
//    {
//        if (player.ridingEntity == null && player instanceof EntityPlayerMP)
//        {
//            EntityPlayerMP playerMP = (EntityPlayerMP)player;
//
//            if (playerMP.dimension != -1)
//            {
//                playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, -1, new TeleporterMP(playerMP.mcServer.worldServerForDimension(-1)));
//            }
//            else
//            {
//                int dimID = WorldUtil.getProviderForName(ConfigManagerMP.homePlanetName).dimensionId;
//                playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dimID, new TeleporterMP(playerMP.mcServer.worldServerForDimension(dimID)));
//            }
//        }
//        return true;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getRenderBlockPass()
//    {
//        return 1;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
//    {
//        if (rand.nextInt(100) == 0)
//        {
//            world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
//        }
//
//        for (int l = 0; l < 4; ++l)
//        {
//            double d0 = x + rand.nextFloat();
//            double d1 = y + rand.nextFloat();
//            double d2 = z + rand.nextFloat();
//            double d3 = 0.0D;
//            double d4 = 0.0D;
//            double d5 = 0.0D;
//            int i1 = rand.nextInt(2) * 2 - 1;
//            d3 = (rand.nextFloat() - 0.5D) * 0.5D;
//            d4 = (rand.nextFloat() - 0.5D) * 0.5D;
//            d5 = (rand.nextFloat() - 0.5D) * 0.5D;
//            d2 = z + 0.5D + 0.25D * i1;
//            d5 = rand.nextFloat() * 2.0F * i1;
//            world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
//        }
//    }
//}