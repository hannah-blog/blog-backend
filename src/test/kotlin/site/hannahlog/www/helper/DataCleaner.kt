package site.hannahlog.www.helper

import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataCleaner {

    private val tableNames: MutableList<String> = ArrayList()

    @PersistenceContext
    private val entityManager: EntityManager? = null

    @PostConstruct
    fun findDatabaseTableNames() {
        @Suppress("UNCHECKED_CAST")
        val tableInfos: List<Array<Any?>?> = entityManager
            ?.createNativeQuery("SHOW TABLES")
            ?.resultList as List<Array<Any?>?>
        for (tableInfo in tableInfos) {
            val tableName = tableInfo!![0] as String?
            tableNames.add(tableName!!)
        }
    }

    @Transactional
    fun clear() {
        entityManager?.clear()
        truncate()
    }

    private fun truncate() {
        entityManager
            ?.createNativeQuery(String.format(FOREIGN_KEY_CHECK_FORMAT, 0))
            ?.executeUpdate()
        for (tableName in tableNames) {
            entityManager
                ?.createNativeQuery(String.format(TRUNCATE_FORMAT, tableName))
                ?.executeUpdate()
        }
        entityManager
            ?.createNativeQuery(String.format(FOREIGN_KEY_CHECK_FORMAT, 1))
            ?.executeUpdate()
    }

    companion object {
        private const val FOREIGN_KEY_CHECK_FORMAT = "SET REFERENTIAL_INTEGRITY %d"
        private const val TRUNCATE_FORMAT = "TRUNCATE TABLE %s"
    }
}