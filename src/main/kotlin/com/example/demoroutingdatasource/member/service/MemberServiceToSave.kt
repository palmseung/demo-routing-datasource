package com.example.demoroutingdatasource.member.service

import com.example.demoroutingdatasource.member.domain.Member
import com.example.demoroutingdatasource.member.domain.MemberRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceToSave(
    private val repository: MemberRepository
) {

    val log = KotlinLogging.logger { }

    @Transactional
    fun saveAllWithRW(members: List<Member>): List<Member> {
        return repository.saveAll(members)
    }

    @Transactional(readOnly = true)
    fun saveAllWithRo(members: List<Member>): List<Member> {
        return repository.saveAll(members)
    }
}