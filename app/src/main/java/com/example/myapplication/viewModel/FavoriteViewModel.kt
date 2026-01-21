package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoriteViewModel : ViewModel() {
    private val _favorites = MutableLiveData<List<ItemsModel>>()
    val favorites: LiveData<List<ItemsModel>> = _favorites

    fun loadFavorites(userId: String) {

        val ref = FirebaseDatabase.getInstance().getReference("Favorites").child(userId)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<ItemsModel>()
                for (child in snapshot.children) {
                    val item = child.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                _favorites.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Log the error if something goes wrong
                android.util.Log.e("FirebaseError", error.message)
            }
        })
    }
}