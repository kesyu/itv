package com.homework.itv.bean;

import java.io.Serializable;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Represents a Stock Keeping Unit.
 *
 */
public class StockKeepingUnit implements Serializable {
	private static final long serialVersionUID = 4858252145839856703L;
	private String item;
	private int price;
	private SpecialPrice specialPrice;
	
	public StockKeepingUnit(){}
	
	public StockKeepingUnit(String item, int price, SpecialPrice specialPrice) {
		super();
		this.item = item;
		this.price = price;
		this.specialPrice = specialPrice;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public SpecialPrice getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(SpecialPrice specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockKeepingUnit other = (StockKeepingUnit) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockKeepingUnit [itemId=").append(item).append(", price=").append(price)
				.append(", specialPrice=").append(specialPrice).append("]");
		return builder.toString();
	}
}
