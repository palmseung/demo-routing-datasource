package com.example.demoroutingdatasource.member

import com.example.demoroutingdatasource.member.domain.Member
import com.example.demoroutingdatasource.member.service.MemberOuterService
import com.example.demoroutingdatasource.member.service.MemberServiceToSave
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val service: MemberServiceToSave,
    private val outer: MemberOuterService
) {

    private val log = KotlinLogging.logger { }

    @PostMapping("/members-master")
    fun saveAllMaster(): List<Member> {
        val members = service.saveAllWithRW(
            listOf(
                Member(name = "Master_Jay", isActive = true),
                Member(name = "Master_Haon", isActive = true),
                Member(name = "Master_Jessie", isActive = true)
            )
        )
        log.info { ">>>> master db initialized. members = $members" }
        return members
    }

    @PostMapping("/members-slave")
    fun saveAllSlave(): List<Member> {
        val members = service.saveAllWithRo(
            listOf(
                Member(name = "Slave_Jay", isActive = true),
                Member(name = "Slave_Haon", isActive = true),
                Member(name = "Slave_Jessie", isActive = true)
            )
        )
        log.info { ">>>> slave db initialized. members = $members" }
        return members
    }

    @GetMapping("/members/tx-with-in-ro-out-rw")
    fun getWithInRoOutRw(): Member? {
        return outer.getByIdWithInnerRoAndOuterRW(id = 2)
    }

    @GetMapping("/members/tx-with-in-rw-out-ro")
    fun getWithInRwOutRo(): Member? {
        return outer.getByIdWithInnerRWAndOuterRO(id = 2)
    }

    @GetMapping("/members/tx-with-in-ro-out-none")
    fun getWithInRoOutNone(): Member? {
        return outer.getByIdWIthInnerROAndOuterNone(id = 2)
    }

    @GetMapping("/members/tx-with-in-rw-out-none")
    fun getWithInRwOutNone(): Member? {
        return outer.getByIdWithInnerRWAndOuterNone(id = 2)
    }

    @GetMapping("/members/tx-with-in-ro-with-require-new-and-out-rw")
    fun getWithInRoWithRequireNewAndOutRw(): Member? {
        return outer.getByWithInnerROWithRequireNewAndOuterRW(id = 2)
    }

    @GetMapping("/members/tx-with-in-rw-with-require-new-and-out-ro")
    fun getWithInRwWithRequireNewAndOutRo(): Member? {
        return outer.getByWithInnerRWWithRequireNewAndOuterRO(id = 2)
    }

    @GetMapping("/members/tx-with-in-ro-with-fixed-and-out-rw")
    fun getWithInRoWithFixedAnnotationAndOutRw(): Member? {
        return outer.getInnerROWithFixedAnnotationAndOuterRW(id = 2)
    }

    @GetMapping("/members/tx-with-in-rw-with-fixed-and-out-ro")
    fun getWithInRWWithFixedAnnotationAndOutRO(): Member? {
        return outer.getInnerRWWithFixedAnnotationAndOuterRO(id = 2)
    }

}