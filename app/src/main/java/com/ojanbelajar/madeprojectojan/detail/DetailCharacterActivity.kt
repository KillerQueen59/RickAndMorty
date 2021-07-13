package com.ojanbelajar.madeprojectojan.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ojanbelajar.madeprojectojan.R
import com.ojanbelajar.core.domain.model.Character
import com.ojanbelajar.madeprojectojan.databinding.ActivityDetailHomeBinding
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel


class DetailCharacterActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailHomeBinding
    private val model: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val data = intent.getParcelableExtra<Character>(DATA)
        if (data != null){
            setData(data)
        }
    }


    fun setData(data: Character){
        binding.episodeName.text = "${data.episode} episodes"
        binding.genderName.text = data.gender
        binding.nameCharacter.text = data.name
        binding.locationName.text = data.location.name
        binding.originName.text = data.origin.name
        binding.textAlive.text = data.status
        binding.speciesName.text = data.species
        Glide.with(this)
            .load(data.image)
            .into(binding.imageCharacter)
        var status = data.isFavourite
        setStatusFavorite(status,true)
        binding.favButton.setOnClickListener {
            status = !status
            model.setFavouriteCharacter(data,status)
            setStatusFavorite(status,false)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean,isFirst: Boolean) {
        if (statusFavorite) {
            if (!isFirst) toast("Added to Favourite")
            binding.favButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            if (!isFirst) toast("Removed to Favourite")
            binding.favButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }
}