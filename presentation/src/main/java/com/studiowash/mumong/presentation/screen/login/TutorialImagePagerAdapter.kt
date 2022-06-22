package com.studiowash.mumong.presentation.screen.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.studiowash.mumong.presentation.databinding.ItemTutorialImageBinding

class TutorialImagePagerAdapter(private val imageResources: List<Int>) : RecyclerView.Adapter<TutorialImagePagerAdapter.TutorialImageViewHolder>() {
    class TutorialImageViewHolder(val binding: ItemTutorialImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialImageViewHolder {
        val binding = ItemTutorialImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TutorialImageViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TutorialImageViewHolder, position: Int) {
        holder.binding.ivTutorialImage.load(imageResources[position])
    }

    override fun getItemCount(): Int = imageResources.size
}
