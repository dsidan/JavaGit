package com.onlineauction.service.impl;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.service.BidCollector;

public class BidCollectorImpl implements BidCollector {

	private final AuctionRepository auctionRepository;

	public BidCollectorImpl(AuctionRepository auctionRepository) {
		this.auctionRepository = auctionRepository;
	}

	
	@Override
	public void collectBid(AuctionItem auctionItem, Bidder bidder) {
		auctionRepository.saveBid(auctionItem, bidder);
	}
}
