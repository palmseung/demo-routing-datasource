package com.example.demoroutingdatasource.config.db.routing

class RoutingDatasourceContextHolder {

    companion object {
        val CONTEXT = ThreadLocal<DatasourceType>()

        fun set(db: DatasourceType) {
            CONTEXT.set(db)
        }

        fun get(): DatasourceType {
            return CONTEXT.get()
        }

        fun remove() {
            CONTEXT.remove()
        }
    }

}