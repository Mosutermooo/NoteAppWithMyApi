package com.premium.noteappwithmyapi.repos

import android.util.Log
import com.example.model.notes.Note
import com.example.model.notes.NoteRequest
import com.example.model.notes.NoteResponse
import com.premium.noteappwithmyapi.network.ApiService
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.*

class NotesRepositoryTestImpl : NotesRepository   {

    override suspend fun addNoteByUserId(noteRequest: NoteRequest?) : Response<NoteResponse>{
        val noteFakeRepo = arrayListOf<NoteRequest>()
        val note = noteRequest?.note
        val id = noteRequest?.userId
        return if(note != null && id != null){
            noteFakeRepo.add(noteRequest)
            val noteResponse = NoteResponse(
                true,
                "Note Successfully added",
                null
            )
            Log.e("notes", "$noteFakeRepo")
            Response.success(noteResponse)
        }else{
            Response.error(400, ResponseBody.create(null, "Please enter a valid userId, and note"))
        }
    }

    override suspend fun getNotesByUserId(userId: Int?) : Response<NoteResponse> {

        val noteResponse = NoteResponse(
            true,
            "notes",
            notes
        )
        return if(userId != null && userId > 0){
            if(notes.isNotEmpty()){
                Response.success(noteResponse)
            }else{
                Response.error(400, ResponseBody.create(null, "This user doesn't have notes"))
            }
        }else{
            Response.error(400, ResponseBody.create(null, "Please enter a valid userId"))
        }
    }



    override suspend fun getNoteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNoteById(id: Int, noteRequest: NoteRequest) {
        TODO("Not yet implemented")
    }

    val notes = listOf<Note>(
        Note(0,1, "Test Note 1", 2),
        Note(0,2, "Test Note 2", 2),
        Note(0,3, "Test Note 3", 2),
        Note(0,4, "Test Note 4", 2)
    )
}