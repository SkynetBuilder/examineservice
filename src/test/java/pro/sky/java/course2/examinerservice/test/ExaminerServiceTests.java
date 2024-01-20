package pro.sky.java.course2.examinerservice.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.IllegalAmountException;
import pro.sky.java.course2.examinerservice.service.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTests {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;
    private Set<Question> expected;
    private final Question EXPECTED_QUESTION = new Question("Random", "генератор превдослучайных чисел");

    @BeforeEach
    void setUp() {
        expected = new HashSet<>(Set.of(EXPECTED_QUESTION));
    }

    @Test
    public void testGetQuestionIfCorrectAmount() {
        when(javaQuestionService.getAll()).thenReturn(expected);
        when(javaQuestionService.getRandomQuestion()).thenReturn(EXPECTED_QUESTION);
        assertIterableEquals(Set.of(EXPECTED_QUESTION), examinerService.getQuestion(1));
    }

    @Test
    public void testGetQuestionIfIncorrectAmount() {
        when(javaQuestionService.getAll()).thenThrow(IllegalAmountException.class);
        assertThrows(IllegalAmountException.class, () -> {
            examinerService.getQuestion(1);
        });
    }
}
