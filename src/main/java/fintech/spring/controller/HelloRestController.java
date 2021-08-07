package fintech.spring.controller;

import fintech.spring.entity.Hello;
import fintech.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController//controller + repsonsebody
@RequiredArgsConstructor
public class HelloRestController {

    //@Autowired //field injection
    final MemberService memberService;

    //setter injection
    //@Autowired
    //private void setMembersService(MemberService memberService){ //왜 private? 솔리드 구현을 위해 변하지 않도록
        //this.memberService = memberService; //안하면 외부에서 호출해서 변경할 수 있기 때문임
    //}

/*    @Autowired//constructor injection
    private void HelloRestController(MemberService memberService){ //왜 private? 솔리드 구현을 위해 변하지 않도록
        this.memberService = memberService; //안하면 외부에서 호출해서 변경할 수 있기 때문임
    }*/

    @GetMapping("hello-string") //text
    public String helloString(String name){
        return String.format("Hello %s", name);
    }

    @GetMapping("hello-map") //json
    public Map<String, Object> helloMap(String name){
        Map<String, Object> m = new HashMap<>();
        m.put("name", name);
        return m;
    }

    @GetMapping("hello-object")
    public Hello helloObject(String name){
        Hello h = new Hello(name);
        return h;
    }
}
