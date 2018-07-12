package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;

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

//    @Test
//    public void should_return_right_answer_when_given_a_validate_input_str(){
//        Answer a = new Answer();
//        Answer b = new Answer();
//        String inputStr = "1 2 3 4";
//        List<String> inputList = new ArrayList<>();
//        inputList.add(inputStr);
//        b.setNumList(inputList);
//        assertThat(a.createAnswer(inputStr).toString(),is(b.toString()));
//    }

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
        Answer answer = Answer.createAnswer("1 2 3 4 5");
        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException exception) {
            fail("Validate should pass!");
        }
    }

    @Test
    public void should_check_successfully_when_given_a_list_with_current_num(){
        Answer result = Answer.createAnswer("1 2 3 4");
        Record record = actualAnswer.check(result);
        assertThat(record.getValue(),is("4A0B"));
    }

    @Test
    public void should_check_successfully_when_given_a_list_with_include_only_num(){
        Answer result = Answer.createAnswer("4 3 2 1");
        Record record = actualAnswer.check(result);
        assertThat(record.getValue(),is("0A4B"));
    }

    @Test
    public void should_check_successfully_when_given_a_list_with_both_current_num_and_include_only_num(){
        Answer result = Answer.createAnswer("1 2 4 3");
        Record record = actualAnswer.check(result);
        assertThat(record.getValue(),is("2A2B"));
    }

    @Test
    public void should_check_successfully_when_given_a_list_with_none_current_num_and_include_only_num(){
        Answer result = Answer.createAnswer("0 0 0 0");
        Record record = actualAnswer.check(result);
        assertThat(record.getValue(),is("0A0B"));
    }

}