package com.onlineauction.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;

public class AuctionRepository {

	private final Map<AuctionItem, List<Bidder>> auctionRepo;

	public AuctionRepository() {
		auctionRepo = new ConcurrentHashMap<>();
	}

	public Map<AuctionItem, List<Bidder>> getAuctionRepo() {
		return auctionRepo;
	}

	public synchronized void saveBid(AuctionItem auctionItem, Bidder bidder) {
		List<Bidder> bidderList = auctionRepo.get(auctionItem);
		if (null == bidderList)
			bidderList = new ArrayList<>();

		bidderList.add(bidder);
		auctionRepo.put(auctionItem, bidderList);
	}

}
