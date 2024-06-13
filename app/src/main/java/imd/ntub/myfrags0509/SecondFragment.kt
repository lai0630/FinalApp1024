package imd.ntub.myfrags0509

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.io.InputStream

class SecondFragment : Fragment() {

    private lateinit var contactDatabaseManager: ContactDatabaseManager
    private lateinit var editTextName: EditText
    private lateinit var editTextTel: EditText
    private lateinit var imageViewContact: ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactDatabaseManager = ContactDatabaseManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        editTextName = view.findViewById(R.id.editTextName)
        editTextTel = view.findViewById(R.id.editTextTel)
        imageViewContact = view.findViewById(R.id.imageViewContact)
        val buttonSave: Button = view.findViewById(R.id.buttonSave)

        imageViewContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
        }

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val tel = editTextTel.text.toString()

            if (name.isNotEmpty() && tel.isNotEmpty()) {
                val contact = Contact(0, name, tel, imageUri?.toString())
                contactDatabaseManager.addContact(contact)
                Toast.makeText(requireContext(), "Contact Saved", Toast.LENGTH_SHORT).show()
                // 清空輸入
                editTextName.text.clear()
                editTextTel.text.clear()
                imageViewContact.setImageResource(R.drawable.icon)
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            val imageStream: InputStream? = imageUri?.let {
                requireContext().contentResolver.openInputStream(it)
            }
            val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
            imageViewContact.setImageBitmap(selectedImage)
        }
    }

    companion object {
        private const val PICK_IMAGE = 1

        @JvmStatic
        fun newInstance() = SecondFragment()
    }
}
