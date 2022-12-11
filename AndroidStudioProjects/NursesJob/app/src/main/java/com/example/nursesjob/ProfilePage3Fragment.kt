package com.example.nursesjob

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nursesjob.databinding.FragmentProfilePage3Binding


class ProfilePage3Fragment : Fragment(R.layout.fragment_profile_page3) {
    private var _binding: FragmentProfilePage3Binding? = null
    private val binding get() = _binding!!
    private lateinit var arrayList: ArrayList<Experience>
    lateinit var experienceList: ArrayList<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilePage3Binding.bind(view)
        lateinit var radioButton: RadioButton
        val args = this.arguments
        val dataOne = args?.getSerializable("dataOne")
        val dataTwo = args?.getSerializable("dataTwo")

        binding.apply {

            var workStatus: String = ""

            val linearLayoutManager = LinearLayoutManager(requireContext())
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.setHasFixedSize(true)

            recyclerView.visibility = View.GONE
            experienceButton.setOnClickListener {
                val view =
                    View.inflate(requireContext(), R.layout.experience_details_alert, null)
                val builder = AlertDialog.Builder(requireContext())
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                dialog.setCancelable(false)
                val dateJoined = view.findViewById<TextView>(R.id.joined_date)
                dateJoined.setOnClickListener {

                    val datePickerFragment = DatePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager
                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY",
                        viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if (resultKey == "REQUEST_KEY") {
                            val date = bundle.getString("SELECTED_DATE")
                            dateJoined.text = date
                        }
                    }
                    datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
                }

                val submitButton = view.findViewById<TextView>(R.id.sumit_button)
                submitButton.setOnClickListener {
                    dialog.dismiss()
                    val workArea = view.findViewById<TextView>(R.id.work_area).text.toString()
                    val organisationType =
                        view.findViewById<TextView>(R.id.organisation_type).text.toString()
                    val organisationName =
                        view.findViewById<TextView>(R.id.organisation_name).text.toString()
                    val position = view.findViewById<TextView>(R.id.position).text.toString()
                    val radioGroupId = view.findViewById<RadioGroup>(R.id.working_group)
                    val selectedOption: Int = radioGroupId!!.checkedRadioButtonId
                    if (selectedOption != -1) {
                        radioButton = view.findViewById(selectedOption)
                        workStatus = radioButton.text.toString()
                    }
                    experienceList = arrayListOf<String>(
                        workArea,
                        organisationType,
                        organisationName,
                        position,
                        dateJoined.text.toString(),
                        workStatus
                    )
                    recyclerView.adapter =
                        ExperienceAdapter(experienceList, requireContext())

                    recyclerView.visibility = View.VISIBLE
                }

            }
            submitButton.setOnClickListener {
                var page1 = dataOne.toString()
                println(page1)
                var page2 = dataTwo.toString()
                println(page2)
                var page3 = experienceList.toString()
                println(page3)
            }
            previousButton.setOnClickListener {
                val setupFragment = ProfilePage2()
                val supportFragmentManager = requireActivity().supportFragmentManager
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, setupFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

    }
}