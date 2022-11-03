import figure.Shape;
import figure.shapeImpl.Cube;
import figure.shapeImpl.Cylinder;
import figure.shapeImpl.Sphere;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HomeWorkTests {
	@Test
	public void filterByPositiveAndSortTest() {
		int[] integers = new int[]{5, 1142, 32, 1, 5, -3};
		integers = HomeWork.filterByPositiveAndSort(integers);
		assertThat(integers).containsExactly(1142, 32, 5, 5, 1);
		integers = new int[]{-23, 0, 0, 13231, -100, 50, 70, 33};
		integers = HomeWork.filterByPositiveAndSort(integers);
		assertThat(integers).doesNotContain(-100, -23);
		assertThat(integers).containsExactly(13231, 70, 50, 33, 0, 0);
	}

	@Test
	public void findHashTagMessagesAndFindTopFiveTestWithNoSpaces() {
		List<String> stringList = new ArrayList<>(List.of("#Java#Kotlin#Java#Kotlin#Link", "#Java"));
		Map<String, Long> resultMap;
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		LinkedHashMap<String, Long> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("#Java", 2L);
		linkedHashMap.put("#Link", 1L);
		linkedHashMap.put("#Kotlin", 1L);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);

		stringList = new ArrayList<>(List.of("#Java#Groovy#Kotlin", "2112#Java", "sasas", "SAJFSLasj#Java#jdk#Groovy", "#FinTech", "#dkgf"));
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		linkedHashMap.clear();
		linkedHashMap.put("#Java", 3L);
		linkedHashMap.put("#Groovy", 2L);
		linkedHashMap.put("#FinTech", 1L);
		linkedHashMap.put("#dkgf", 1L);
		linkedHashMap.put("#Kotlin", 1L);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);

		stringList = new ArrayList<>(List.of("#Java#Kotlin", "2112#Java#StandWithUkraine", "sasas#StandWithUkraine", "#StandWithUkraine"));
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		linkedHashMap.clear();
		linkedHashMap.put("#StandWithUkraine", 3L);
		linkedHashMap.put("#Java", 2L);
		linkedHashMap.put("#Kotlin", 1L);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);

		stringList = new ArrayList<>(List.of("#Java#Kotlin#Groovy", "2112fdf#Java#Spring#Spring#Java", "sasas#Spring#Hibernate#Maven",
				"#Hibernate#Hibernate", "#Kotlin#Java", "#Gradlle#Maven#Spring#Gradle", "SAJFLSJFJIvnnxudsewqwoo#JavaSpring#Spring#Java", "#JDK#Maven"));
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		linkedHashMap.clear();
		linkedHashMap.put("#Java", 4L);
		linkedHashMap.put("#Spring", 4L);
		linkedHashMap.put("#Maven", 3L);
		linkedHashMap.put("#Hibernate", 2L);
		linkedHashMap.put("#Kotlin", 2L);
		assertThat(resultMap).hasSize(5);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);
		assertThat(resultMap).doesNotContainEntry("Groovy", 1L);

	}

	@Test
	public void findHashTagMessagesAndFindTopFiveTestWithSpaces() {
		List<String> stringList = new ArrayList<>(List.of("#Java #Kotlin Hello #Java There #Kotlin #Link", "#Java"));
		Map<String, Long> resultMap;
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		LinkedHashMap<String, Long> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("#Java", 2L);
		linkedHashMap.put("#Link", 1L);
		linkedHashMap.put("#Kotlin", 1L);
		assertThat(resultMap).hasSize(3);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);
		stringList = new ArrayList<>(List.of("#Java #kotlin #Groovy", "2112fdf #Java #Spring #Spring Hello #Java Max", "sasas #Spring #Hibernate #Maven",
				"#Hibernate #Hibernate", "#kotlin resultMap #Java result", "#Gradlle #Maven #Spring #Gradle",
				"SAJFLSJFJIvnnx udsewqwoo #Java Spring #Spring #Java", "SSsffa #JDK 123#Maven dSSbvxwx"));
		resultMap = HomeWork.findHashTagMessagesAndFindTopFive(stringList);
		linkedHashMap.clear();
		linkedHashMap.put("#Java", 4L);
		linkedHashMap.put("#Spring", 4L);
		linkedHashMap.put("#Maven", 3L);
		linkedHashMap.put("#Hibernate", 2L);
		linkedHashMap.put("#kotlin", 2L);
		assertThat(resultMap).hasSize(5);
		assertThat(resultMap).containsExactlyEntriesOf(linkedHashMap);
		assertThat(resultMap).doesNotContainEntry("Groovy", 1L);
	}

	@Test
	public void sortByVolumeNaturalOrderTest() {
		List<Shape> shapes = new ArrayList<>();
		Cube cube = new Cube(13); // volume 2197
		Cylinder cylinder = new Cylinder(5, 10); // 70.24
		Sphere sphere = new Sphere(412412412); // 2.9382164875908896E26
		shapes.add(sphere); // 2197
		shapes.add(cylinder);
		shapes.add(cube);
		shapes = HomeWork.sortByVolumeNaturalOrder(shapes);
		assertThat(shapes).containsExactly(cylinder, cube, sphere);
		Cube secondCube = new Cube(245); // 1.4706125E7
		Sphere secondSphere = new Sphere(12); // 7238.229473870882
		shapes.add(secondCube);
		shapes.add(secondSphere);
		shapes = HomeWork.sortByVolumeNaturalOrder(shapes);
		assertThat(shapes).containsExactly(cylinder, cube, secondSphere, secondCube, sphere);
	}
}
