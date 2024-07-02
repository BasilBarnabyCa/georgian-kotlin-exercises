package ca.georgiancollege.ice07

import androidx.recyclerview.widget.DiffUtil

// A utility class that helps the recycler adapter determine how to efficiently update the list  when the data changes
class TVShowComparator: DiffUtil.ItemCallback<TVShow>() {
    // Checks if the items are the same - if two items represent the same entity by comparing the IDs
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem.id == newItem.id
    }

    // Checks if the contents are the same - if two items are teh same by comparing the properties
    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
        return oldItem == newItem
    }
}