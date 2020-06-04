package rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments.BeleskeFragment
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments.GraphFragment
import rs.raf.projekat2.valerija_nagl_RN682018.presentation.view.fragments.RasporedFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> RasporedFragment()
            FRAGMENT_2 -> BeleskeFragment()
            else -> GraphFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "RASPORED"
            FRAGMENT_2 -> "BELESKE"
            else -> "GRAFIK"
        }
    }

}