package com.example.demoroutingdatasource.config.db

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
                DatasourceType.READ_WRITE to rwDataSource,
                DatasourceType.READ_ONLY to roDataSource,
            )
        )
    }

    override fun determineCurrentLookupKey(): Any? {
        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("Datasource is routed to readonly")
            DatasourceType.READ_ONLY
        } else {
            log.info("Datasource is routed to read-write")
            DatasourceType.READ_WRITE
        }
    }
}