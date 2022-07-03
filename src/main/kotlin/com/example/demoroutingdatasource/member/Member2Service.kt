package com.example.demoroutingdatasource.member

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Service
class Member2Service(
    val repository: MemberRepository
) {

    val log = KotlinLogging.logger { }

//    @Transactional(readOnly = true)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    fun getMember2(id: Long): Member? {
        log.info { ">>> isActive = ${TransactionSynchronizationManager.isActualTransactionActive()}" }
        log.info { ">>>>> isReadOnly = ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}" }
        log.info { ">>>>> currentTransaction = ${TransactionSynchronizationManager.getCurrentTransactionName()}" }
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional
    fun save2(member: Member): Member? {
        val ret = repository.save(member)
//        val ret = repository.saveAndFlush(member)
        return ret
    }

}