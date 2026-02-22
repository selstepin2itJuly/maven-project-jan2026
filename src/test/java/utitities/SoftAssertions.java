package utitities;

import org.testng.asserts.SoftAssert;

public class SoftAssertions {

	SoftAssert softAssert = new SoftAssert();

	public void assertArrays(String[] act, String[] exp) {
		softAssert.assertEquals(act.length, exp.length);
		if (act.length == exp.length) {
			for (int i = 0; i < exp.length - 1; i++) {
				softAssert.assertEquals(act[i], exp[i]);
			}
		}
		softAssert.assertAll();
	}

}
