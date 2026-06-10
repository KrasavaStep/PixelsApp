package com.example.data.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data.database.entity.LikedPhotoEntity
import com.example.data.data.database.entity.PhotoEntity
import com.example.data.data.database.entity.PhotosWithLikedStatus

@Dao
interface PixelsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)

//    @Query("""
//        SELECT photos.*,
//               (liked_photos_table.id IS NOT NULL) AS isLiked
//        FROM photos
//        LEFT JOIN liked_photos_table ON photos.id = liked_photos_table.id
//    """)
    @Query("SELECT * FROM photos")
    fun getPagedPhotos(): PagingSource<Int, PhotoEntity>

    @Query("DELETE FROM photos")
    suspend fun clearAll()

    @Query("SELECT * FROM photos WHERE id = :id")
    suspend fun getPhotoById(id: Int): PhotoEntity

    @Query("""
        SELECT photos.*, 1 AS isLiked 
        FROM liked_photos_table
        INNER JOIN photos ON liked_photos_table.id = photos.id
    """)
    suspend fun getLikedPhotos(): List<PhotosWithLikedStatus>

    @Query("SELECT EXISTS(SELECT 1 FROM liked_photos_table WHERE id = :id)")
    fun checkPhotoIsLiked(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToBookmarks(liked: LikedPhotoEntity)

    @Query("DELETE FROM liked_photos_table WHERE id = :objectId")
    suspend fun removeFromBookmarks(objectId: Int)
}