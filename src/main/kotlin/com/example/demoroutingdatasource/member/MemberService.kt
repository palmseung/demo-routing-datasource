package com.example.demoroutingdatasource.member

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.persistence.Tuple
import javax.sql.DataSource


@Service
class MemberService(
    @Autowired
    val repository: MemberRepository,

    @Autowired
    val service2: Member2Service,

    @Autowired
    val dataSource: DataSource
) {

    val log = KotlinLogging.logger { }

    @Transactional
    fun getAndSaveWithRequire(member: Member): Member {
        val saved = repository.save(member)
        return saved
    }

    @Transactional
    fun getAndSaveWithRequireNew(member: Member): Member {
        val findById = service2.getMemberReadOnlyAndRequireNew(3)
        val saved = repository.save(member)

        log.info { "saved = $saved, findById = $findById" }

        return saved
    }

    @Transactional(readOnly = true)
    fun getAndSaveWithRequireInReadOnly(member: Member): Member {
        val findById = service2.getMemberReadOnlyAndRequire(3)
        val saved = repository.save(member)

        log.info { "saved = $saved, findById = $findById" }

        return saved
    }

    @Transactional(readOnly = true)
    fun getAndSaveAndFlushWithRequireInReadOnly(member: Member): Member {
        val findById = service2.getMemberReadOnlyAndRequire(3)
        val saved = repository.save(member)

        log.info { "saved = $saved, findById = $findById" }

        return saved
    }

    @Transactional(readOnly = true)
    fun getAndSaveWithRequireNewInReadOnly(member: Member): Member {
        val findById = service2.getMemberReadOnlyAndRequireNew(3)
        val saved = repository.save(member)

        log.info { "saved = $saved, findById = $findById" }

        return saved
    }

    @Transactional
    fun saveFlushAndGetInReadOnlyRequireNew(member: Member): Member {
        val saved = repository.save(member)
        val findById = service2.getMemberReadOnlyAndRequireNew(3)

        log.info { "saved = $saved, findById = $findById" }

        return saved
    }

    @Transactional(readOnly = true)
    fun getMember(id: Long): Member? {
        log.info { ">>>>> isReadOnly = ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}" }
        val member = repository.findById(id).orElse(null)
        return member
    }

    @Transactional
    fun combi(member: Member): Result {
        val getMember = service2.getMemberReadOnlyAndRequire(5)
        val ret = service2.save(member)
        return Result(master = ret, slave = getMember)
    }

    @Transactional
    fun combi2(member: Member): Result {
        val getMember = service2.getMemberReadOnlyAndRequireNew(5)
        val ret = service2.save(member)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi3(member: Member): Result {
        val ret = service2.save(member)
        val getMember = service2.getMemberReadOnlyAndRequire(5)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi3_1(member: Member): Result {
        val ret = service2.saveWithRequrieNew(member)
        val getMember = service2.getMemberReadOnlyAndRequire(5)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi3_2(member: Member): Result {
        val ret = service2.saveWithRequrieNew(member)
        val getMember = service2.getMemberReadOnlyAndRequireNew(5)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi4(member: Member): Result {
        val ret = service2.saveWithRequrieNew(member)
        val getMember = service2.getMemberReadOnlyAndRequire(5)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi4_1(member: Member): Result {
        val ret = service2.saveWithRequrieNew(member)
        val getMember = service2.getMemberReadOnlyAndRequireNew(5)
        return Result(master = ret, slave = getMember)
    }

    @Transactional(readOnly = true)
    fun combi4_2(member: Member): Result {
        val getMember = service2.getMemberReadOnlyAndRequireNew(5)
        val ret = service2.saveWithRequrieNew(member)
        return Result(master = ret, slave = getMember)
    }
}
