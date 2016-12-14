package com.homework.itv.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Eva Balazsfalvi
 * 
 * Represents a transaction.
 *
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = -6028025004486318765L;
	private final String id;
	private final Map<String, Integer> items;
	private final Set<StockKeepingUnit> stockKeepingUnits;

	public Transaction(String id) {
		super();
		this.id = id;
		this.items = new HashMap<>();
		this.stockKeepingUnits = new HashSet<>();
	}

	public String getId() {
		return id;
	}
	
	public Map<String, Integer> getItems() {
		return Collections.unmodifiableMap(this.items);
	}
	
	public Set<StockKeepingUnit> getStockKeepingUnits() {
		return Collections.unmodifiableSet(this.stockKeepingUnits);
	}

	public void addItem(String item) {
		if (items.containsKey(item)) {
			items.put(item, Integer.valueOf(items.get(item)+1));
		} else {
			items.put(item, Integer.valueOf(1));
		}
	}
	
	public void addStockKeepingUnit(StockKeepingUnit stockKeepingUnit) {
		stockKeepingUnits.add(stockKeepingUnit);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Transaction other = (Transaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [id=").append(id).append(", items=").append(items)
				.append("]");
		return builder.toString();
	}
}
