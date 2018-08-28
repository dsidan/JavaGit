package com.onlineauction.publisher;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;

class BidPublisherHighRangeDataAlgoTest {

	@Test
	public void testWinningBidCaseBicycle() {
		BidPublisherHighRangeDataAlgo bidPublisherHighRangeDataAlgo = new BidPublisherHighRangeDataAlgo();
		List<Bidder> bidderListforBicycle = new ArrayList<>();
		bidderListforBicycle.add(getBidder(1, BigDecimal.valueOf(50), BigDecimal.valueOf(80), BigDecimal.valueOf(3)));
		bidderListforBicycle.add(getBidder(2, BigDecimal.valueOf(60), BigDecimal.valueOf(82), BigDecimal.valueOf(2)));
		bidderListforBicycle.add(getBidder(3, BigDecimal.valueOf(55), BigDecimal.valueOf(85), BigDecimal.valueOf(5)));
		WinnerBidData winningBidder = bidPublisherHighRangeDataAlgo.getWinningBid(bidderListforBicycle);
		assertEquals(3, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(85), winningBidder.getBidAmount());
	}

	@Test
	public void testWinningBidCaseScooter() {
		BidPublisherHighRangeDataAlgo bidPublisherHighRangeDataAlgo = new BidPublisherHighRangeDataAlgo();
		List<Bidder> bidderListforScooter = new ArrayList<>();
		bidderListforScooter.add(getBidder(1, BigDecimal.valueOf(700), BigDecimal.valueOf(725), BigDecimal.valueOf(2)));
		bidderListforScooter
				.add(getBidder(2, BigDecimal.valueOf(599), BigDecimal.valueOf(725), BigDecimal.valueOf(15)));
		bidderListforScooter.add(getBidder(3, BigDecimal.valueOf(625), BigDecimal.valueOf(725), BigDecimal.valueOf(8)));
		WinnerBidData winningBidder = bidPublisherHighRangeDataAlgo.getWinningBid(bidderListforScooter);
		assertEquals(1, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(722), winningBidder.getBidAmount());

	}

	@Test
	public void testWinningBidCaseBoat() {
		BidPublisherHighRangeDataAlgo bidPublisherHighRangeDataAlgo = new BidPublisherHighRangeDataAlgo();
		List<Bidder> bidderListforBoat = new ArrayList<>();
		bidderListforBoat
				.add(getBidder(1, BigDecimal.valueOf(2500), BigDecimal.valueOf(3000), BigDecimal.valueOf(500)));
		bidderListforBoat
				.add(getBidder(2, BigDecimal.valueOf(2800), BigDecimal.valueOf(3202), BigDecimal.valueOf(201)));
		bidderListforBoat
				.add(getBidder(3, BigDecimal.valueOf(2501), BigDecimal.valueOf(3200), BigDecimal.valueOf(247)));
		WinnerBidData winningBidder = bidPublisherHighRangeDataAlgo.getWinningBid(bidderListforBoat);
		assertEquals(2, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(3001), winningBidder.getBidAmount());
	}

	@Test
	public void testWinningBidCaseMobile() {
		BidPublisherHighRangeDataAlgo bidPublisherHighRangeDataAlgo = new BidPublisherHighRangeDataAlgo();
		List<Bidder> bidderListforMobile = new ArrayList<>();
		bidderListforMobile.add(getBidder(1, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
		bidderListforMobile.add(getBidder(2, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
		bidderListforMobile.add(getBidder(3, BigDecimal.valueOf(727), BigDecimal.valueOf(727), BigDecimal.valueOf(1)));
		WinnerBidData winningBidder = bidPublisherHighRangeDataAlgo.getWinningBid(bidderListforMobile);
		assertEquals(1, winningBidder.getBidderID());
		assertEquals(BigDecimal.valueOf(727), winningBidder.getBidAmount());
	}

	private Bidder getBidder(int bidderID, BigDecimal startBid, BigDecimal maxBid, BigDecimal autoIncAmount) {
		return new Bidder(bidderID, startBid, maxBid, autoIncAmount);
	}

}
