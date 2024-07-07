package ca.georgiancollege.ice07

import androidx.recyclerview.widget.ListAdapter

class TVShowListAdapter(private val onItemClicked: (TVShow) -> Unit) :
    ListAdapter<TVShow, TVShowViewHolder>(TVShowComparator))
{
}