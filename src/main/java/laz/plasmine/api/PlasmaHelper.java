package laz.plasmine.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.StringTextComponent;

public class PlasmaHelper {

	private int maxCapacity = 0;
	private int capacity = 0;
	private int maxSend = 0;

	public PlasmaHelper(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	public PlasmaHelper(int maxCapacity, int maxSend) {
		this.maxCapacity = maxCapacity;
		this.maxSend = maxSend;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	public void setMaxCapacity(int cap) {
		maxCapacity = cap;
		if (maxCapacity < capacity) capacity = maxCapacity;
	}
	
	public void setMaxSend(int send) {
		maxSend = send;
	}
	
	public int getMaxSend() {
		return maxSend;
	}
	
	public void setCapacity(int amount) {
		capacity = amount;
	}

	public int getCapacityLeft() {
		return maxCapacity - getCapacity();
	}

	public int addPlasma(int amount) {
		if (getCapacityLeft() <= amount) {
			int dif = amount - getCapacityLeft();
			capacity = maxCapacity;
			return dif;
		}
		capacity += amount;
		return 0;
	}

	public int removePlasma(int amount) {
		if (amount > capacity) {
			int dif = capacity;
			capacity = 0;
			return dif;
		}
		
		capacity -= amount;
		return amount;
		
	}
	
	public int sendPlasma() {
		if (maxSend > getCapacity()) return 0;
		return maxSend;
	}
	
	public int sendPlasma(int amount) {
		if (getCapacity() == 0) return 0;
		if (amount > getCapacity()) return getCapacity();
		return amount;
	}

	public void write(CompoundNBT compound) {
		compound.putInt("capacity", capacity);
		compound.putInt("maxCapacity", maxCapacity);
		compound.putInt("maxSend", maxSend);
	}

	public void read(CompoundNBT compoundNBT) {
		capacity = compoundNBT.getInt("capacity");
		maxCapacity = compoundNBT.getInt("maxCapacity");
		maxSend = compoundNBT.getInt("maxSend");
	}

	public void displayInfo(PlayerEntity player) {
		player.sendMessage(new StringTextComponent("\n\u00A75------------- INFO ------------\n"), player.getUniqueID());
		player.sendMessage(new StringTextComponent("- Max Capacity : \u00A74" + maxCapacity), player.getUniqueID());
		player.sendMessage(new StringTextComponent("- Capacity : \u00A76" + capacity + ""), player.getUniqueID());
		if (maxSend > 0) player.sendMessage(new StringTextComponent("- Transfer rate : \u00A7a" + maxSend), player.getUniqueID());
	}

}
