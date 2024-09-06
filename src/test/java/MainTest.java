import io.futurecompany.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class MainTest {

    public static Stream<Arguments> is18YearsOldTest() {
        return Stream.of(
                Arguments.of("A person has one day left until he turns 18",
                        LocalDate.now().minusYears(18).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        false),
                Arguments.of("Today a person turns 18",
                        LocalDate.now().minusYears(18).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        true),
                Arguments.of("A person is already 18 years and one day old",
                        LocalDate.now().minusYears(18).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        true)
        );
    }

    @ParameterizedTest(name ="{0}, expect {2}")
    @MethodSource
    @DisplayName("Success")
    void is18YearsOldTest(String name, String dateOfBirth, boolean expectingResult){
        assertEquals(Main.is18YearsOld(dateOfBirth), expectingResult, String.format("For date '%s' we expected is18YearsOld() return %b", dateOfBirth, expectingResult));
    }


    public static Stream<Arguments> is18YearsOldExceptionTest() {
        return Stream.of(
                Arguments.of("Invalid format", "1984"),
                Arguments.of("Invalid date value", "1985-02-29"),
                Arguments.of("Invalid month value", "1984-00-23"),
                Arguments.of("Invalid day value", "1984-03-32")
        );
    }

    @ParameterizedTest(name ="{0}: {1}")
    @MethodSource
    @DisplayName("Errors")
    void is18YearsOldExceptionTest(String name, String dateOfBirth){
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            Main.is18YearsOld(dateOfBirth);
            });
    }
}
