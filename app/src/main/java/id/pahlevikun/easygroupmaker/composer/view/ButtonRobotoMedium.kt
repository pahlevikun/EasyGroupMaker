package id.pahlevikun.easygroupmaker.composer.view

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatButton
import android.util.AttributeSet

/**
 * Created by praja on 17-May-17.
 */

class ButtonRobotoMedium : AppCompatButton {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            val tf = Typeface.createFromAsset(context.assets, "fonts/Roboto-Regular.ttf")
            typeface = tf
        }
    }
}
