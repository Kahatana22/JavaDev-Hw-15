package com.example.JavaDevHw15.services;

import com.example.JavaDevHw15.entities.Note;
import com.example.JavaDevHw15.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return (List<Note>) noteRepository.findAll();
    }

    public Note add(Note note) {
        if (!Objects.isNull(note)) {
            return noteRepository.save(note);
        }
        return null;
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        Note currentNote = noteRepository.findById(note.getId()).get();
        currentNote.setTitle(note.getTitle());
        currentNote.setContent(note.getContent());
        noteRepository.save(currentNote);
    }

    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }
}