package TestLibraryDatabaseBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import LibraryDatabaseBuilder.DirectoryCrawler;
/*
 * Test class for DirectoryCrawler class
 * @author RonZapp
 * 
 */
class TestDirectoryCrawler {
	public static final String JSON = "json";
	public static final String JSONPATH = "input/lastfm_tinyset/J/TRBIJES12903CF5B12.json";
	public static final String TINYSETPATH = "input/lastfm_tinyset/J";
	public static final String SMALLSETPATH = "input/lastfm_tinyset";
	public static final String SUBSETPATH = "input/lastfm_subset";
	public static final String JSONEXAMPLE = "{\"artist\": \"Mouth Of The Architect\", \"timestamp\": \"2011-08-08 18:12:58.469145\", \"similars\": [[\"TRHBJNQ12903CF5B08\", 1], [\"TRBLUXB12903CF5AF7\", 0.95312600000000003], [\"TRNPOMO12903CF5CA7\", 0.17623], [\"TRIBURQ12903CF5CDB\", 0.17388500000000001], [\"TRNHSAG12903CF5E10\", 0.13997999999999999], [\"TRAOIWA128F9314657\", 0.12534100000000001], [\"TRDRIXM128F9314691\", 0.124762], [\"TREGSGB12903CDD8F7\", 0.116809], [\"TRFHHBK128F42911D1\", 0.114466], [\"TRGQUDO128F428CEA5\", 0.10136000000000001], [\"TRJAFRH128F428CEA7\", 0.098098000000000005], [\"TREXYUR12903CF4351\", 0.0672677], [\"TRIBCXK128F4291A7C\", 0.065832299999999996], [\"TROVKHA12903CF4355\", 0.063808699999999996], [\"TRAKMKJ12903CF5DB9\", 0.060594299999999997], [\"TRWPCEJ12903D00CA5\", 0.0534373], [\"TRIOMHG128F92F9444\", 0.0531779], [\"TRVGRET128EF367422\", 0.047188099999999997], [\"TRNKBMZ128EF367424\", 0.045033999999999998], [\"TRUDWQM12903D00CB1\", 0.034553], [\"TRWVKLU128F92F179B\", 0.034254300000000001], [\"TRVLFVM12903CEC8EC\", 0.032000500000000001], [\"TRJTUBB128F92F17A4\", 0.027477000000000001], [\"TRUTOAD12903D03131\", 0.0261687], [\"TRSEUKB12903CFEAC9\", 0.024777899999999999], [\"TRDOYBG12903CEB5D1\", 0.024777899999999999], [\"TRWEPCI128F42207FC\", 0.021034899999999999], [\"TRQDCRF12903D0311C\", 0.020733999999999999], [\"TRGJFOH128F42207F9\", 0.020519800000000001], [\"TRKABKR12903D0315A\", 0.017962100000000002], [\"TRFSHNB12903CBD9BB\", 0.0148091], [\"TRTEVED128F425891C\", 0.00137582], [\"TRQAUFE128F932C2BC\", 0.00124514], [\"TREJHZY128F932C2BE\", 0.0012451300000000001], [\"TRJBVJY128F930C092\", 0.0011786100000000001], [\"TRCDQPR128F92F095C\", 0.00111148], [\"TRFZQCV12903CFAE61\", 0.0010219000000000001]], \"tags\": [[\"Post-Metal\", \"100\"], [\"Sludge\", \"77\"], [\"post rock\", \"44\"], [\"experimental\", \"33\"], [\"doom metal\", \"33\"], [\"sludge metal\", \"33\"], [\"post metal\", \"33\"], [\"Atmospheric Sludge\", \"22\"], [\"beautiful\", \"22\"], [\"What was once held so close has decayed\", \"22\"], [\"wow\", \"11\"], [\"post-prog\", \"11\"], [\"post prog\", \"11\"], [\"atmospheric sludge metal\", \"11\"], [\"mouth of the architect\", \"11\"], [\"mmt metal\", \"11\"], [\"post rocks\", \"11\"], [\"sorrowful\", \"11\"], [\"sorrow\", \"11\"], [\"download\", \"11\"], [\"moving\", \"11\"], [\"00s\", \"11\"], [\"emotional\", \"11\"], [\"atmospheric\", \"11\"], [\"post-rock\", \"11\"], [\"Progressive metal\", \"11\"], [\"metal\", \"11\"], [\"me mola\", \"0\"]], \"track_id\": \"TRBIJES12903CF5B12\", \"title\": \"Harboring An Apparition\"}";
	
	
	@Test
	/* Tests if crawler can parse single JSON file */
	void testSingleJson() {
		DirectoryCrawler dc = new DirectoryCrawler(JSONPATH, JSON);
		HashSet<String> hs = dc.crawl();
		assertTrue(hs.size() == 1 && hs.contains(JSONEXAMPLE));
	}
	
	@Test
	/* Tests if crawler can traverse a bottom level directory */
	void testTinyset() {
		DirectoryCrawler dc = new DirectoryCrawler(TINYSETPATH, JSON);
		HashSet<String> hs = dc.crawl();
		assertTrue(hs.size() == 12);
	}
	
	@Test
	/* Tests if crawler can traverse directory that contains more directories */
	void testNestedDirectories() {
		DirectoryCrawler dc = new DirectoryCrawler(SMALLSETPATH, JSON);
		HashSet<String> hs = dc.crawl();
		assertTrue(hs.size() == 21);
	}
	
	@Test
	/* Tests if crawler can get through lastfm_subset (9000 songs!) without throwing any exceptions */
	void testExceptionsSubset() {
		DirectoryCrawler dc = new DirectoryCrawler(SUBSETPATH, JSON);
		try {
			HashSet<String> hs = dc.crawl();
			System.out.println(hs.size());
		} catch (Exception e) {
			fail(String.format("testExceptionsSubset: %s", e.toString()));
		}
	}
}
