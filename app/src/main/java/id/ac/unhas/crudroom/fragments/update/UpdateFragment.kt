package id.ac.unhas.crudroom.fragments.update

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
import androidx.navigation.fragment.navArgs
import id.ac.unhas.crudroom.R
import id.ac.unhas.crudroom.data.User
import id.ac.unhas.crudroom.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by  navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_firstname.setText(args.currentUser.firstName)
        view.update_lastname.setText(args.currentUser.lastName)
        view.update_age.setText(args.currentUser.age.toString())
        view.update_email.setText(args.currentUser.email)

        view.update_button.setOnClickListener{
            updateItem()
        }
        return view
    }

    private fun updateItem(){
        val firstName = update_firstname.text.toString()
        val lastName = update_lastname.text.toString()
        val age = Integer.parseInt(update_age.text.toString())
        val email = update_email.text.toString()

        if(inputCheck(firstName, lastName, update_age.text)){
            //Create User Object
            val user = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age.toString()), email)
            //Update Data to database
            mUserViewModel.updateUser(user)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_SHORT).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Failed to update, please check again", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}