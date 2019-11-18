package com.rodionov.giphy_app.db

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rodionov on 18.11.2019.
 */
class DB {

    companion object {
        val TAG = "DB"
        const val REALM_SCHEMA_VERSION: Long = 1
    }

    val realm: Realm

    init {
        realm = getRealmWithoutMigration()
    }

    private fun getRealmWithoutMigration(): Realm {
        val builder = RealmConfiguration.Builder()
        val config = builder
            .schemaVersion(REALM_SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        return Realm.getDefaultInstance()
    }
}