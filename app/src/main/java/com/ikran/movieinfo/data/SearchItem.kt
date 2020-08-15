package com.ikran.movieinfo.data

import androidx.recyclerview.widget.DiffUtil
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class SearchItem(
    @JsonProperty("Title")  val title:String?,
    @JsonProperty("Year")   val year:String?,
    @JsonProperty("imdbID") val id :String?,
    @JsonProperty("Type")   val type :String?,
    @JsonProperty("Poster") val posterUrl:String?
) {
    companion object{
        val DIFF = object : DiffUtil.ItemCallback<SearchItem>(){
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
                oldItem.id.equals(newItem.id)


            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
                oldItem.id.equals(newItem.id)

        }
    }

    override fun equals(other: Any?): Boolean {
        return other === this || (other is SearchItem && other.id == id)
    }

    override fun hashCode(): Int = Objects.hashCode(this)

}

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchResult(
    @JsonProperty("Search") val searchItems : List<SearchItem?>?,
    @JsonProperty("totalResults") val  totalResults:Int?,
    @JsonProperty("Response") val Response : Boolean,
    @JsonProperty("Error ") val Error : String?
)
