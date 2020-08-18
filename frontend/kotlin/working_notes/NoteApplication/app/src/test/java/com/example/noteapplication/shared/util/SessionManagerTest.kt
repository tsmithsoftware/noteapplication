package com.example.noteapplication.shared.util

import android.content.Context
import android.content.SharedPreferences
import com.example.noteapplication.R
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test

class SessionManagerTest {

    private val mockContext: Context = mock()
    private val mockSharedPreferences: SharedPreferences = mock()
    private val mockEditor: SharedPreferences.Editor = mock()
    private val token = "user_token"
    private val appName = "NoteApplication"

    @Before
    fun setup() {
        whenever(mockSharedPreferences.getString(SessionManager.USER_TOKEN, null)).thenReturn(token)
        whenever(mockContext.getSharedPreferences(any(), any())).thenReturn(mockSharedPreferences)
        whenever(mockContext.getString(R.string.app_name)).thenReturn(appName)
        whenever(mockSharedPreferences.edit()).thenReturn(mockEditor)
    }

    @Test
    fun testSessionManagerSavesAuthTokenWithCorrectString() {
        SessionManager(mockContext).saveAuthToken(token)
        verify(mockContext).getSharedPreferences(appName, Context.MODE_PRIVATE)
        verify(mockEditor).putString(SessionManager.USER_TOKEN, token)
        verify(mockEditor).apply()
    }

    @Test
    fun testSessionManagerCallsSavedPreferencesToGetToken() {
        assert(SessionManager(mockContext).fetchAuthToken() == token)
    }
}