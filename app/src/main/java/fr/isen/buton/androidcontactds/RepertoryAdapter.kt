package fr.isen.buton.androidcontactds

import android.graphics.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import fr.isen.buton.androidcontactds.dataclass.ResponseAPI
import fr.isen.buton.androidcontactds.dataclass.Results

class RepertoryAdapter (private var result: ResponseAPI, val onClickListener: (plats: Results) -> Unit) : RecyclerView.Adapter<RepertoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_contact, parent, false)


        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return result.results.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val contact = result.results[position]
        holder.name.text = contact.name.first +" "+ contact.name.last.uppercase()
        if (contact.picture.medium != "") {
            val radius = 100
            val transformation = RoundedCornersTransformation(radius, 0)
            Picasso.get().load(contact.picture.medium).transform(transformation).into(holder.image)
        }

        val loc = contact.location
        holder.address.text = loc.street.number.toString()+ " "+loc.street.name+ ", "+ loc.city
        holder.email.text = contact.email
        holder.name.setOnClickListener {
            onClickListener(contact)
        }
        holder.image.setOnClickListener {
            onClickListener(contact)
        }
        holder.address.setOnClickListener {
            onClickListener(contact)
        }
        holder.email.setOnClickListener {
            onClickListener(contact)
        }

    }

    class CategoryViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val image: ImageView = itemView.findViewById(R.id.image)
        val address: TextView = itemView.findViewById(R.id.address)
        val email: TextView = itemView.findViewById(R.id.email)
    }


}

