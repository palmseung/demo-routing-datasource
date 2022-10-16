package com.example.demoroutingdatasource.config.db.routing

import mu.KotlinLogging
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource


open class RoutingDataSource(
    rwDataSource: DataSource,
    roDataSource: DataSource
) : AbstractRoutingDataSource() {
    private val log = KotlinLogging.logger { }

    init {
        super.setTargetDataSources(
            mapOf(
                DatasourceType.MASTER to rwDataSource,
                DatasourceType.SLAVE to roDataSource,
            )
        )
    }

    override fun determineCurrentLookupKey(): Any? {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("Datasource is routed to readonly")
            DatasourceType.SLAVE
        } else {
            log.info("Datasource is routed to read-write")
            DatasourceType.MASTER
        }
    }
}