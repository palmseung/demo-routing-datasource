package com.example.demoroutingdatasource.config.db

import com.example.demoroutingdatasource.member.service.MemberInnerService
import com.example.demoroutingdatasource.member.service.MemberServiceToSave
import mu.KotlinLogging
import org.springframework.context.annotation.Configuration

@Configuration
class DBInit(
    private val master: MemberServiceToSave,
    private val slave: MemberInnerService,
) {

    private val log = KotlinLogging.logger { }

}