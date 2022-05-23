package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        memberService.join(member);
        return new CreateMemberResponse(member);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequestDto request){
        Member member = new Member();
        member.setName(request.getName());

        memberService.join(member);
        return new CreateMemberResponse(member);
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        private String name;

        public CreateMemberResponse(Member member){
            this.id = member.getId();
            this.name = member.getName();
        }
    }

    @Data
    static class CreateMemberRequestDto {

        @NotEmpty
        private String name;
    }
}
