package com.example.demoroutingdatasource.config.db.routing

import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Transactional(propagation = Propagation.REQUIRES_NEW)
annotation class EnforceMasterDataSource()
