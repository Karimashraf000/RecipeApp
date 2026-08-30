package com.example.finalproject.ui.recipe.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.local.AppDatabase
import com.example.finalproject.data.local.FavoriteRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private val favoriteList = mutableListOf<FavoriteRecipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = FavoriteAdapter(
            favorites = favoriteList,
            onItemClick = { favorite ->
                val bundle = bundleOf("mealId" to favorite.mealId)
                findNavController().navigate(R.id.action_favorite_to_detail, bundle)
            },
            onRemoveClick = { favorite ->
                removeFavorite(favorite)
            }
        )
        recyclerView.adapter = adapter

        loadFavorites()

    }
    override fun onResume() {
        super.onResume()
        if (::adapter.isInitialized) {
            loadFavorites()
        }
    }
    private fun loadFavorites() {
        val dao = AppDatabase.getDatabase(requireContext()).favoriteDao()
        lifecycleScope.launch(Dispatchers.IO) {
            val favorites = dao.getAll()
            withContext(Dispatchers.Main) {
                favoriteList.clear()
                favoriteList.addAll(favorites)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun removeFavorite(favorite: FavoriteRecipe) {
        val dao = AppDatabase.getDatabase(requireContext()).favoriteDao()
        lifecycleScope.launch(Dispatchers.IO) {
            dao.delete(favorite)
            withContext(Dispatchers.Main) {
                adapter.removeItem(favorite)
            }
        }
    }

}