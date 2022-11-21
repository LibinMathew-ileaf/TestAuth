package com.example.secureauthentication.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.IOException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import javax.crypto.*

 class Encryptor {
    lateinit var encryption: ByteArray
        private set
    lateinit var iv: ByteArray
        private set

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchProviderException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        IOException::class,
        InvalidAlgorithmParameterException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class
    )
    fun encryptText(alias: String, textToEncrypt: String): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))
        iv = cipher.iv
        return cipher.doFinal(textToEncrypt.encodeToByteArray()).also { encryption = it }
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchProviderException::class,
        InvalidAlgorithmParameterException::class
    )
    private fun getSecretKey(alias: String): SecretKey {
        val keyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
        )
        return keyGenerator.generateKey()
    }

    companion object {
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}