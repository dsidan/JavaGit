package com.onlineauction.service;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;

public interface BidCollector {

	public void collectBid(AuctionItem auctionItem, Bidder bidder);
}
