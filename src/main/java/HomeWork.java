import figure.Shape;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class HomeWork {

	private static final Pattern startWithHashTagAndUnique = Pattern.compile("([.#])\\w+");

	public static int[] filterByPositiveAndSort(int[] integers) {
		return Arrays.stream(integers)
				.filter(integer -> integer >= 0)
				.boxed()
				.sorted(reverseOrder())
				.mapToInt(Integer::valueOf)
				.toArray();
	}

	public static Map<String, Long> findHashTagMessagesAndFindTopFive(List<String> strings) {
		return strings.stream()
				.flatMap(HomeWork::parseStringAndRemoveDuplicateHashTag)
				.collect(groupingBy(identity(), counting()))
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(reverseOrder()))
				.limit(5)
				.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

	}

	public static List<Shape> sortByVolumeNaturalOrder(List<Shape> shapes) {
		return shapes.stream()
				.sorted(comparing(Shape::calculateVolume))
				.collect(toList());

	}

	private static Stream<String> parseStringAndRemoveDuplicateHashTag(String str) {
		Set<String> strings = new HashSet<>();
		Matcher matcher = startWithHashTagAndUnique.matcher(str);
		while (matcher.find()) {
			strings.add(matcher.group());
		}
		return strings.stream();
	}
}
