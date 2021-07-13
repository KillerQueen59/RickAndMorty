package com.ojanbelajar.madeprojectojan.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ojanbelajar.core.ui.CharacterAdapter
import com.ojanbelajar.madeprojectojan.detail.DetailCharacterActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ojanbelajar.core.data.source.Resource
import com.ojanbelajar.madeprojectojan.databinding.FragmentHomeBinding
import org.jetbrains.anko.support.v4.startActivity
import kotlin.math.abs
import org.koin.android.viewmodel.ext.android.viewModel



class HomeFragment : Fragment() {

    private val model: HomeViewModel by viewModel()
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CharacterAdapter()
        adapter.onItemClick = { data ->
            startActivity<DetailCharacterActivity>("data" to data)
        }
        model.character.observe(viewLifecycleOwner, { character ->
            if (character != null){
                when(character){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(character.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        })

        val composite = CompositePageTransformer()
        composite.addTransformer(MarginPageTransformer(0))
        composite.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        with(binding.pager){
            this.adapter = adapter
            this.clipToPadding = false
            this.clipChildren = false
            this.offscreenPageLimit = 3
            this.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            this.setPageTransformer(composite)
            this.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Handler(Looper.getMainLooper()).removeCallbacks(slider())
                    Handler(Looper.getMainLooper()).postDelayed({
                        slider()
                    },3000)
                }
            })
        }
    }
    fun slider() = Runnable {
        binding.pager.currentItem = binding.pager.currentItem + 1
    }

}
