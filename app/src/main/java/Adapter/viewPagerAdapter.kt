package Adapter

import Fragments.ClientFragment
import Fragments.ServerFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class viewPagerAdapter(fragmentManager: FragmentManager):FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        var fragment:Fragment?=null

        when(position){
            0 -> fragment = ServerFragment()
            1 -> fragment = ClientFragment()
        }

        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var pageTitle:String?=null
        when(position){
            0 -> pageTitle = "Server"
            1 -> pageTitle = "Client"
        }
        return pageTitle
    }

}