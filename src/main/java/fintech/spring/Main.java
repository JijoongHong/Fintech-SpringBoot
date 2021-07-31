/*
package fintech.spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Member;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Throwable{
        ObjectMapper mapper = new ObjectMapper();
        Member builtMember = new Member("myid", "myname", "koscom");
                //Member.builder().id("myid").name("myname").org("koscom").build();

        //직렬화
        String json = mapper.writeValueAsString(builtMember);
        byte[] byteStream = json.getBytes(); //네트워크 전송용
        System.out.println(json);

        //역직렬화
        String inputString = new String(byteStream);
        Member readObject = mapper.readValue(inputString, Member.class); //json 넣어도됨
        System.out.println(readObject);
    }

}
*/
