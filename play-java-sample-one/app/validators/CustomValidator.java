package validators;

import play.data.validation.Constraints.Validator;
import play.libs.F;
import play.libs.F.Tuple;

public class CustomValidator {

	public static class IsUrl extends Validator<String> {
		public boolean isValid(String s) {
			return s.toLowerCase().startsWith("http://");
		}

		@Override
		public Tuple<String, Object[]> getErrorMessageKey() {
			return new F.Tuple<String, Object[]>("error.invalid",
					new String[] {});
		}
	}

}
