package id.pahlevikun.easygroupmaker.presenter.`interface`

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import java.io.File

interface ScreenShotInterface {
    fun takeScreenshot(view: View): Bitmap

    fun saveScreenshot(bitmap: Bitmap): Boolean

    fun shareScreenshot(context: Context, imagePath: File)
}