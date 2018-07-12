package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by jxzhong on 2017/9/23.
 */
public class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    public void should_return_right_answer_when_given_a_validate_input_str(){
        Answer a = new Answer();
        Answer b = new Answer();
        String inputStr = "1 2 3 4";
        List<String> inputList = new ArrayList<>();
        inputList.add(inputStr);
        b.setNumList(inputList);
        assertThat(a.createAnswer(inputStr).toString(),is(b.toString()));
    }

    @Test
    public void should_pass_validate_when_given_a_validate_list(){
        try {
            actualAnswer.validate();
        } catch (AnswerFormatIncorrectException exception) {
            fail("Validate should pass!");
        }
    }

    @Test
    public void should_fail_validate_when_given_a_not_validate_list(){
        Answer answer = new Answer();
        String str = "1 2 3 4 5";
        List<String> numList = Arrays.stream(str.split(" ")).
                collect(Collectors.toList());
        answer.setNumList(numList);
        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException exception) {
            fail("Validate should pass!");
        }
    }

    @Test
    public void should_check_successfully_when_given_a_right_list(){

    }

}