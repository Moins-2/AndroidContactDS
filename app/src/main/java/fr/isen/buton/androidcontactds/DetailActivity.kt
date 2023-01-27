package fr.isen.buton.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.buton.androidcontactds.databinding.ActivityDetailBinding
import fr.isen.buton.androidcontactds.dataclass.Location
import fr.isen.buton.androidcontactds.dataclass.Results
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val contactString = intent.getStringExtra("contact")
        val gson = Gson()
        val contact: Results = gson.fromJson(contactString, Results::class.java)

        binding.name.text = contact.name.first+" "+contact.name.last.uppercase()
        binding.address.text = getAdd(contact.location)
        binding.email.text = contact.email
        binding.phone.text = contact.phone.split("-").joinToString(" ")

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val inputDate = contact.dob.date
        val inputDateObj = inputFormat.parse(inputDate)
        binding.birth.text  = outputFormat.format(inputDateObj)


        if (contact.picture.large != "") {
            val radius = 30
            val transformation = RoundedCornersTransformation(radius, 0)
            Picasso.get().load(contact.picture.large).transform(transformation).into(binding.imageView)
        }

    }

    private fun getAdd(location: Location): String{
        return location.street.number.toString()+ " "+ location.street.name+ ", "+ location.city
    }
}