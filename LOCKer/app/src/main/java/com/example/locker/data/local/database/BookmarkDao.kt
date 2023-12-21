package com.example.locker.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: BookmarkEntity)

    @Query("DELETE from bookmark where id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM bookmark WHERE bookmark = 1")
    fun getAllBookmark(): LiveData<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT * FROM bookmark WHERE id =:id)")
    fun isBookmark(id: Int): Boolean

}