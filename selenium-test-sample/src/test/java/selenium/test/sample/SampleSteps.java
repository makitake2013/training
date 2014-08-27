package selenium.test.sample;

import static org.junit.Assert.assertTrue;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;

public class SampleSteps {
	private ApplicationDriver applicationDriver;

	public SampleSteps(ApplicationDriver applicationDriver) {
		this.applicationDriver = applicationDriver;
	}

	@前提("^ページを表示する$")
	public void ページを表示する() {
		applicationDriver.open("http://localhost:8080/spring-mvc-sample/echo/");
	}

	@もし("\"([^\"]*)\"を入力した$")
	public void 入力する(String value) {
		applicationDriver.inputText("name", value);
	}

	@もし("^サブミットした$")
	public void サブミットする() {
		applicationDriver.submit();
	}

	@ならば("\"([^\"]*)\"と表示されていること$")
	public void と表示されていること(String pattern) {
		assertTrue(applicationDriver.isShown(pattern));
	}
}
