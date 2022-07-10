package com.studiowash.mumong.presentation.screen.login

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.studiowash.mumong.presentation.R

enum class TutorialContents(@DrawableRes val imageRes: Int, @StringRes val titleRes: Int, @StringRes val contentRes: Int) {
    Page1(R.drawable.img_tutorial_1, R.string.login_tutorial_1_title, R.string.login_tutorial_1_content),
    Page2(R.drawable.img_tutorial_2, R.string.login_tutorial_2_title, R.string.login_tutorial_2_content),
    Page3(R.drawable.img_tutorial_3, R.string.login_tutorial_3_title, R.string.login_tutorial_3_content)
}