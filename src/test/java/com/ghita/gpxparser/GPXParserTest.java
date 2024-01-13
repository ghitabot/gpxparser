package com.ghita.gpxparser;

import com.ghitabot.gpxparser.GPXParser;
import com.ghitabot.gpxparser.beans.GPX;
import com.ghitabot.gpxparser.beans.Waypoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class GPXParserTest {
    GPXParser parser = new GPXParser();

    @Test
    public void test() {
        try {
            GPX gpx = parser.parseGPX(this.getClass().getResourceAsStream("fells_loop.gpx"));
            assertEquals(gpx.getCreator(), "ExpertGPS 1.1 - https://www.topografix.com");
            assertEquals(gpx.getVersion(), "1.0");
            assertEquals(86,gpx.getWaypoints().size());
            /**
             * <wpt lat="42.455956" lon="-71.107483">
             *  <ele>64.008000</ele>
             *  <time>2001-06-02T03:27:04Z</time>
             *  <name>SOAPBOX</name>
             *  <desc><![CDATA[Soap Box Derby Track]]></desc>
             *  <sym>Cemetery</sym>
             *  <type><![CDATA[Intersection]]></type>
             * </wpt>
             */
            assertEquals("SOAPBOX", gpx.getWaypoints().iterator().next().getName());
            assertEquals(64.008000, gpx.getWaypoints().iterator().next().getElevation());
            assertEquals(gpx.getRoutes().size(), 1);
        }catch (Throwable t) {
            assertNull(t, "There should be no exception thrown");
        }
    }
}
