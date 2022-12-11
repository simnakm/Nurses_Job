package com.example.nursesjob

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.nursesjob.databinding.FragmentProfilePage1Binding


class PageOneFragment : Fragment(R.layout.fragment_profile_page1) {
    private var _binding: FragmentProfilePage1Binding? = null
    private val binding get() = _binding!!
    lateinit var radioButton: RadioButton
    lateinit var inputList:ArrayList<String>
    lateinit var districtAdapter: ArrayAdapter<CharSequence?>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfilePage1Binding.bind(view)

        var userName = ""
        var userAge = ""
        var userDob = ""
        var userEmail = ""
        var userMobile = ""
        var userState = ""
        var userDistrict = ""

        binding.apply {
            val spinnerState = view.findViewById<Spinner>(R.id.state)

            val spinnerDistrict = view?.findViewById<Spinner>(R.id.district)

            spinnerState?.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.states,
                android.R.layout.simple_spinner_item
            )

            spinnerState?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //..
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedState = spinnerState.selectedItem.toString()
                    userState=selectedState
                    val parentId = parent?.id
                    if (parentId == R.id.state) {
                        if (selectedState == "Select State") {
                            districtAdapter = ArrayAdapter.createFromResource(
                                requireContext(),
                                R.array.select_district, android.R.layout.simple_spinner_item
                            )
                            spinnerDistrict?.adapter = districtAdapter

                        } else if (selectedState == "Andhra Pradesh") {
                            districtAdapter = ArrayAdapter.createFromResource(
                                parent.context,
                                R.array.array_andhra_pradesh_districts,
                                android.R.layout.simple_spinner_item
                            )
                        } else {
                            println("else part")
                        }

                    }
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerDistrict?.adapter = districtAdapter
                    spinnerDistrict?.onItemSelectedListener =
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
                                val selectedDistrict = spinnerDistrict?.selectedItem.toString()
                                userDistrict=selectedDistrict
                            }


                        }
                }
            }

            dob.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")
                        dob.text = date
                        userDob = date.toString()
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
            }

            nextButton.setOnClickListener {
            userName = name.text.toString()
            userAge = age.text.toString()

            val radioGroupId = view.findViewById<RadioGroup>(R.id.gender_group)
            val selectedOption: Int = radioGroupId!!.checkedRadioButtonId
            if (selectedOption != -1) {
                radioButton = view.findViewById(selectedOption)
                var userGender = radioButton.text.toString()
            }

            userEmail = email.text.toString()
            userMobile = mobileNumber.text.toString()




            inputList = ArrayList<String>()
            inputList.add(userName)
            inputList.add(userAge)
            inputList.add(userDob)
            inputList.add(userEmail)
            inputList.add(userMobile)
            inputList.add(userDob)
            inputList.add(userState)
            inputList.add(userDistrict)
       //  inputList= arrayListOf<String> (userName,userAge,userDob,userEmail,userMobile,userDob,userState,userDistrict)




                Toast.makeText(requireContext(),userAge,Toast.LENGTH_LONG).show()
                val bundle=Bundle()
                bundle.putSerializable("dataOne",inputList.toString())
                val setupFragment = ProfilePage2()
                setupFragment.arguments=bundle
                val supportFragmentManager = requireActivity().supportFragmentManager
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, setupFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }


        }

    }

}

