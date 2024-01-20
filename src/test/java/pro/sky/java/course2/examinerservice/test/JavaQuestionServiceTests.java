package pro.sky.java.course2.examinerservice.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTests {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();
    private Set<Question> expected;

    @BeforeEach
    void setUp() {
        expected = new HashSet<>(Set.of(
                new Question("int", "тип переменной, имеющей значение целого числа"),
                new Question("Spring", "framework"),
                new Question("Java", "язык программирования"),
                new Question("Array", "стандартный массив, нерасширяемый"),
                new Question("JUnit5", "фреймворк для тестов")
        ));
    }

    @Test
    public void testAddIfQuestionAndAnswerProvided() {
        for (Question question : expected) {
            javaQuestionService.add(question.getQuestion(), question.getAnswer());
        }
        assertIterableEquals(expected, javaQuestionService.getAll());
    }
    @Test
    public void testAddIfQuestionObjectProvided() {
        for (Question question : expected) {
            javaQuestionService.add(question);
        }
        assertIterableEquals(expected, javaQuestionService.getAll());
    }
    @Test
    public void testRemove(){
        for (Question question : expected) {
            javaQuestionService.add(question);
        }
        expected.remove(new Question("int", "тип переменной, имеющей значение целого числа"));
        javaQuestionService.remove(new Question("int", "тип переменной, имеющей значение целого числа"));
        assertIterableEquals(expected, javaQuestionService.getAll());
    }
}
