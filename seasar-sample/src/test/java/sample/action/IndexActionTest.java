package sample.action;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;

@RunWith(Seasar2.class)
public class IndexActionTest {

	private IndexAction action;

	@Test
	public void testSubmit() {
		action.text = "test";
		action.submit();
		assertThat(action.text, is("test-submit"));
	}
}
