package com.example.demoroutingdatasource.member

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager


@Service
class MemberService(
    @Autowired
    val repository: MemberRepository,
    @Autowired
    val service2: Member2Service
) {

    val log = KotlinLogging.logger { }

    @Transactional
    fun save(member: Member): Member {
        log.info { ">>>>> isReadOnly = ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}" }
        log.info { ">>>>> currentTransacition = ${TransactionSynchronizationManager.getCurrentTransactionName()}" }

        val saved = repository.save(member)
        return saved
    }

    @Transactional(readOnly = true)
    fun getMember(id: Long): Member? {
        log.info { ">>>>> isReadOnly = ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}" }
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional
    fun combi(member: Member): Member? {
        log.info { ">>>>> isReadOnly1 = ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}" }
        log.info { ">>>>> currentTransacition1 = ${TransactionSynchronizationManager.getCurrentTransactionName()}" }
        val member2 = service2.getMember2(5)
        save(member)
        return member2
    }

    @Transactional
    fun combi2(member: Member): Member? {
        log.info { ">>> isActive = ${TransactionSynchronizationManager.isActualTransactionActive()}" }
        val ret1 = service2.getMember2(5)
        log.info { "ret1 = $ret1" }
        val ret = service2.save2(member)
        log.info { "ret = ${ret}" }
        return ret
    }

}
