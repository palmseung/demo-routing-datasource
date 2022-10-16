package com.example.demoroutingdatasource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = ["com.example.demoroutingdatasource"])
@EnableAspectJAutoProxy
class DemoRoutingDatasourceApplication

fun main(args: Array<String>) {
	// test3
	runApplication<DemoRoutingDatasourceApplication>(*args)
}
