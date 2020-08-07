package com.example.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class NotesRepo {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;


    public NotesRepo(Application application) {
        // get a database instance
        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);

        // choose which table to access and get its dao
        noteDao = notesDatabase.getNoteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note){
        new InsertAsyncTask(noteDao).execute(note);
    }



    public void update(Note note){
        new UpdateAsyncTask(noteDao).execute(note);
    }



    public void delete(Note note){
        new DeleteAsyncTask(noteDao).execute(note);
    }


    public void deleteAll(){
        new DeleteAllAsyncTask(noteDao).execute();
    }


    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao ;


        public InsertAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class UpdateAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao ;


        public UpdateAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao ;


        public DeleteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao ;


        public DeleteAllAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAll();
            return null;
        }
    }



}
