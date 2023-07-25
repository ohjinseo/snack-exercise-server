package com.soma.snackexercise.service.member;

import com.soma.snackexercise.domain.member.Member;
import com.soma.snackexercise.dto.member.JoinListMemberDto;
import com.soma.snackexercise.dto.member.request.MemberUpdateRequest;
import com.soma.snackexercise.dto.member.response.GetOneGroupMemberResponse;
import com.soma.snackexercise.dto.member.response.MemberResponse;
import com.soma.snackexercise.exception.MemberNameAlreadyExistsException;
import com.soma.snackexercise.exception.MemberNotFoundException;
import com.soma.snackexercise.repository.member.MemberRepository;
import com.soma.snackexercise.util.constant.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDeletionService memberDeletionService;

    @Transactional
    public MemberResponse update(String email, MemberUpdateRequest request) {
        Member member = memberRepository.findByEmailAndStatus(email, Status.ACTIVE).orElseThrow(MemberNotFoundException::new);

        if (!member.getName().equals(request.getName())) {
            validateDuplicateName(request.getName());
        }

        member.update(request);
        return MemberResponse.toDto(member);
    }

    private void validateDuplicateName(String name) {
        if (memberRepository.existsByName(name)) {
            throw new MemberNameAlreadyExistsException(name);
        }
    }


    // TODO : 방장이 회원 탈퇴하거나 회원이 그룹에서 나가면 joinList만 inActive해서 개인랭킹에서 보여지도록
    /*
    회원 탈퇴하기
    joinList 모두 삭제
    관련 알림 모두 삭제
    관련 회원_운동 삭제
    관련 미션 모두 삭제
     */
    public void delete(String email) {
        memberDeletionService.deleteMember(email);
    }
}
