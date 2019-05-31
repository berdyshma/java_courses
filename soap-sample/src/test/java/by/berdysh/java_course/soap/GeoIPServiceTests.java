package by.berdysh.java_course.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIPServiceTests {

	@Test

	public void testMyIp() {
		String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.127.104.151");

	}
}


