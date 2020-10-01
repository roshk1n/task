package com.home.test.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import java.lang.ref.WeakReference

object GuiHelper {

    fun setError(view: TextView?, errorText: CharSequence, removeOnType: Boolean) {
        if (view != null) {
            val parent = searchParent(view, TextInputLayout::class.java)
            if (parent != null) {
                parent.isErrorEnabled = true
                parent.error = errorText
            } else {
                view.error = errorText
            }
            if (removeOnType) {
                view.addTextChangedListener(ErrorRemovingTextWatcher(view))
            }
        }
    }

    /** [android.text.TextWatcher] which removes error and self from the view on any change.  */
    private class ErrorRemovingTextWatcher internal constructor(
        /** View to remove error from.  */
        private val view: TextView
    ) : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            view.post(object : ViewTask<TextView>(view) {
                override fun execute(v: TextView) {
                    val parent = searchParent(view, TextInputLayout::class.java)
                    if (parent != null) {
                        parent.error = null
                        parent.isErrorEnabled = false
                    } else {
                        v.error = null
                    }
                    v.removeTextChangedListener(this@ErrorRemovingTextWatcher)
                }
            })
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }

    /**
     * Searches view tree for parent of specified type.
     *
     * @param v      view to look parent
     * @param target parent type
     * @param <T>    parent type
     * @return parent view of `null` if parent of specified type doesn't exist at view tree
    </T> */
    private fun <T : View> searchParent(v: View, target: Class<T>): T? {
        var p: ViewParent? = v.parent
        while (p != null) {
            if (target.isAssignableFrom(p.javaClass)) {

                return p as T?
            }
            p = p.parent
        }
        return null
    }


    /**
     * Task to manipulate view.
     *
     * @param <V> view type
    </V> */
    private abstract class ViewTask<V : View> constructor(view: V) : Runnable {

        /**
         * View reference.
         */
        private val reference: WeakReference<V> = WeakReference(view)

        override fun run() {
            val view = reference.get()
            if (view != null) {
                execute(view)
            }
        }

        /**
         * Executes operation on view.
         *
         * @param view view to execute operation on
         */
        protected abstract fun execute(view: V)
    }
}