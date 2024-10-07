package com.thangavel.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.thangavel.sqlite.dto.NotesDto

class NotesDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun insertNotes(notesDto: NotesDto) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, notesDto.title)
            put(COLUMN_CONTENT, notesDto.content)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateNotes(notes: NotesDto) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, notes.title)
            put(COLUMN_CONTENT, notes.content)
        }
        val whereClaus = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(notes.id.toString())
        db.update(TABLE_NAME, values, whereClaus, whereArgs)
        db.close()
    }

    fun getNoteByID(noteId: Int): NotesDto {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteId "
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        cursor.close()
        db.close()
        return NotesDto(id, title, content)

    }

    fun deleteNotes(id: Int) {
        val db = writableDatabase
        val whereClaus = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(id.toString())
        db.delete(TABLE_NAME, whereClaus, whereArgs)
        db.close()
    }

    fun getAllNotes(): List<NotesDto> {
        val noteList = mutableListOf<NotesDto>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            noteList.add(NotesDto(id, title, content))
        }
        cursor.close()
        db.close()
        return noteList
    }
}