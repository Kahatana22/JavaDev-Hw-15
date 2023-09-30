package com.example.JavaDevHw15.controllers;

import com.example.JavaDevHw15.entities.Note;
import com.example.JavaDevHw15.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping(value = "/list")
    public ModelAndView getListOfNotes() {
        ModelAndView model = new ModelAndView("note/list");
        model.addObject("notes", noteService.listAll());
        return model;
    }

    @GetMapping(value = "/create")
    public String createNote() {
        return "note/note";
    }

    @PostMapping(path = "/create")
    public ModelAndView updateListOfNodes(@RequestParam(name = "note") String newNote, @RequestParam(name = "title") String newTitle) {
        noteService.add(new Note(newTitle, newNote));
        return new ModelAndView("redirect:/note/list");
    }

    @PostMapping(path = "/delete")
    public String deleteNote(@RequestParam("id") Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping(value = "/edit")
    public ModelAndView editNote(@RequestParam("id") Long id) {
        ModelAndView edit = new ModelAndView("note/edit");
        Note note = noteService.getById(id).get();
        edit.addObject("note", note);
        return edit;
    }

    @PostMapping(path = "/edit")
    public String updateNote(@ModelAttribute("note") Note updateNote) {
        noteService.update(updateNote);
        return "redirect:/note/list";
    }
}