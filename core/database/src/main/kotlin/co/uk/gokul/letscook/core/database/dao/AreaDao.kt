package co.uk.gokul.letscook.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.uk.gokul.letscook.core.database.entities.AreaEntity

/**
 * Data Access Object for the [AreaEntity].
 */
@Dao
interface AreaDao {

    /**
     * Inserts a list of areas into the database.
     * Replaces existing entries on conflict.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAreas(areas: List<AreaEntity>)

    /**
     * Retrieves all areas from the database, ordered by name.
     */
    @Query("SELECT * FROM areas ORDER BY strArea ASC")
    suspend fun getAreas(): List<AreaEntity>
}