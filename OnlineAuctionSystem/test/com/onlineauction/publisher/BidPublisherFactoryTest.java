package com.onlineauction.publisher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BidPublisherFactoryTest {

	@Test
	void testMaxValAlgo() {
		BidPublisherFactory bidPublisherFactory = new BidPublisherFactory();
		assertEquals(BidPublisherMaxValAlgo.class, bidPublisherFactory.createBidPublisher("MaxVal").getClass());
	}

	void testDefault() {
		BidPublisherFactory bidPublisherFactory = new BidPublisherFactory();
		assertEquals(BidPublisherMaxValAlgo.class, bidPublisherFactory.createBidPublisher("").getClass());
	}

	void testHighRangeDataAlgo() {
		BidPublisherFactory bidPublisherFactory = new BidPublisherFactory();
		assertEquals(BidPublisherHighRangeDataAlgo.class,
				bidPublisherFactory.createBidPublisher("HighRangeData").getClass());
	}

}
