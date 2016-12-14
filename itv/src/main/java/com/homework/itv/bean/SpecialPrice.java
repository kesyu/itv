package com.homework.itv.bean;

import java.io.Serializable;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Represent a {@link StockKeepingUnit}'s special price.
 *
 */
public class SpecialPrice implements Serializable{
	private static final long serialVersionUID = -8947241202695242688L;
	private Integer count;
	private Integer price;
	
	public SpecialPrice(){}
	
	public SpecialPrice(Integer count, Integer price) {
		super();
		this.count = count;
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + price;
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
		SpecialPrice other = (SpecialPrice) obj;
		if (count != other.count)
			return false;
		if (price != other.price)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SpecialPrice [count=").append(count).append(", price=").append(price).append("]");
		return builder.toString();
	}
}
