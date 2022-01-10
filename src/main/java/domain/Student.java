package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair theName = new JsonPair("name", new JsonString(this.name));
        JsonPair theSurname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair theYear = new JsonPair("year", new JsonNumber(this.year));
        JsonObject examInfo[] = new JsonObject[exams.length];

        int idx = 0;
        for (Tuple<String, Integer> exam: this.exams){
            boolean checkPassed;
            JsonPair course = new JsonPair("course", new JsonString(exam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(exam.value));
            if (exam.value < 3){
                checkPassed = false;
            } else { checkPassed = true;}
            JsonPair passed = new JsonPair("passed", new JsonBoolean(checkPassed));
            JsonObject total = new JsonObject(course, mark, passed);
            examInfo[idx] = total;
            idx++;
        }
        JsonPair examArr = new JsonPair("exams", new JsonArray(examInfo));

        return new JsonObject(theName, theSurname, theYear, examArr);
    }
}