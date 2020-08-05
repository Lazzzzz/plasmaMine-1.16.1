package laz.plasmine.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

public class PlasmaHelper {
	
	private int maxCapacity  = 0;
	private int transferRate = 0;
	private int capacity 	 = 0;
	
	private float efficiency = 0;
	
	public PlasmaHelper(int maxCapacity, int rate, float efficiency) {
		this.maxCapacity  = maxCapacity;
		this.transferRate = rate;
		this.efficiency   = efficiency;
	}
	
	private int removePlasma() {
		int amount = (int) (transferRate * efficiency);
		if (capacity - amount < 0) {
			capacity = 0;
			return this.capacity;
		}
		capacity -= amount;
		return amount;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void transferOut(PlasmaHelper getter) {
		getter.addPlasma(removePlasma());
	}
	
	public boolean asMoreThan(PlasmaHelper getter) {
		return getter.getCapacity() < capacity; 
	}
	
	public void addPlasma(float amount) {
		capacity += amount;
		if (capacity > maxCapacity) capacity = maxCapacity;
	}
	
	public void write(CompoundNBT compound) {
		compound.putInt("maxCapacity" , maxCapacity);
		compound.putInt("transferRate", transferRate);
		compound.putInt("capacity"    , capacity);
		compound.putFloat("efficiency", efficiency);
	}
	
	public void read(CompoundNBT compoundNBT) {
		maxCapacity  = compoundNBT.getInt("maxCapacity");
		transferRate = compoundNBT.getInt("transferRate");
		capacity 	 = compoundNBT.getInt("capacity");
		efficiency 	 = compoundNBT.getFloat("efficiency");
	}
	
	public void displayInfo(PlayerEntity player) {
		player.sendMessage(new StringTextComponent("- Max Capacity : \u00A74" + maxCapacity), player.getUniqueID());
		player.sendMessage(new StringTextComponent("- TransferRate : \u00A7c" + transferRate), player.getUniqueID());
		player.sendMessage(new StringTextComponent("- Capacity : \u00A76" 	   + capacity), player.getUniqueID());
		player.sendMessage(new StringTextComponent("- Efficienct : \u00A7e" + efficiency), player.getUniqueID());
	}
	
}
