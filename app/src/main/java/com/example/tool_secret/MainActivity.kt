package com.example.tool_secret

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.viewpager2.widget.ViewPager2
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity(), TextWatcher {
    private lateinit var viewPager: ViewPager2
    private lateinit var tooSecretViewPager: ToolSecretViewPager

    private var executedOnWindowFocusChanged = false

    private val cipherInfo: MutableMap<String, String> = mutableMapOf(
        "encryptText" to "",
        "decryptText" to "",
        "windowWidth" to "",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val windowMetrics = this.windowManager.currentWindowMetrics
        this.cipherInfo["windowWidth"] = windowMetrics.bounds.width().toString()

        this.tooSecretViewPager = ToolSecretViewPager(this, this.cipherInfo)
        this.viewPager = this.tooSecretViewPager.viewPager
        this.viewPager.isUserInputEnabled = false
    }

    private fun toEncrypt(keyText: String, decryptText: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val key = SecretKeySpec(keyText.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val encryptText = cipher.doFinal(decryptText.toByteArray())

        return Base64.encodeToString(cipher.iv, Base64.DEFAULT) + Base64.encodeToString(encryptText, Base64.DEFAULT)
    }

    private fun toDecrypt(keyText: String, encryptText: String): String {
        val iv = Base64.decode(encryptText.substring(0, 25), Base64.DEFAULT)
        val key = SecretKeySpec(keyText.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))

        return String(cipher.doFinal(Base64.decode(encryptText.substring(25), Base64.DEFAULT)), StandardCharsets.UTF_8)
    }

    fun buttonClickListener(view: View) {
        val keyText = this.getString(R.string.secret_key)

        when (view.id) {
            R.id.imageButtonToDecrypt -> {
                // 復号
                this.cipherInfo["encryptText"] = this.tooSecretViewPager.adapter!!.fragments[2]!!.getPositionText(2)
                this.cipherInfo["decryptText"] = this.toDecrypt(keyText, this.cipherInfo["encryptText"]!!)

                this.tooSecretViewPager.adapter!!.fragments[0]!!.updateText(0, this.cipherInfo["decryptText"]!!.
                replace(" ", "&#8200;").
                replace("?", "&#63;"))
                this.tooSecretViewPager.adapter!!.fragments[1]!!.updateText(1, this.cipherInfo["decryptText"]!!)
            }
            R.id.imageButtonToEncrypt -> {
                // 暗号
                this.cipherInfo["decryptText"] = this.tooSecretViewPager.adapter!!.fragments[1]!!.getPositionText(1)
                this.cipherInfo["encryptText"] = this.toEncrypt(keyText, this.cipherInfo["decryptText"]!!)

                this.tooSecretViewPager.adapter!!.fragments[0]!!.updateText(0, this.cipherInfo["decryptText"]!!.
                replace(" ", "&#8200;").
                replace("?", "&#63;"))
                this.tooSecretViewPager.adapter!!.fragments[2]!!.updateText(2, this.cipherInfo["encryptText"]!!)
            }

            R.id.view_pager_prev -> {
                if (this.viewPager.currentItem > 0) {
                    this.viewPager.currentItem -= 1
                }

                if (this.viewPager.currentItem == 0) {
                    findViewById<Button>(R.id.view_pager_prev).alpha = 0.25F
                } else {
                    findViewById<Button>(R.id.view_pager_prev).alpha = 1F
                }
                findViewById<Button>(R.id.view_pager_next).alpha = 1F

                if (this.viewPager.currentItem != 1) {
                    findViewById<Button>(R.id.sort_asc).alpha = 0.25F
                    findViewById<Button>(R.id.sort_desc).alpha = 0.25F
                } else {
                    findViewById<Button>(R.id.sort_asc).alpha = 1F
                    findViewById<Button>(R.id.sort_desc).alpha = 1F
                }
            }

            R.id.view_pager_next -> {
                if (this.viewPager.currentItem < this.viewPager.adapter!!.itemCount) {
                    this.viewPager.currentItem += 1
                }

                if (this.viewPager.currentItem == this.viewPager.adapter!!.itemCount - 1) {
                    findViewById<Button>(R.id.view_pager_next).alpha = 0.25F
                } else {
                    findViewById<Button>(R.id.view_pager_next).alpha = 1F
                }
                findViewById<Button>(R.id.view_pager_prev).alpha = 1F

                if (this.viewPager.currentItem != 1) {
                    findViewById<Button>(R.id.sort_asc).alpha = 0.25F
                    findViewById<Button>(R.id.sort_desc).alpha = 0.25F
                } else {
                    findViewById<Button>(R.id.sort_asc).alpha = 1F
                    findViewById<Button>(R.id.sort_desc).alpha = 1F
                }
            }
            R.id.sort_asc -> {
                if (this.viewPager.currentItem == 1) {
                    val sortedText =
                        this.tooSecretViewPager.adapter!!.fragments[1]!!.getPositionText(1)
                            .split("\r?\n".toRegex()).sorted().joinToString("\n")
                    this.tooSecretViewPager.adapter!!.fragments[1]!!.updateText(1, sortedText)
                }
            }
            R.id.sort_desc -> {
                if (this.viewPager.currentItem == 1) {
                    val sortedText =
                        this.tooSecretViewPager.adapter!!.fragments[1]!!.getPositionText(1)
                            .split("\r?\n".toRegex()).sorted().reversed().joinToString("\n")
                    this.tooSecretViewPager.adapter!!.fragments[1]!!.updateText(1, sortedText)
                }
            }
        }

        return
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (!executedOnWindowFocusChanged) {
            val windowHeight = findViewById<FrameLayout>(R.id.container).height
            val background = findViewById<ConstraintLayout>(R.id.background).apply {
                updateLayoutParams {
                    translationY = -windowHeight.toFloat()
                    height = windowHeight * 2
                }
            }

            findViewById<LinearLayout>(R.id.controller_container_bottom).translationY = windowHeight.toFloat()

            ObjectAnimator.ofPropertyValuesHolder(
                background,
                PropertyValuesHolder.ofFloat("translationY", 0F)
            ).apply {
                duration = 10000
                repeatMode = ObjectAnimator.RESTART
                repeatCount = -1
                interpolator = LinearInterpolator()
                start()
            }

            findViewById<EditText>(R.id.search).addTextChangedListener(this)

            this.executedOnWindowFocusChanged = true
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (p3 == 0) {
            this.tooSecretViewPager.adapter!!.fragments[0]!!.updateText(0,
                "<tt>" +
                        this.cipherInfo["decryptText"]!!.
                        replace(" ", "&#8200;").
                        replace("?", "&#63;") +
                        "</tt>"
            )
            return
        } else {
            val result = "<tt>" + this.cipherInfo["decryptText"]!!.
                replace(" ", "&#8200;").
                replace("?", "&#63;").
                replace(
                    p0.toString().replace(" ", "&#8200;").lowercase(),
                    "<span style='background-color:#000000;'>" +
                            p0.toString().
                            replace(" ", "&#8200;").
                            replace("?", "&#63;") +
                            "</span>",
                    ignoreCase = true
                ) + "</tt>"
            this.tooSecretViewPager.adapter!!.fragments[0]!!.updateText(0, result)

            val index = this.cipherInfo["decryptText"]!!.indexOf(p0.toString().lowercase(), 0, true)
            if (index >= 0) {
                findViewById<EditText>(R.id.editTextTextMultiLine).setSelection(index)
            }

            return
        }
    }

    override fun afterTextChanged(s: Editable?) {}
}