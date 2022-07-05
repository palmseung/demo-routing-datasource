package com.example.demoroutingdatasource.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    @Autowired
    val service: MemberService,

    @Autowired
    val service2: Member2Service
) {

    @PostMapping("/api/members")
    fun save(@RequestBody member: Member): Member? {
        return service2.save(member)
    }

    @GetMapping("/api/members/{id}")
    fun get(@PathVariable id: Long): Member? {
        return service.getMember(id)
    }

    /*
    @Transactional 안에서 @Transactional(readonly=true) 메소드 호출해도 master 바라봄
     */
    @PostMapping("/api/members/combi1")
    fun combi1(@RequestBody member: Member): Result {
        return service.combi(member)
    }

    /*
    @Transactional 안에서 @Transactional(readonly=true, propagation=REQUIRE_NEW) 메소드 호출하면 slave 바라봄
     */
    @PostMapping("/api/members/combi2")
    fun combi2(@RequestBody member: Member): Result {
        return service.combi2(member)
    }

    /*
    @Transactional(readonly=true) 안에서 @Transactional(readonly = true)랑 @Transactional 메소드 모두 slave 바라봄 (insert도 slave로 됨)
     */
    @PostMapping("/api/members/combi3")
    fun combi3(@RequestBody member: Member): Result {
        return service.combi3(member)
    }

    /*
    @Transactional(readonly=true) 안에서 @Transactional(readonly = true)랑 @Transactional 메소드 모두 master 바라봄 (insert도 slave로 됨)
     */
    @PostMapping("/api/members/combi3-1")
    fun combi3_1(@RequestBody member: Member): Result {
        return service.combi3_1(member)
    }


    /*
    @Transactional(readonly=true) 안에서 @Transactional(readonly = true)랑 @Transactional 메소드 모두 master 바라봄 (insert도 slave로 됨)
     */
    @PostMapping("/api/members/combi3-2")
    fun combi3_2(@RequestBody member: Member): Result {
        return service.combi3_2(member)
    }

    /*
    @Transactional(readonly=true) 안에서 @Transactional(readonly = true)랑 @Transactional 메소드 호출하면 master 바라봄
     */
    @PostMapping("/api/members/combi4")
    fun combi4(@RequestBody member: Member): Result {
        return service.combi4(member)
    }
}