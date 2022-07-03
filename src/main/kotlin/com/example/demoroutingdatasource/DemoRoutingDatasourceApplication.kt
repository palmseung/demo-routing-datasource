package com.example.demoroutingdatasource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = ["com.example.demoroutingdatasource"])
class DemoRoutingDatasourceApplication

fun main(args: Array<String>) {
	runApplication<DemoRoutingDatasourceApplication>(*args)
}
