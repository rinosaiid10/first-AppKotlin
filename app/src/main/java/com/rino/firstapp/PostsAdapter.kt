package com.rino.firstapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView

class PostsAdapter(
    var mContext: Context,
    var resource: Int,
    var values: ArrayList<Post>

): ArrayAdapter<Post>(mContext,resource,values){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // on recuperer la valeur avec postion
        val post = values[position]

        //convertir le xml en view 'convertView' avec LayoutInflater.from()

        //recuperer les values
        val itemView = LayoutInflater.from(mContext).inflate(resource,parent,false)

        //on affiche les values

        val tvTitre = itemView.findViewById<TextView>(R.id.tvTitre)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val imagesPost = itemView.findViewById<ImageView>(R.id.imagePost)
        val imageShowPopup = itemView.findViewById<ImageView>(R.id.imageShowPopup)


        tvTitre.text = post.titre
        tvDescription.text = post.descritpion
        imagesPost.setImageResource(post.image)
        imageShowPopup.setOnClickListener{
            val popupMenu = PopupMenu(mContext,imageShowPopup)
            //convertion

            popupMenu.menuInflater.inflate(R.menu.list_context_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {item ->
                when(item.itemId){
                    R.id.itemShow -> {
                        Intent(mContext,PostDetailsActivity:: class.java).also {
                             it.putExtra("titre",post.titre)
                            mContext.startActivity(it)
                        }

                    }
                    R.id.itemDelete -> {
                        values.removeAt(position)
                        notifyDataSetChanged()

                    }

                }
                true
            }

             popupMenu.show()

        }


        return itemView
    }


}