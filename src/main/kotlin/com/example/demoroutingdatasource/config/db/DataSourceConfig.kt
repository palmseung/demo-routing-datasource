package com.example.demoroutingdatasource.config.db

import com.example.demoroutingdatasource.config.db.routing.RoutingDataSource
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
class DataSourceConfig {
    @Bean(name = ["rwDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.rw.hikari")
    fun rwDataSource(): DataSource {
        return HikariDataSource()
    }

    @Bean(name = ["roDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.ro.hikari")
    fun roDataSource(): DataSource {
        return HikariDataSource()
    }

    @Primary
    @Bean(name = ["routingDataSource"])
    fun routingDataSource(
        @Qualifier("rwDataSource") rwDataSource: DataSource,
        @Qualifier("roDataSource") roDataSource: DataSource,
    ): DataSource {
        val routingDataSource = RoutingDataSource(rwDataSource, roDataSource)
        routingDataSource.setDefaultTargetDataSource(rwDataSource)

        return routingDataSource
    }

    @Bean
    fun dataSource(@Qualifier("routingDataSource") routingDataSource: DataSource): DataSource {
        return LazyConnectionDataSourceProxy(routingDataSource)
    }

    @Bean
    fun transactionManager(@Qualifier("routingDataSource") dataSource: DataSource?): PlatformTransactionManager? {
        val transactionManager = DataSourceTransactionManager()
        transactionManager.dataSource = dataSource
        return transactionManager
    }

}


