package com.onlineauction.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.onlineauction.model.AuctionItem;
import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;
import com.onlineauction.publisher.BidPublisherMaxValAlgo;
import com.onlineauction.repository.AuctionRepository;
import com.onlineauction.service.impl.BidWinnerCalculatorImpl;

public class BidWinnerCalculatorTest {

	private AuctionRepository auctionRepository;
	private BidWinnerCalculator bidWinnerCalculator;

	@Before
	public void initilize() {
		Map<AuctionItem, List<Bidder>> auctionRepo = createAuctionMap();
		auctionRepository = Mockito.mock(AuctionRepository.class);
		bidWinnerCalculator = new BidWinnerCalculatorImpl(auctionRepository);
		Mockito.when(auctionRepository.getAuctionRepo()).thenReturn(auctionRepo);
	}

	@Test
	public void testWinningBidCaseBicycle() {
		WinnerBidData winningBidder = bidWinnerCalculator.computeWinningBid(getAuctionItem(1, "Bicycle"), "MaxVal");
		assertEquals(3, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(85), winningBidder.getBidAmount());
	}

	@Test
	public void testWinningBidCaseScooter() {
		WinnerBidData winningBidder= bidWinnerCalculator.computeWinningBid(getAuctionItem(2, "Scooter"), "HighRangeData");
		assertEquals(1, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(722), winningBidder.getBidAmount());
		
	}

	@Test
	public void testWinningBidCaseBoat() {
		WinnerBidData winningBidder = bidWinnerCalculator.computeWinningBid(getAuctionItem(3, "Boat"), "HighRangeData");
		assertEquals(2, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(3001), winningBidder.getBidAmount());
	}
	
	
	@Test
	public void testWinningBidCaseMobile() {
		WinnerBidData winningBidder = bidWinnerCalculator.computeWinningBid(getAuctionItem(5, "Mobile"), "HighRangeData");
		assertEquals(1, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(727), winningBidder.getBidAmount());
	}

	public Map<AuctionItem, List<Bidder>> createAuctionMap() {
		AuctionItem bicycle = getAuctionItem(1, "Bicycle");
		List<Bidder> bidderListforBicycle = new ArrayList<>();
		bidderListforBicycle.add(getBidder(1, BigDecimal.valueOf(50), BigDecimal.valueOf(80), BigDecimal.valueOf(3)));
		bidderListforBicycle.add(getBidder(2, BigDecimal.valueOf(60), BigDecimal.valueOf(82), BigDecimal.valueOf(2)));
		bidderListforBicycle.add(getBidder(3, BigDecimal.valueOf(55), BigDecimal.valueOf(85), BigDecimal.valueOf(5)));

		AuctionItem scooter = getAuctionItem(2, "Scooter");
		List<Bidder> bidderListforScooter = new ArrayList<>();
		bidderListforScooter.add(getBidder(1, BigDecimal.valueOf(700), BigDecimal.valueOf(725), BigDecimal.valueOf(2)));
		bidderListforScooter.add(getBidder(2, BigDecimal.valueOf(599), BigDecimal.valueOf(725), BigDecimal.valueOf(15)));
		bidderListforScooter.add(getBidder(3, BigDecimal.valueOf(625), BigDecimal.valueOf(725), BigDecimal.valueOf(8)));

		AuctionItem boat = getAuctionItem(3, "Boat");
		List<Bidder> bidderListforBoat = new ArrayList<>();
		bidderListforBoat.add(getBidder(1, BigDecimal.valueOf(2500), BigDecimal.valueOf(3000), BigDecimal.valueOf(500)));
		bidderListforBoat.add(getBidder(2, BigDecimal.valueOf(2800), BigDecimal.valueOf(3202), BigDecimal.valueOf(201)));
		bidderListforBoat.add(getBidder(3, BigDecimal.valueOf(2501), BigDecimal.valueOf(3200), BigDecimal.valueOf(247)));

		AuctionItem sameStartMaxBid = getAuctionItem(5, "Mobile");
		List<Bidder> bidderListforMobile = new ArrayList<>();
		bidderListforMobile.add(getBidder(1, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
		bidderListforMobile.add(getBidder(2, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
		bidderListforMobile.add(getBidder(3, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
 
		Map<AuctionItem, List<Bidder>> auctionRepo = new HashMap<>();
		auctionRepo.put(bicycle, bidderListforBicycle);
		auctionRepo.put(scooter, bidderListforScooter);
		auctionRepo.put(boat, bidderListforBoat);
		auctionRepo.put(sameStartMaxBid, bidderListforMobile);
		return auctionRepo;
	}

	private Bidder getBidder(int bidderID, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncAmount) {
		return new Bidder(bidderID, startBid, maxBid, autoIncAmount);
	}

	private AuctionItem getAuctionItem(int itemID, String itemName) {
		return new AuctionItem(itemID, itemName);
	}

}
