package fintech.spring.controller;

import fintech.spring.entity.Member;
import fintech.spring.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    final MemberService memberService;

    @GetMapping("/{key}")
    public Member getMember(@PathVariable("key") Long id,
                            @RequestParam(required = false) String name){//?뒤의 것들

        if (name != null)
            return memberService.findMember(id, name);
        else
            return memberService.findMember(id, name);
    }

    @PostMapping("/save")
    public void saveMember(@RequestBody Member member){
        memberService.addUser(member);
    }

    @GetMapping("/api/count")
    public List<Object> countByOrgGroup(@RequestParam Boolean isActive) {
        return memberService.countOrgGroup(isActive);
    }
}
