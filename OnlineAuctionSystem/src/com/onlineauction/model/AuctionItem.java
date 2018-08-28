package com.onlineauction.model;

public final class  AuctionItem {

	private final int itemID;
	private final String itemName;

	public AuctionItem(int itemID, String itemName) {
		this.itemID = itemID;
		this.itemName = itemName;
	}

	public int getItemID() {
		return itemID;
	}

	public String getItemName() {
		return itemName;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof AuctionItem)) {
			return false;
		}
		AuctionItem auctionItem = (AuctionItem) o;
		return auctionItem.itemID == itemID;
	}

	@Override
	public int hashCode() {
		return itemID;
	}

}
