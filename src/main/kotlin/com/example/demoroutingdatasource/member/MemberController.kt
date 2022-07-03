package com.example.demoroutingdatasource.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    @Autowired
    val service: MemberService
) {

    @PostMapping("/api/members")
    fun save(@RequestBody member: Member): Member {
        return service.save(member)
    }

    @GetMapping("/api/members/{id}")
    fun get(@PathVariable id: Long): Member? {
        return service.getMember(id)
    }

    @PostMapping("/api/members/combi/{id}")
    fun getAndSave(@RequestBody member: Member): Member? {
        return service.combi(member)
    }

    @PostMapping("/api/members/combi2")
    fun saveAndSave(@RequestBody member: Member): Member? {
        return service.combi2(member)
    }
}