package com.meridiane.betsson.model

import androidx.lifecycle.*
import com.meridiane.betsson.room.MainDatabase
import kotlinx.coroutines.launch

class MainViewModel(database: MainDatabase) : ViewModel() {
    private val dao = database.getDao()

    val allType : LiveData<List<MatchesType>> = dao.getAll().asLiveData()

    fun insertType(note:MatchesType) = viewModelScope.launch {
        dao.insert(note)
    }
    fun deleteEntity(id:Int) = viewModelScope.launch {
        dao.deleteEntity(id)
    }

    fun updateNote(note:MatchesType) = viewModelScope.launch {
        dao.updateItem(note)
    }

    fun initDB(list: List<MatchesType>) = viewModelScope.launch {
        dao.insertAll(list)
    }

    class MainViewModelFactory(private val database: MainDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {    // создаёт VM
            if (modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}