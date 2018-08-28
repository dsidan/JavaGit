package com.onlineauction.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;

class AuctionRepositoryTest {

	@Test
	void testSave() {
		AuctionRepository auctionRepository = new AuctionRepository();
		AuctionItem bicycle = getAuctionItem(1, "Bicycle");
		Bidder bidderAlice = getBidder(1, BigDecimal.valueOf(50), BigDecimal.valueOf(80), BigDecimal.valueOf(3));
		auctionRepository.saveBid(bicycle, bidderAlice);
		Bidder bidderAaron = getBidder(2, BigDecimal.valueOf(60), BigDecimal.valueOf(82), BigDecimal.valueOf(2));
		auctionRepository.saveBid(bicycle, bidderAaron);
		Bidder bidderAmanda = getBidder(3, BigDecimal.valueOf(55), BigDecimal.valueOf(85), BigDecimal.valueOf(5));
		auctionRepository.saveBid(bicycle, bidderAmanda);

		assertEquals(1, auctionRepository.getAuctionRepo().size());
		assertEquals(3, auctionRepository.getAuctionRepo().get(bicycle).size());
		assertEquals("BidderID : 1, Start Bid : 50, Max Bid : 80, Auto Increment Amount : 3",
				auctionRepository.getAuctionRepo().get(bicycle).get(0).toString());

	}

	private AuctionItem getAuctionItem(int itemID, String itemName) {
		return new AuctionItem(itemID, itemName);
	}

	private Bidder getBidder(int bidderID, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncAmount) {
		return new Bidder(bidderID, startBid, maxBid, autoIncAmount);
	}

}
