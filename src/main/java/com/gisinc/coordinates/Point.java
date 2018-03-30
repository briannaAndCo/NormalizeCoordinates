package com.gisinc.coordinates;

/**
 * This describes a map coordinate location, where a latitude and longitude are
 * held in decimal degrees.
 * 
 * @author landon
 *
 */
public class Point {
	private double lon;
	private double lat;

	public Point(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat))
			return false;
		if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon))
			return false;
		return true;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}

	/**
	 * Returns a point normalized to the (-180, 180) longitude range and (-90,
	 * 90) latitude range.
	 * 
	 * @return the normalized point.
	 */
	public Point getNormalizedPoint() {
		return new Point(getNormalizedLon(), getNormalizedLat());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(lat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(lon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Returns the value of the latitude normalized to a (-90, 90) range.
	 * 
	 * @return - the normalized latitude.
	 */
	private double getNormalizedLat() {
		double normalizedLat;
		// Remove all complete rotations from the latiude.
		double remainderLat = removeCompleteRotations(lat);

		// Check if the point has crossed the equator
		if (remainderLat > 180 || remainderLat < -180) {
			// Get the sign of the normalized value by reversing the current
			// sign.
			int sign = (int) (-1
					* Math.signum(remainderLat));

			// Get how far away the new point is from 0 degrees.
			// Adjust the new point.
			remainderLat = Math.abs((180 - Math.abs(remainderLat))) * sign;
		}

		// If the degrees are still out of the normalized range,
		// then handle wrapping the pole
		if (remainderLat > 90 || remainderLat < -90) {

			// Get the distance from the equator measured wrapping around the
			// pole, but keep the sign.
			normalizedLat = Math.abs((180 - Math.abs(remainderLat)))
					* Math.signum(remainderLat);

		}
		// Else only the extra rotations needed to be removed.
		else {
			normalizedLat = remainderLat;
		}
		return normalizedLat;
	}

	/**
	 * Returns the value of the longitude normalized to a (-180, 180) range.
	 * 
	 * @return - the normalized longitude.
	 */
	private double getNormalizedLon() {
		double normalizedLon;
		// Remove all complete rotations from the longitude.
		double remainderLon = removeCompleteRotations(lon);

		// If the degrees still exceed the bounds, then they are crossing one of
		// the two main meridians.
		if (remainderLon < -180 || remainderLon > 180) {
			// Get the sign of the normalized value by reversing the current
			// sign.
			int sign = (int) (-1
					* Math.signum(remainderLon));

			// Get how far away the new point is from 0 degrees.
			normalizedLon = Math.abs((360 - Math.abs(remainderLon))) * sign;
		}
		// Else only the extra rotations needed to be removed.
		else {
			
			normalizedLon = remainderLon == 0 ? Math.abs(remainderLon)
					: remainderLon;
		}

		return normalizedLon;
	}

	/**
	 * Returns the degrees minus all complete rotations of the earth.
	 * 
	 * @param degree
	 *            - the inital degrees to remove the rotations from.
	 * @return - the degree value with all excess rotations removed.
	 */
	private double removeCompleteRotations(double degree) {
		return degree % 360;
	}
}
