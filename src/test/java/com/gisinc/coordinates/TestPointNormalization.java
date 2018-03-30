package com.gisinc.coordinates;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestPointNormalization {

	@Test
	void testGetNormalizedPointA() {
		Point point = new Point(190, 240);
		assertEquals(new Point(-170, -60), point.getNormalizedPoint());
	}
	
	@Test
	void testGetNormalizedPointB() {
		Point point = new Point(-210, 120);
		assertEquals(new Point(150, 60), point.getNormalizedPoint());
	}
	
	@Test
	void testGetNormalizedPointC() {
		Point point = new Point(720, -10);
		assertEquals(new Point(0, -10), point.getNormalizedPoint());
	}
	
	@Test
	void testGetNormalizedPointD() {
		Point point = new Point(-750, 0);
		assertEquals(new Point(-30, 0), point.getNormalizedPoint());
	}
	
	@Test
	void testGetNormalizedPointE() {
		Point point = new Point(-890, -100);
		assertEquals(new Point(-170, -80), point.getNormalizedPoint());
	}
	
	@Test
	void testGetNormalizedPointF() {
		Point point = new Point(920, 180);
		assertEquals(new Point(-160, 0), point.getNormalizedPoint());
	}

}
