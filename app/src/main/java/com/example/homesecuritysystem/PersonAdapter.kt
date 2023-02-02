package com.example.homesecuritysystem

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.util.Base64
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homesecuritysystem.Api.MyData
import com.example.homesecuritysystem.Api.MyDataItem
import com.example.homesecuritysystem.databinding.ActivityCardBinding
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.binary.Hex
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.System.out
import java.net.URL

import java.security.spec.PSSParameterSpec.DEFAULT
import java.util.Base64.getDecoder
import kotlin.reflect.typeOf

class PersonAdapter(val context: Context, val personList: List<MyDataItem>): RecyclerView.Adapter<PersonAdapter.PersonHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class PersonHolder(item: View, listener: onItemClickListener) : RecyclerView.ViewHolder(item) {
        var date: TextView
        var id: TextView
//        var img_name: TextView
        var isFamiliar: SwitchCompat
        var time: TextView
        var img_data: ImageView

        init {
            date = item.findViewById(R.id.tvDate)
            id = item.findViewById(R.id.tvId)
            time = item.findViewById(R.id.tvTime)
            isFamiliar = item.findViewById(R.id.switch1)
            img_data = item.findViewById(R.id.imgPerson)

            isFamiliar.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        d("MyLog", "zero")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_card, parent, false)
        return PersonHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
//        for image
        val imgUrl = personList[position].img_data
        val imageBytes = Base64.decode(imgUrl, Base64.DEFAULT)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.img_data.setImageBitmap(image)

        holder.date.text = personList[position].date
        var person_id = personList[position].id.toString()
        holder.id.text = person_id
        holder.time.text = personList[position].time

        holder.isFamiliar.isChecked = personList[position].isFamiliar
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}

