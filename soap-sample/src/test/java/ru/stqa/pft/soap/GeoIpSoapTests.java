package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpSoapTests {

    @Test
    public void testMyIp() {
       String geoIP =  new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("93.100.36.253");
        assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>66</State></GeoIP>");
    }
}
