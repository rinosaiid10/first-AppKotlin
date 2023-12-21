package com.rino.firstapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit

class HomeActivity : AppCompatActivity() {

  //lateinit c'est a dire initialiser apres une variable
    lateinit var listPosts : ListView
    var postsArray = ArrayList<Post>()
    lateinit var adapter: PostsAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val email= intent.getStringExtra("email")

         listPosts = findViewById<ListView>(R.id.listPosts)
         postsArray = arrayListOf(

             Post("post1","Une description qui vas etre affiché ici et qui ne veux rien dire",R.drawable.img1),
             Post("post2","Une description qui vas etre affiché ici et qui ne veux rien dire",R.drawable.img2),
             Post("post3","Une description qui vas etre affiché ici et qui ne veux rien dire",R.drawable.img3),
             Post("post4","Une description qui vas etre affiché ici et qui ne veux rien dire",R.drawable.img4),
             Post("post5","Une description qui vas etre affiché ici et qui ne veux rien dire",R.drawable.img5)
         )
         adapter = PostsAdapter(this, R.layout.item_post, postsArray)
         listPosts.adapter  = adapter

        listPosts.setOnItemClickListener { adapterView, view, position, id ->
            val clickedPost = postsArray[position]
            Intent(this,PostDetailsActivity::class.java).also {
                it.putExtra("titre",clickedPost.titre)
                startActivity(it)

            }

        }
        registerForContextMenu(listPosts)

//        val tvHello = findViewById<TextView>(R.id.tvHello)

     /*   // 1: recuperezr l'email envoyé par activityMain
        val email= intent.getStringExtra("email")
        // 2: afficher email dans le
        tvHello.text = "Bienvenue :$email"*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //convertir xml en menu view
        menuInflater.inflate(R.menu.home_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId ){
            R.id.itemAdd ->{
//                Toast.makeText(this,"add post",Toast.LENGTH_LONG).show()
            }
            R.id.itemConfig ->{


            }
            R.id.itemLogout ->{

                //finish()
                // afficher un dialog de confirmation

                showLogoutConfirmDialog()

            }



        }
        return super.onOptionsItemSelected(item)
    }

/*    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.list_context_menu,menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
    val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val postiion : Int = info.position
        when(item.itemId){
            R.id.itemShow ->{
              Intent(this,PostDetailsActivity::class.java).also {
                  it.putExtra("titre",postsArray[postiion].titre)
                  startActivity(it)
              }
            }
            R.id.itemDelete ->{
               postsArray.removeAt(postiion)
                adapter.notifyDataSetChanged()

            }
        }
        return super.onContextItemSelected(item)
    }*/


  fun showLogoutConfirmDialog() {
 val builder = AlertDialog.Builder(this)
      builder.setTitle("Confirmation !")
      builder.setMessage("Etes-vous sure de vouloir quitter l'appli ?  !")
      builder.setPositiveButton("Oui") { dialogInterface, id ->
          //recuperer editor

          val editor = this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit()
          editor.remove("is_authentificated")
          editor.apply()
          finish()
      }
      builder.setNegativeButton("Nom") { dialogInterface, id ->
          dialogInterface.dismiss()

      }
      builder.setPositiveButton("Annuler") { dialogInterface, id ->
          dialogInterface.dismiss()

      }

 val alertDialog: AlertDialog = builder.create()
      alertDialog.show()

    }


}