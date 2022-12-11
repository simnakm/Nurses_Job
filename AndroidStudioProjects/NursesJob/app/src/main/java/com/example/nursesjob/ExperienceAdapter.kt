package com.example.nursesjob

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExperienceAdapter(
    private val experienceList: ArrayList<String>,
    requireContext: Context
) : RecyclerView.Adapter<ExperienceAdapter.ExperienceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.experience_list_item, parent, false)
        return ExperienceHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExperienceHolder, position: Int) {
        holder.workArea.text = experienceList[0]
        holder.organisationType.text = experienceList[1]
        holder.organisationName.text = experienceList[2]
        holder.position.text = experienceList[3]
        holder.doj.text =experienceList[4]
        holder.workStatus.text = experienceList[5]

    }

    override fun getItemCount(): Int {
        return experienceList.size
    }

    class ExperienceHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val workArea: TextView = itemView.findViewById(R.id.work_area)
        val organisationType: TextView = itemView.findViewById(R.id.organisation_type)
        val organisationName: TextView = itemView.findViewById(R.id.organisation_name)
        val position: TextView = itemView.findViewById(R.id.position)
        val doj: TextView = itemView.findViewById(R.id.joined_date)
        val workStatus: TextView = itemView.findViewById(R.id.currently_working)

    }
}