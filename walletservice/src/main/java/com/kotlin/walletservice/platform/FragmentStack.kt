package com.kotlin.walletservice.platform

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentStack(
    private var activity: Activity,
    private var manager: FragmentManager,
    private var containerId: Int
) {
    interface OnBackPressedHandlingFragment {
        fun onBackPressed(): Boolean
    }

    /**
     * Returns the number of fragments in the stack.
     *
     * @return the number of fragments in the stack.
     */
    fun size() = getFragments().size


    fun push(fragment: Fragment) {

        val top = peek()
        if (top != null) {
            manager.beginTransaction()
                .remove(top)
                .add(containerId, fragment, indexToTag(manager.backStackEntryCount + 1))
                .addToBackStack(null)
                .commit()
        } else {
            manager.beginTransaction()
                .add(containerId, fragment, indexToTag(0))
                .commit()
        }

        manager.executePendingTransactions()
    }


    /**
     * Pops the top item if the stack.
     * If the fragment implements [OnBackPressedHandlingFragment], calls [OnBackPressedHandlingFragment.onBackPressed] instead.
     * If [OnBackPressedHandlingFragment.onBackPressed] returns false the fragment gets popped.
     *
     * @return true if a fragment has been popped or if [OnBackPressedHandlingFragment.onBackPressed] returned true;
     */
    fun back(): Boolean {
        val top = peek()
        if (top is OnBackPressedHandlingFragment) {
            if ((top as OnBackPressedHandlingFragment).onBackPressed())
                return true
        }
        return pop()
    }


    /**
     * Pops the topmost fragment from the stack.
     * The lowest fragment can't be popped, it can only be replaced.
     *
     * @return false if the stack can't pop or true if a top fragment has been popped.
     */
    private fun pop(): Boolean {
        if (manager.backStackEntryCount == 0)
            return false
        manager.popBackStackImmediate()
        return true
    }


    /**
     * Replaces stack contents with just one fragment.
     */
    fun replace(fragment: Fragment) {
        manager.run {
            popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            beginTransaction().replace(containerId, fragment, indexToTag(0)).commit()
            executePendingTransactions()
        }
    }

    /**
     * Returns the topmost fragment in the stack.
     */
    fun peek(): Fragment? = manager.findFragmentById(containerId)


    /**
     * Returns a back fragment if the fragment is of given class.
     * If such fragment does not exist and activity implements the given class then the activity will be returned.
     *
     * @param fragment     a fragment to search from.
     * @param callbackType a class of type for callback to search.
     * @param <T>          a type of callback.
     * @return a back fragment or activity.
    </T> */
    fun <T> findCallback(fragment: Fragment, callbackType: Class<T>): T? {

        val back = getBackFragment(fragment)

        if (back != null && callbackType.isAssignableFrom(back.javaClass))
            return back as T?

        return if (callbackType.isAssignableFrom(activity.javaClass)) activity as T else null

    }


    private fun getBackFragment(fragment: Fragment): Fragment? {
        val fragments = getFragments()
        for (f in fragments.indices.reversed()) {
            if (fragments[f] === fragment && f > 0)
                return fragments[f - 1]
        }
        return null
    }


    private fun getFragments(): MutableList<Fragment> {
        val fragments = mutableListOf<Fragment>()
        for (i in 0..manager.backStackEntryCount) {
            val fragment = manager.findFragmentByTag(indexToTag(i))
            if (fragment != null)
                fragments.add(fragment)
        }
        return fragments
    }

    private fun indexToTag(index: Int) = Integer.toString(index)
}