package com.example.demoroutingdatasource.member.service

import com.example.demoroutingdatasource.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberOuterService(
    private val inner: MemberInnerService
) {

    @Transactional
    fun getByIdWithInnerRoAndOuterRW(id: Long): Member? {
        return inner.getMemberWithRO(id)
    }

    @Transactional(readOnly = true)
    fun getByIdWithInnerRWAndOuterRO(id: Long): Member? {
        return inner.getMemberWithRW(id)
    }

    fun getByIdWithInnerRWAndOuterNone(id: Long): Member? {
        return inner.getMemberWithRW(id)
    }

    fun getByIdWIthInnerROAndOuterNone(id: Long): Member? {
        return inner.getMemberWithRO(id)
    }

    @Transactional
    fun getByWithInnerROWithRequireNewAndOuterRW(id: Long): Member? {
        return inner.getMemberWithROWithRequireNew(id)
    }

    @Transactional
    fun getByWithInnerRWWithRequireNewAndOuterRO(id: Long): Member? {
        return inner.getMemberWithRWWithRequireNew(id)
    }

    @Transactional
    fun getInnerROWithFixedAnnotationAndOuterRW(id: Long): Member? {
        return inner.getInnerROWithFixedAnnotation(id)
    }

    @Transactional(readOnly = true)
    fun getInnerRWWithFixedAnnotationAndOuterRO(id: Long): Member? {
        return inner.getInnerRWWithFixedAnnotation(id)
    }
}