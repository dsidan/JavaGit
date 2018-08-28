package com.onlineauction.service;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.WinnerBidData;

public interface BidWinnerCalculator {

	public WinnerBidData computeWinningBid(AuctionItem auctionItem, String logic);

}