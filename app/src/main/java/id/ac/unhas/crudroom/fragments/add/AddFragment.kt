package id.ac.unhas.crudroom.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.unhas.crudroom.R
import id.ac.unhas.crudroom.data.User
import id.ac.unhas.crudroom.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_button.setOnClickListener{
            insertDatatoDatabase()
        }

        return view
    }

    private fun insertDatatoDatabase() {
        val firstName =  add_firstname.text.toString()
        val lastname = add_lastname.text.toString()
        val age = add_age.text
        val email = add_email.text.toString()

        if(inputCheck(firstName, lastname, age)){
            //Create User Object
            val user = User(0, firstName, lastname, Integer.parseInt(age.toString()), email)
            //Add Data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added",Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Failed to add, please check again",Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}