package com.example.demoroutingdatasource.member.domain

import com.example.demoroutingdatasource.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MemberRepository : JpaRepository<Member, Long>
