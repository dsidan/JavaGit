package com.onlineauction.service.impl;

import java.util.List;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;
import com.onlineauction.publisher.BidPublisher;
import com.onlineauction.publisher.BidPublisherFactory;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.service.BidWinnerCalculator;

public class BidWinnerCalculatorImpl implements BidWinnerCalculator {

	private final AuctionRepository auctionRepository;
	private final BidPublisherFactory bidPublisherFactory;

	public BidWinnerCalculatorImpl(AuctionRepository auctionRepository) {
		this.auctionRepository = auctionRepository;
		this.bidPublisherFactory = new BidPublisherFactory();
	}

	@Override
	public WinnerBidData computeWinningBid(AuctionItem auctionItem, String logic) {
		List<Bidder> bidderList = auctionRepository.getAuctionRepo().get(auctionItem);
		BidPublisher bidPublisher = bidPublisherFactory.createBidPublisher(logic);
		return bidPublisher.getWinningBid(bidderList);

	}

}
