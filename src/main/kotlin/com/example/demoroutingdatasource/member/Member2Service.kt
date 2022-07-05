package com.example.demoroutingdatasource.member

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class Member2Service(
    val repository: MemberRepository
) {

    val log = KotlinLogging.logger { }

    @Transactional
    fun getMember(id: Long): Member? {
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional(readOnly = true)
    fun getMemberReadOnlyAndRequire(id: Long): Member? {
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getMemberReadOnlyAndRequireNew(id: Long): Member? {
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional
    fun save(member: Member): Member? {
        return repository.save(member)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun saveWithRequrieNew(member: Member): Member? {
        val ret = repository.save(member)
        return ret
    }

}