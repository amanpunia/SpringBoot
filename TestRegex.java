import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		System.out.println(new TestRegex().validate("()# .s'_f.jpg"));
	}

	private Pattern pattern;
	private Matcher matcher;

	private static final String IMAGE_FILENAME_PATTERN = "[a-zA-Z0-9_. `~!@#$%^&()+=,.;'\\-\\[\\]\\{\\}]+(\\.(?i)(jpg|png|jpeg|gif|bmp)$)";

	public TestRegex() {
		pattern = Pattern.compile(IMAGE_FILENAME_PATTERN);
	}

	public boolean validate(final String image) {

		matcher = pattern.matcher(image);
		return matcher.matches();

	}
}
