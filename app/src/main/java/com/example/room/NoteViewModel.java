package com.example.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private LiveData<List<Note>> allNotes ;
    private NotesRepo notesRepo ;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        notesRepo = new NotesRepo(application);
    }

    public LiveData<List<Note>> getAllNotes() {
        allNotes = notesRepo.getAllNotes();
        return allNotes;
    }

    public void insert(Note note){
        notesRepo.insert(note);
    }
    public void update(Note note){
        notesRepo.update(note);
    }
    public void delete(Note note){
        notesRepo.delete(note);
    }
    public void deleteAllNotes(){
        notesRepo.deleteAll();
    }
}
