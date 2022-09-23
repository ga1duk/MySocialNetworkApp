package com.company.mysocialnetworkapp.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.auth.AppAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInDialogFragment : DialogFragment() {

    @Inject
    lateinit var appAuth: AppAuth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.dialog_title_sign_in))
                .setMessage(getString(R.string.sign_in_message_are_you_sure))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.sign_in_dialog_positive_button_text)) { _, _ ->
                    appAuth.removeAuth()
                    findNavController().navigate(R.id.action_postsFragment_to_signInFragment)
                }
                .setNegativeButton(getString(R.string.sign_in_dialog_negative_button_text)) { _, _ ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}