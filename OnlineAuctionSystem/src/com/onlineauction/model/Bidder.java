package com.onlineauction.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public final class Bidder {

	private final int bidderID;
	private final BigDecimal startBid;
	private final BigDecimal maxBid;
	private final BigDecimal autoIncAmount;
	private final Timestamp timestamp;

	public Bidder(int bidderID, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncAmount) {
		this.bidderID = bidderID;
		this.startBid = startBid;
		this.maxBid = maxBid;
		this.autoIncAmount = autoIncAmount;
		timestamp = new Timestamp(System.nanoTime());
	}

	public int getBidderID() {
		return bidderID;
	}

	public BigDecimal getStartBid() {
		return startBid;
	}

	public BigDecimal getMaxBid() {
		return maxBid;
	}

	public BigDecimal getAutoIncAmount() {
		return autoIncAmount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "BidderID : " + bidderID + ", Start Bid : " + startBid + ", Max Bid : " + maxBid
				+ ", Auto Increment Amount : " + autoIncAmount;

	}

}
