package com.appcent.animatedbutton

sealed class UIState {
    object UnLike : UIState()
    object Like : UIState()
    object Animating : UIState()
}
