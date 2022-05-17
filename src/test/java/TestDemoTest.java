import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.spy;

class TestDemoTest {

  private TestDemo testDemo;

  @BeforeEach
  void setUp() {
    testDemo = new TestDemo();
  }


  @ParameterizedTest
  @MethodSource("TestDemo#argumentsForAddPositive")
  void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected,
      boolean expectException) {

    if (!expectException) {
      assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
    } else {
      assertThatThrownBy(() -> testDemo.addPositive(a, b))
          .isInstanceOf(IllegalArgumentException.class);
    }

  }



  public static Stream<Arguments> argumentsForAddPositive() {
    return (Stream.of(arguments(1, 2, 3, true), arguments(-5, 2, 7, true),
        arguments(4, 6, 7, false), arguments(0, 9, 3, true), arguments(10, 5, 3, false)));
  }

  @Test
  void assertThatNumberSquaredIsCorrect() {
    TestDemo mockDemo = spy(testDemo);
    doReturn(7).when(mockDemo).getRandomInt();
    int sevenSquared = mockDemo.randomNumberSquared();
    assertThat(sevenSquared).isEqualTo(49);
  }
}


