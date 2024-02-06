package com.example.quotesapp.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.quotesapp.domain.model.Quote

fun sendTextToWhatsapp(quote:Quote,context: Context){
    val intent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,"${quote.content} -${quote.author}")
        `package` = "com.whatsapp"
    }
    startActivity(context,intent, Bundle())
}