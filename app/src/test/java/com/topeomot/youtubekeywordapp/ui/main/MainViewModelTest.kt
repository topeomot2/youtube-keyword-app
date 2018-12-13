package com.topeomot.youtubekeywordapp.ui.main

import android.content.SharedPreferences
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.data.DataRepository
import com.topeomot.youtubekeywordapp.data.YoutubeRepository
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import com.topeomot.youtubekeywordapp.model.exception.NoSubjectMatterException
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class MainViewModelTest{

    lateinit var dataRepository: DataRepository

    lateinit var sharedPreference: SharedPreferences

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        dataRepository = mock(YoutubeRepository::class.java)
        sharedPreference = mock(SharedPreferences::class.java)
        mainViewModel = MainViewModel(dataRepository, sharedPreference)

    }


    @Test
    fun emptyPreference_getSubjectMatter_throwException() {
        `when`(sharedPreference.getString(YoutubeKeywordConstant.SUBJECT_MATTER,""))
            .thenReturn("")
        try{
            mainViewModel.getSubjectMatter()
            fail("Expected NoSubjectMatterException Exception")
        } catch (ex: NoSubjectMatterException){
            assertEquals("cache expired", ex.message)
        }


    }

    @Test
    fun subjectInPreference_getSubjectMatter_returnSubjectMatter() {
        `when`(sharedPreference.getString(YoutubeKeywordConstant.SUBJECT_MATTER,""))
            .thenReturn("kpop")
        try{
            val response = mainViewModel.getSubjectMatter()
            assertEquals("kpop", response)
        } catch (ex: NoSubjectMatterException){
            fail("Unexpected NoSubjectMatterException Exception")
        }
    }

    @Test
    fun setSubjectMatter_confirmSave() {
        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(sharedPreference.edit()).thenReturn(editor)

        mainViewModel.setSubjectMatter("kpop")

        verify(editor).putString(any(String::class.java), any(String::class.java))
    }


    @Test
    fun searchCall_getResponse() {
        val testString = "movie"
        `when`(dataRepository.getSearchResult("kpop")).thenReturn(Flowable.just(
            YoutubeResponse(
                kind = testString
            )
        ));

        mainViewModel.search("kpop")
            .subscribe ({ response: YoutubeResponse? ->
                assertEquals(response?.kind, testString)
            },
                { error: Throwable? ->  fail("No Result")})
    }
}