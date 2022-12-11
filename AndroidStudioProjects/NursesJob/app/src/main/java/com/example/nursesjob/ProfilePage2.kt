package com.example.nursesjob

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.nursesjob.databinding.FragmentProfilePage2Binding
import java.util.*


class ProfilePage2 : Fragment(R.layout.fragment_profile_page2) {
    private var _binding: FragmentProfilePage2Binding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilePage2Binding.bind(view)

        var userCourse = ""
        var userYear = ""
        var userCouncil = ""
        var userSalary = ""
        var userLicence = ""

        val args = this.arguments
        val inputData = args?.getSerializable("dataOne")

        binding.apply {
            val courseSpinner = view.findViewById<Spinner>(R.id.course_text)
            val yearSpinner = view.findViewById<Spinner>(R.id.course_year)
            val councilSpinner = view.findViewById<Spinner>(R.id.nursing_council)
            val licenceSpinner = view.findViewById<Spinner>(R.id.international_licence)
            courseSpinner?.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.nursing_courses,
                android.R.layout.simple_spinner_item
            )

            val years = ArrayList<String>()
            val thisYear: Int = Calendar.getInstance().get(Calendar.YEAR)
            for (i in 1900..thisYear) {
                years.add(i.toString())
            }
            var adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)

            yearSpinner.adapter = adapter
            councilSpinner?.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.nursing_council,
                android.R.layout.simple_spinner_item
            )
            licenceSpinner?.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.international_licence,
                android.R.layout.simple_spinner_item
            )
            nextButton.setOnClickListener {

                courseSpinner?.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            //..
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            userCourse = courseSpinner.selectedItem.toString()

                        }
                    }

                yearSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //..
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        userYear = yearSpinner.selectedItem.toString()
                    }
                }

                councilSpinner?.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            //..
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            userCouncil = councilSpinner.selectedItem.toString()
                        }
                    }

                userSalary = salary.text.toString()
                licenceSpinner?.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val userLicence = licenceSpinner.selectedItem.toString()
                        }
                    }
                val inputList =
                    arrayListOf<String>(userCourse, userYear, userCouncil, userSalary, userLicence)

                val bundle = Bundle()
                bundle.putSerializable("dataOne", inputData.toString())
                bundle.putSerializable("dataTwo", inputList.toString())
                val setupFragment = ProfilePage3Fragment()
                setupFragment.arguments = bundle
                val supportFragmentManager = requireActivity().supportFragmentManager
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, setupFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            previousButton.setOnClickListener {
                val setupFragment = PageOneFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, setupFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }

    }
}