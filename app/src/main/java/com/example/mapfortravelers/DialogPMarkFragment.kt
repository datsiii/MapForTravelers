package com.example.mapfortravelers

import android.app.Dialog
import android.graphics.Bitmap
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.yandex.runtime.Runtime.getApplicationContext
import java.io.ByteArrayOutputStream


class DialogPMarkFragment : DialogFragment() {
    companion object {
        private const val TAG = "DialogPMarkFragment"
        private const val EXTRA_TITLE = "Хотите создать метку?"
        private const val EXTRA_HINT = "Mетка"
        private const val EXTRA_MULTILINE = "multiline"
        private const val EXTRA_TEXT = "text"
        fun newInstance(
            title: String? = null,
            hint: String? = null,
            text: String? = null,
            isMultiline: Boolean = false
        ): DialogPMarkFragment {
            val dialog = DialogPMarkFragment()
            val args = Bundle().apply {
                putString(EXTRA_TITLE, title)
                putString(EXTRA_HINT, hint)
                putString(EXTRA_TEXT, text)
                putBoolean(EXTRA_MULTILINE, isMultiline)
            }
            dialog.arguments = args
            return dialog
        }
    }

    lateinit var editText: TextInputEditText
    lateinit var btnUpload: MaterialButton
    var bitmap: Bitmap? = null
    var onOk: (() -> Unit)? = null
    var onCancel: (() -> Unit)? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString(EXTRA_TITLE)
        val hint = arguments?.getString(EXTRA_HINT)
        val text: String? = arguments?.getString(EXTRA_TEXT)
        val isMiltiline = arguments?.getBoolean(EXTRA_MULTILINE) ?: false
        val view = requireActivity().layoutInflater.inflate(R.layout.fragment_dialog, null)
        editText = view.findViewById(R.id.editText)
        btnUpload = view.findViewById(R.id.btn_upld)
        editText.hint = hint
        btnUpload.setOnClickListener {
            val byteArrayOutputStream: ByteArrayOutputStream
            byteArrayOutputStream = ByteArrayOutputStream()
            if (bitmap != null) {
                val w: Int = bitmap!!.getWidth()
                val h: Int = bitmap!!.getHeight()
                val halfw = w / 2
                val halfh = h / 2
                bitmap = Bitmap.createScaledBitmap(bitmap!!, halfw, halfh, false)
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                val bytes = byteArrayOutputStream.toByteArray()
                val base64Image = Base64.encodeToString(bytes, Base64.DEFAULT)

                val queue: RequestQueue = Volley.newRequestQueue(getApplicationContext())
                val url: String = ""
            }
        }
        if (isMiltiline) {
            editText.minLines = 3
            editText.inputType =
                InputType.TYPE_TEXT_FLAG_MULTI_LINE or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        }
        if (text != null) {
            // editText.setText(text)
            // editText.setSelection(text.length)
            editText.append(text)
        }
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setView(view)
            .setPositiveButton("Создать") { _, _ ->
                onOk?.invoke()
            }
            .setNegativeButton("Отмена") { _, _ ->
                onCancel?.invoke()
            }
        val dialog = builder.create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        return dialog
    }

}