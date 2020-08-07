package com.example.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance ;



    public abstract NoteDao getNoteDao();

    public static synchronized NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,
                    "note_database")
                    .addCallback(callback)
                    .fallbackToDestructiveMigration() // delete the db and recreate it from scratch when there is a change
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // fill the database on creation off the main thread
            new PopulateDbAsyncTask(instance).execute();
        }
    };



    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao ;

        public PopulateDbAsyncTask(NotesDatabase notesDatabase) {
            noteDao = notesDatabase.getNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title1", "desc1", 1));
            noteDao.insert(new Note("title2", "desc2", 2));
            noteDao.insert(new Note("title3", "desc3", 3));
            return null;
        }
    }
}
