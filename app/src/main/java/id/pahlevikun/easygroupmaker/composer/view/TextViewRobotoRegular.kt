package id.pahlevikun.easygroupmaker.composer.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView


class TextViewRobotoRegular : TextView {

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