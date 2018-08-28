package com.onlineauction.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.service.impl.BidCollectorImpl;

public class BidCollectorTest {

	private BidCollector bidCollector;
	private AuctionRepository auctionRepository;

	@Test
	public void testCollectBid() {
		auctionRepository = new AuctionRepository();
		bidCollector = new BidCollectorImpl(auctionRepository);
		
		AuctionItem bicycle = getAuctionItem(1, "Bicycle");
		Bidder bidderAlice = getBidder(1, BigDecimal.valueOf(50), BigDecimal.valueOf(80), BigDecimal.valueOf(3));
		bidCollector.collectBid(bicycle, bidderAlice);
		Bidder bidderAaron = getBidder(2, BigDecimal.valueOf(60), BigDecimal.valueOf(82), BigDecimal.valueOf(2));
		bidCollector.collectBid(bicycle, bidderAaron);
		Bidder bidderAmanda = getBidder(3, BigDecimal.valueOf(55), BigDecimal.valueOf(85), BigDecimal.valueOf(5));
		bidCollector.collectBid(bicycle, bidderAmanda);

		AuctionItem scooter = getAuctionItem(2, "Scooter");
		bidderAlice = getBidder(1, BigDecimal.valueOf(700), BigDecimal.valueOf(725), BigDecimal.valueOf(2));
		bidCollector.collectBid(scooter, bidderAlice);
		bidderAaron = getBidder(2, BigDecimal.valueOf(599), BigDecimal.valueOf(725), BigDecimal.valueOf(15));
		bidCollector.collectBid(scooter, bidderAaron);
		bidderAmanda = getBidder(3, BigDecimal.valueOf(625), BigDecimal.valueOf(725), BigDecimal.valueOf(8));
		bidCollector.collectBid(scooter, bidderAmanda);

		AuctionItem boat = getAuctionItem(3, "Boat");
		bidderAlice = getBidder(1, BigDecimal.valueOf(2500), BigDecimal.valueOf(3000), BigDecimal.valueOf(500));
		bidCollector.collectBid(boat, bidderAlice);
		bidderAaron = getBidder(2, BigDecimal.valueOf(2800), BigDecimal.valueOf(3100), BigDecimal.valueOf(201));
		bidCollector.collectBid(boat, bidderAaron);
		bidderAmanda = getBidder(3, BigDecimal.valueOf(2501), BigDecimal.valueOf(3200), BigDecimal.valueOf(247));
		bidCollector.collectBid(boat, bidderAmanda);

		assertEquals(3, auctionRepository.getAuctionRepo().size());
		assertEquals(3, auctionRepository.getAuctionRepo().get(boat).size());
		assertEquals("BidderID : 1, Start Bid : 2500, Max Bid : 3000, Auto Increment Amount : 500",
				auctionRepository.getAuctionRepo().get(boat).get(0).toString());
	}

	private AuctionItem getAuctionItem(int itemID, String itemName) {
		return new AuctionItem(itemID, itemName);
	}

	private Bidder getBidder(int bidderID, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncAmount) {
		return new Bidder(bidderID, startBid, maxBid, autoIncAmount);
	}

}
