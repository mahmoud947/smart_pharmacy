package com.example.curativepis.core.presentation.screen.home_screen

sealed class HomeScreenEvent {
    object OPenNote:HomeScreenEvent()
    object SignOut:HomeScreenEvent()
}