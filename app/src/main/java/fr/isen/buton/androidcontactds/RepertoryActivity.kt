package fr.isen.buton.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.buton.androidcontactds.databinding.ActivityRepertoryBinding


class RepertoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepertoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("rep","test")

        binding = ActivityRepertoryBinding.inflate(layoutInflater)
        val view = binding.root


        val recy = binding.contacts

        val myFunc = fun(contact: Results) {
            val i = Intent(this@RepertoryActivity, DetailActivity::class.java)
            val gson = Gson()
            i.putExtra("contact",gson.toJson(contact))
            startActivity(i)

        }
        recy.layoutManager = LinearLayoutManager(this)

        recy.adapter = RepertoryAdapter(ResponseAPI(
                arrayOf(
                    Results(
                        gender = "male",
                        name = Name(
                            title = "Mr",
                            first = "John",
                            last = "Doe"
                        ),
                        location = Location(
                            street = Street(
                                number = 123,
                                name = "Main St"
                            ),
                            city = "New York",
                            state = "NY",
                            country = "United States",
                            postcode = 10001,
                            coordinates = Coordinates(
                                latitude = "40.758896",
                                longitude = "-73.985130"
                            ),
                            timezone = Timezone(
                                offset = "-04:00",
                                description = "Eastern Time (US & Canada)"
                            )
                        ),
                        email = "johndoe@gmail.com",
                        login = Login(
                            uuid = "12345678-abcd-1234-efgh-1234567890ab",
                            username = "johndoe",
                            password = "password",
                            salt = "salt",
                            md5 = "md5",
                            sha1 = "sha1",
                            sha256 = "sha256"
                        ),
                        dob = Dob(
                            date = "1980-01-01",
                            age = 43
                        ),
                        registered = Registered(
                            date = "2020-01-01",
                            age = 3
                        ),
                        phone = "555-555-5555",
                        cell = "555-555-5555",
                        id = Id(
                            name = "SSN",
                            value = "123-45-6789"
                        ),
                        picture = Picture(
                            large = "https://randomuser.me/api/portraits/men/1.jpg",
                            medium = "https://randomuser.me/api/portraits/med/men/1.jpg",
                            thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg"
                        ),
                        nat = "US"
                    ))), myFunc)


        setContentView(view)
    getRepertory()
    }



    private  fun getRepertory(){
        val queue = Volley.newRequestQueue(this)

        val url = "https://randomuser.me/api/?results=10&nat=fr"
        val jsonObject = JSONObject()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject,
            Response.Listener { response ->
                // add Gson and log it
                val gson = Gson()
            //    val repertory = gson.fromJson(response.toString(), Array<Results>::class.java)
                Log.w("rep", "get")
                val repertory = gson.fromJson(response.toString(), ResponseAPI::class.java)
               updateData(repertory)
                Log.w("rep", "endend")

            },
            Response.ErrorListener { error ->
                Log.e("api", "Error: ${error.message}")
            }
        )
        queue.add(jsonObjectRequest)


    }

    private fun updateData(data: ResponseAPI){
        Log.w("rep", "update")
        val myFunc = fun(contact: Results) {
            val i = Intent(this@RepertoryActivity, DetailActivity::class.java)
            val gson = Gson()
            i.putExtra("contact",gson.toJson(contact))
            startActivity(i)

        }
        Log.w("rep", "m b")
        Log.w("rep", data.toString())

        val adapter = RepertoryAdapter(data, myFunc)
        Log.w("rep", "m a")

        val recyView = binding.contacts
        recyView.adapter = adapter
        recyView.layoutManager = LinearLayoutManager(this)
        Log.w("rep", "end")

    }
}