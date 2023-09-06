package com.example.tool_secret.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.tool_secret.R

class MainFragment(private val cipherInfo: MutableMap<String, String>) : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val pageIndex = arguments?.getInt("pageNumber")

        val layout: View = when (pageIndex) {
            0 -> {
                // 表示
                inflater.inflate(R.layout.fragment_main, container, false)
            }
            1 -> {
                // textarea プレーン表示
                inflater.inflate(R.layout.fragment_main_textarea_decrypt, container, false)
            }
            else -> {
                // else if (pageIndex == 2)
                // textarea 暗号表示
                inflater.inflate(R.layout.fragment_main_textarea_encrypt, container, false)
            }
        }

        val that = this
        when (pageIndex) {
            1 -> {
                layout.findViewById<EditText>(R.id.editTextTextMultiLineDecrypt).apply {
                    setText(that.cipherInfo["decryptText"])
                }
            }
            2 -> {
                layout.findViewById<EditText>(R.id.editTextTextMultiLineEncrypt).apply {
                    setText(that.cipherInfo["encryptText"])
                }
            }
            else -> {
                layout.findViewById<EditText>(R.id.editTextTextMultiLine).apply {
                    setText(Html.fromHtml(that.cipherInfo["decryptText"], Html.FROM_HTML_MODE_LEGACY))
                }
            }
        }

        return layout
    }

    fun getPositionText(position: Int): String {
        return if (position == 1) {
            requireView().findViewById<EditText>(R.id.editTextTextMultiLineDecrypt).text.toString()
        } else {
            requireView().findViewById<EditText>(R.id.editTextTextMultiLineEncrypt).text.toString()
        }
    }

    fun updateText(position: Int, text: String): Boolean {
        when (position) {
            0 -> {
                requireView().findViewById<EditText>(R.id.editTextTextMultiLine).setText(Html.fromHtml(text.replace("[\\r?\\n]".toRegex(), "<br>"), Html.FROM_HTML_MODE_LEGACY))
            }
            1 -> {
                requireView().findViewById<EditText>(R.id.editTextTextMultiLineDecrypt).setText(text)
            }
            2 -> {
                view?.findViewById<EditText>(R.id.editTextTextMultiLineEncrypt)?.setText(text)
            }
        }
        return true
    }
}