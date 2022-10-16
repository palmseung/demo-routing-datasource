package com.example.demoroutingdatasource.member.service

import com.example.demoroutingdatasource.config.db.routing.EnforceMasterDataSource
import com.example.demoroutingdatasource.config.db.routing.EnforceSlaveDataSource
import com.example.demoroutingdatasource.member.domain.Member
import com.example.demoroutingdatasource.member.domain.MemberRepository
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class MemberInnerService(
    private val repository: MemberRepository
) {

    private val log = KotlinLogging.logger { }

    @Transactional(readOnly = true)
    fun getMemberWithRO(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

    @Transactional
    fun getMemberWithRW(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getMemberWithROWithRequireNew(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getMemberWithRWWithRequireNew(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

    @EnforceSlaveDataSource
    fun getInnerROWithFixedAnnotation(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

    @EnforceMasterDataSource
    fun getInnerRWWithFixedAnnotation(id: Long): Member? {
        return repository.findByIdOrNull(id)
    }

}