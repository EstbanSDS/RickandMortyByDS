package com.example.rickandmortybyds.domain.model.repository

import com.example.rickandmortybyds.core.network.ApiServiceRickAndMorty
import com.example.rickandmortybyds.core.room.dao.RAMDao
import com.example.rickandmortybyds.core.room.entity.CharactersEntity
import com.example.rickandmortybyds.domain.model.RAMCharacterResponse
import com.example.rickandmortybyds.domain.model.RickAndMortyDetailResponse
import com.example.rickandmortybyds.domain.model.usecase.RAMEpisodeResponse
import com.example.rickandmortybyds.utils.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(

    private val apiService: ApiServiceRickAndMorty,
    private val ramCharacterDao: RAMDao,

): RickAndMortyRepository{
    override suspend fun getRickAndMortyDetail(): RickAndMortyDetailResponse {
        return withContext(Dispatchers.IO){
            apiService.getRickAndMortyInformationDetail().body()!!
        }
    }

    override fun getCharactersStream(): Flow<List<CharactersEntity>> {
        return ramCharacterDao.getAllCharacters()
    }

    override suspend fun getAllCharactersDB(): RickAndMortyDetailResponse {
        return withContext(Dispatchers.IO){
            val ramCharacters = apiService.getRickAndMortyInformationDetail()
            val body = ramCharacters.body()

            if (ramCharacters.isSuccessful){
                if (body!= null){
                    val ramEntities =body.results?.map { apiCharacter ->
                        val characterExsist = ramCharacterDao.getCharacterById(apiCharacter.id!!)
                        apiCharacter.toEntity()
                    }
//                    body
                    ramCharacterDao.insertAllCharacters(ramEntities!!)
                    body
                } else{
                    throw Exception("Cuerpo de respuesta vacio")
                }
            }else {
                throw Exception("Error del servidor: ${ramCharacters.code()}")
            }
        }
    }

    override suspend fun getCharacterByIdDB(characterId: Int): RAMCharacterResponse {
        val characterDB = ramCharacterDao.getCharacterById(characterId) ?: CharactersEntity()
        val character = characterDB.toEntity()
        return character
    }

    override suspend fun getEpisodeByNumber(episodeNumber: Int): RAMEpisodeResponse {
       return withContext(Dispatchers.IO) {
           apiService.getRAMEpisodeByNumber(episodeNumber)
       }
    }

    /*override suspend fun getEpisodeByNumber(episodeNumber: Int): RAMEpisodeResponse{

    }*/



}