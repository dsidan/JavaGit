package com.onlineauction.model;

import java.math.BigDecimal;

public final class WinnerBidData {

	private final int bidderID;
	private final BigDecimal bidAmount;
	
	public WinnerBidData(int bidderID, BigDecimal bidAmount)
	{
		this.bidderID = bidderID;
		this.bidAmount = bidAmount;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public int getBidderID() {
		return bidderID;
	}



}
