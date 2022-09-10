package com.premium.noteappwithmyapi.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.notes.Note
import com.example.model.notes.NoteRequest
import com.example.model.notes.NoteResponse
import com.premium.noteappwithmyapi.models.AlreadyLoggedInUser
import com.premium.noteappwithmyapi.network.ApiInstance
import com.premium.noteappwithmyapi.repos.AuthRepositoryImpl
import com.premium.noteappwithmyapi.repos.AuthRepositoryTestImpl
import com.premium.noteappwithmyapi.repos.NotesRepositoryImpl
import com.premium.noteappwithmyapi.repos.NotesRepositoryTestImpl
import com.premium.noteappwithmyapi.utils.DataStore
import com.premium.noteappwithmyapi.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val repository: NotesRepositoryImpl = NotesRepositoryImpl(ApiInstance.apiService)
    private val testRepository: NotesRepositoryTestImpl = NotesRepositoryTestImpl()
    val getNotesState : MutableStateFlow<Resource<NoteResponse>> = MutableStateFlow(Resource.Idle())
    val addNoteState : MutableStateFlow<Resource<String>> = MutableStateFlow(Resource.Idle())
    private val dataStore = DataStore(app)

    fun getNotes() = viewModelScope.launch {
        getNotesState.emit(Resource.Loading())
        val id = dataStore.read("id")
        Log.e("userId", "$id")
        if(id != null){
           val response = repository.getNotesByUserId(id.toInt())
           if(response.isSuccessful){
               response.body()?.let {
                   getNotesState.emit(Resource.Success(it))
               }
           }else{
               getNotesState.emit(Resource.Error(null ,response.message()))
           }
        }
    }

    fun addNote(noteRequest: NoteRequest) = viewModelScope.launch {
        addNoteState.emit(Resource.Loading())
        if(noteRequest.note != "" && noteRequest.userId > 0){
            val response = testRepository.addNoteByUserId(noteRequest)
            if(response.isSuccessful){
                response.body()?.let {
                    addNoteState.emit(Resource.Success(it.message))
                }
            }else{
                addNoteState.emit(Resource.Error(null, response.message()))
            }
        }
    }


}