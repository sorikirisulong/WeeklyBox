package com.hsh.weeklybox.ui.common.helper

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class FragmentHelper(
    context: Context,
    private val fragmentResId: Int,
    fragmentManager: FragmentManager? = null
) {

    private val fragmentManager: FragmentManager = fragmentManager
        ?: (context as FragmentActivity).supportFragmentManager

    @JvmOverloads
    fun replaceFragment(
        fragment: Fragment,
        tag: String? = null,
        pushBackStack: Boolean = true,
        enterAniResId: Int = 0,
        exitAniResId: Int = 0,
        popEnterAniResId: Int = 0,
        popExitAniResId: Int = 0
    ) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(enterAniResId, exitAniResId, popEnterAniResId, popExitAniResId)
            .replace(fragmentResId, fragment, tag ?: fragment.javaClass.simpleName)
            .apply {
                if (pushBackStack && tag != null) addToBackStack(tag)
            }
            .commit()
    }

    @JvmOverloads
    fun addFragment(
        fragment: Fragment,
        tag: String? = null,
        checkUnique: Boolean = true,
        pushBackStack: Boolean = true,
        enterAniResId: Int = 0,
        exitAniResId: Int = 0,
        popEnterAniResId: Int = 0,
        popExitAniResId: Int = 0
    ) {
        // only add one fragment for same tag
        if (checkUnique && findFragmentByTag(tag) != null) {
            return
        }
        fragmentManager.beginTransaction()
            .setCustomAnimations(enterAniResId, exitAniResId, popEnterAniResId, popExitAniResId)
            .add(fragmentResId, fragment, tag ?: fragment.javaClass.simpleName)
            .apply {
                if (pushBackStack && tag != null) addToBackStack(tag)
            }
            .commit()
    }

    fun removeFragment() {
        fragmentManager.popBackStack()
    }

    fun hasRemovableFragment(): Boolean {
        return fragmentManager.backStackEntryCount > 1
    }

    val prevFragment: Fragment?
        get() {
            val fragmentList = fragmentManager.fragments
            return if (fragmentList.size > 1) {
                fragmentList[fragmentList.size - 2]
            } else {
                null
            }
        }

    val lastFragment: Fragment?
        get() {
            val fragmentList = fragmentManager.fragments
            return if (fragmentList.isNotEmpty()) {
                fragmentList[fragmentList.size - 1]
            } else null
        }

    val fragments: List<Fragment>
        get() = fragmentManager.fragments

    fun findFragmentById(fragmentResId: Int): Fragment? {
        return fragmentManager.findFragmentById(fragmentResId)
    }

    fun findFragmentByTag(tag: String?): Fragment? {
        return fragmentManager.findFragmentByTag(tag)
    }

    fun removeFragment(fragmentResId: Int) {
        val fragment = fragmentManager.findFragmentById(fragmentResId)
        fragment?.let {
            fragmentManager.beginTransaction().remove(it).commit()
        }
    }

    fun removeFragment(fragmentTag: String?) {
        if (fragmentTag == null) return

        val fragment = fragmentManager.findFragmentByTag(fragmentTag)
        if (fragment != null) {
            fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
        }
    }

    fun popFragment(tag: String?) {
        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showFragment(fragment: Fragment?) {
        fragmentManager.beginTransaction().apply {
            fragments.forEach {
                hide(it)
            }
            fragment?.let {
                show(it)
            }
            commit()
        }
    }

    fun hideFragment(fragment: Fragment?) {
        fragment?.let {
            fragmentManager.beginTransaction().hide(it).commit()
        }
    }

    fun hideFragments() {
        fragmentManager.beginTransaction()
            .also { transition ->
                fragments.forEach { transition.hide(it) }
            }
            .commit()
    }

    companion object {
        fun getTopFragmentByTagFromBackStack(fragmentManager: FragmentManager): Fragment? {
            val count = fragmentManager.backStackEntryCount

            if (count <= 0) {
                return null
            }

            val backStackEntry = fragmentManager.getBackStackEntryAt(count - 1)

            return if (backStackEntry.name.isNullOrEmpty().not()) {
                null
            } else {
                fragmentManager.findFragmentByTag(backStackEntry.name)
            }
        }
    }

}