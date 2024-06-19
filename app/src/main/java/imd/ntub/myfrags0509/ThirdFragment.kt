package imd.ntub.myfrags0509

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

private const val ARG_PARAM1 = "param1"

class ThirdFragment : Fragment() {
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString("message")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val txtMsg = view.findViewById<TextView>(R.id.txtMessage)
        txtMsg.text = message

        val imgBack = view.findViewById<ImageView>(R.id.imgBack)
        // 可以根据需要设置 imgBack 的其他属性，例如透明度
        imgBack.alpha = 0.8f  // 将图片透明度设置为50%
    }

    companion object {
        @JvmStatic
        fun newInstance(message: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString("message", message)
                }
            }
    }
}
